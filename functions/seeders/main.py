import random
import time

import firebase_admin
from firebase_admin import firestore
from firebase_admin.credentials import Certificate

from functions.crawler.main import crawler
from functions.utils.models import Lottery, ContestResult


def get_lotteries():
    return [
        Lottery(
            uuid="MEGA",
            api="http://servicebus2.caixa.gov.br/portaldeloterias/api/megasena",
            collection="mega_sena_results",
            title="Mega-Sena",
            description="A Mega-Sena é a loteria que paga milhões para o acertador dos 6 números. Para apostar você escolhe de 6 a 15 números, entre os 60 disponíveis no volante.",
            color="#209869",
            enabled=True
        ),
        Lottery(
            uuid="QUINA",
            api="http://servicebus2.caixa.gov.br/portaldeloterias/api/quina",
            collection="quina_results",
            title="Quina",
            description="Na Quina, você aposta de 5 a 15 números, entre os 80 disponíveis, e concorre a prêmios de valores grandiosos.",
            color="#260085",
            enabled=True
        ),
        Lottery(
            uuid="LOTOFACIL",
            api="http://servicebus2.caixa.gov.br/portaldeloterias/api/lotofacil",
            collection="lotofacil_results",
            title="Lotofácil",
            description="A Lotofácil é a loteria certa para você que gosta de apostar e, principalmente, ganhar. Você pode marcar de 15 a 20 números, entre os 25 disponíveis no volante.",
            color="#930989",
            enabled=True
        ),
        Lottery(
            uuid="LOTOMANIA",
            api="http://servicebus2.caixa.gov.br/portaldeloterias/api/lotomania",
            collection="lotomania_results",
            title="Lotomania",
            description="Na Lotomania, você escolhe 50 números e ganha se acertar 15, 16, 17, 18, 19, 20 ou nenhum número. Ela é simples de apostar e fácil de ganhar.",
            color="#F78100",
            enabled=True
        ),
    ]


def instantiate():
    try:
        key_path = '../credentials.json'
        cred = Certificate(key_path)
        app = firebase_admin.initialize_app(cred)
    except:
        app = firebase_admin.initialize_app()

    db = firestore.client()
    return app, db


def seed_results(db, transaction):
    lotteries = get_lotteries()

    for lottery in lotteries:
        contest_number = 1
        last_contest = int(crawler(lottery.api)["numero"])
        print(lottery.api, "with", last_contest, "results")

        while contest_number <= last_contest:
            url = f'{lottery.api}/{contest_number}'
            draw = get_contest_result_by_url(url)

            save(db, None, lottery.collection, draw.id, draw.__dict__)

            delay = random.randrange(212, 345) / 1000
            time.sleep(delay)  # let's avoid being banned huh!?
            contest_number += 1


def seed_lotteries(db, transaction):
    lotteries = get_lotteries()
    for lottery in lotteries:
        save(
            db=db,
            transaction=transaction,
            collection="lotteries",
            uuid=lottery.uuid,
            document=lottery.to_dict()
        )


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


def save(db, transaction, collection, uuid, document):
    result_ref = db.collection(collection).document(uuid)
    if transaction:
        transaction.set(result_ref, document)
    else:
        result_ref.set(document)


def seed_them_all():
    app, db = instantiate()

    transaction = db.transaction()
    seed_lotteries(db, transaction)
    seed_results(db, transaction)
    transaction.commit()
