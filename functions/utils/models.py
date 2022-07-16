import datetime


class Lottery:
    def __init__(self, uuid, api, collection, title, description, color, enabled):
        self.uuid = str(uuid)
        self.api = str(api)
        self.collection = str(collection)
        self.title = str(title)
        self.description = str(description)
        self.color = str(color)
        self.enabled = bool(enabled)

    def to_dict(self):
        dest = {
            u'uuid': self.uuid,
            u'api': self.api,
            u'collection': self.collection,
            u'title': self.title,
            u'description': self.description,
            u'color': self.color,
            u'enabled': self.enabled,
        }
        return dest


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
