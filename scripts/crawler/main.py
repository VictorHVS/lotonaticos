import datetime
import json

import firebase_admin
import requests
import urllib3
from firebase_admin import firestore
from firebase_admin.credentials import Certificate
from urllib3.exceptions import InsecureRequestWarning

urllib3.disable_warnings(InsecureRequestWarning)

COLLECTION_LOTTERIES = "lotteries"


class ContestResult:
    class WinnerByCity:
        def __init__(self, winners_count, city, uf, prize_amount):
            self.winners_count = int(winners_count)
            self.city = str(city)
            self.uf = str(uf)
            self.prize_amount = float(prize_amount)

    class PrizeSharing:
        def __init__(self, track_description, track, winners_count, prize_amount):
            self.track_description = str(track_description)
            self.track = int(track)
            self.winners_count = int(winners_count)
            self.prize_amount = float(prize_amount)

    def extract_date(self, str_date):
        try:
            date = datetime.datetime.strptime(str_date, '%d/%m/%Y')
        except:
            date = None
        return date

    def __init__(self,
                 id,
                 accumulated,
                 result_draw_at,
                 next_drawn_at,
                 drawn_numbers,
                 drawn_numbers_sorted,
                 winners_list,
                 prize_sharing_list,
                 draw_location,
                 city_uf_name,
                 amount_raised,
                 next_drawn_amount_raised,
                 drawn_0_5_amount_raised,
                 especial_drawn_amount_raised,
                 next_drawn_amount_estimated,
                 updated_at):
        self.id = str(id)
        self.accumulated = bool(accumulated)
        self.result_draw_at = self.extract_date(result_draw_at)
        self.next_drawn_at = self.extract_date(next_drawn_at)
        self.drawn_numbers = list(map(int, drawn_numbers))
        self.drawn_numbers_sorted = list(map(int, drawn_numbers_sorted))
        self.winners_list = winners_list
        self.prize_sharing_list = prize_sharing_list
        self.draw_location = str(draw_location)
        self.city_uf_name = str(city_uf_name)
        self.amount_raised = float(amount_raised)
        self.drawn_0_5_amount_raised = float(drawn_0_5_amount_raised)
        self.especial_drawn_amount_raised = float(especial_drawn_amount_raised)
        self.next_drawn_amount_raised = float(next_drawn_amount_raised)
        self.next_drawn_amount_estimated = float(next_drawn_amount_estimated)
        self.updated_at = updated_at


def instantiate():
    try:
        key_path = '../credentials.json'
        cred = Certificate(key_path)
        app = firebase_admin.initialize_app(cred)
        db = firestore.client()
    except:
        app = firebase_admin.initialize_app()
        db = firestore.client()

    return app, db


def crawler(url):
    response = requests.request("GET", url, verify=False)
    return json.loads(response.text)


def save(db, transaction, collection, uuid, document):
    result_ref = db.collection(collection).document(uuid)
    if transaction:
        transaction.set(result_ref, document)
    else:
        result_ref.set(document)


def fetch_lotteries(db):
    docs = db.collection(COLLECTION_LOTTERIES).stream()

    lotteries = []

    for doc in docs:
        lottery = doc.to_dict()
        lotteries.append(lottery)

    return lotteries


def get_contest_result_by_url(url):
    contest = crawler(url)

    winner_by_city_list = []
    prize_sharing_list = []

    for item in contest['listaRateioPremio']:
        prize_sharing_list.append(
            ContestResult.PrizeSharing(
                track_description=item['descricaoFaixa'],
                track=item['faixa'],
                winners_count=item['numeroDeGanhadores'],
                prize_amount=item['valorPremio']
            )
        )

    for item in contest['listaMunicipioUFGanhadores']:
        winner_by_city_list.append(
            ContestResult.WinnerByCity(
                winners_count=item['ganhadores'],
                city=item['municipio'],
                uf=item['uf'],
                prize_amount=prize_sharing_list[0].prize_amount
            )
        )

    return ContestResult(
        id=contest["numero"],
        accumulated=contest['acumulado'],
        result_draw_at=contest['dataApuracao'],
        next_drawn_at=contest['dataProximoConcurso'],
        drawn_numbers=contest['dezenasSorteadasOrdemSorteio'],
        drawn_numbers_sorted=contest['listaDezenas'],
        winners_list=[x.__dict__ for x in winner_by_city_list],
        prize_sharing_list=[x.__dict__ for x in prize_sharing_list],
        draw_location=contest['localSorteio'],
        city_uf_name=contest['nomeMunicipioUFSorteio'],
        amount_raised=contest['valorArrecadado'],
        next_drawn_amount_raised=contest['valorAcumuladoProximoConcurso'],
        drawn_0_5_amount_raised=contest['valorAcumuladoConcurso_0_5'],
        especial_drawn_amount_raised=contest['valorAcumuladoConcursoEspecial'],
        next_drawn_amount_estimated=contest['valorEstimadoProximoConcurso'],
        updated_at=firestore.firestore.SERVER_TIMESTAMP
    )


def entry_point(event, context):
    app, db = instantiate()
    lotteries = fetch_lotteries(db)

    for lottery in lotteries:
        last_result = get_contest_result_by_url(lottery['api'])

        save(
            db=db,
            collection=lottery["collection"],
            uuid=str(last_result.id),
            document=last_result,
            transaction=None
        )
