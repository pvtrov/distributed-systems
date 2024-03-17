import configparser
import requests
from fastapi import FastAPI, Request
from fastapi.responses import HTMLResponse
from fastapi.templating import Jinja2Templates
from requests import HTTPError
from unidecode import unidecode

app = FastAPI()
templates = Jinja2Templates(directory="static")

zodiac_signs = {
    'baran': 'aries',
    'byk': 'touro',
    'bliznieta': 'gemeos',
    'rak': 'cancer',
    'lew': 'leao',
    'panna': 'virgem',
    'waga': 'libra',
    'skorpion': 'escorpiao',
    'strzelec': 'sagitorio',
    'koziorozec': 'capricornio',
    'wodnik': 'aquario',
    'ryby': 'peixes'
}

error_messages = {
    'invalid_sign': 'Zły znak zodiaku, spróbój ponownie!',
    'horoscopeAPI_error': 'Wystąpił błąd z połączeniem z horoscopeAPI',
    'translatorAPI_error': 'Wystąpił błąd z połączeniem z translatorAPI',
    'invalid_path': 'Nie znaleziono strony dla ścieżki: {path}'
}


def clear_input(text: str):
    text = text.lower()
    text = unidecode(text)
    return text


def validate_sign(zodiac_sign: str):
    return zodiac_sign in zodiac_signs.keys()


class HoroscopeServiceException(Exception):
    def __init__(self, rc, error_msg):
        self.doggo_URL = "https://http.dog/"
        self.rc = rc
        self.error_msg = error_msg


class HoroscopeAPI:
    def __init__(self):
        self._parse_config()
        self.translatorAPI = TranslatorAPI(self.auth_key, self.target_lang, self.translator_URL)

    def _parse_config(self):
        config = configparser.ConfigParser()
        config.read('config.ini')

        self.auth_key = config['DeepL']['auth_key']
        self.target_lang = config['DeepL']['target_lang']
        self.translator_URL = config['DeepL']['URL']

        self.x_rapid_key = config['Horoscope']['X-RapidAPI-Key']
        self.x_rapid_host = config['Horoscope']['X-RapidAPI-Host']
        self.horoscope_URL = config['Horoscope']['URL']

    def _create_headers(self):
        return {"X-RapidAPI-Key": self.x_rapid_key, "X-RapidAPI-Host": self.x_rapid_host}

    @staticmethod
    def _get_portuguese_sign(zodiac_sign: str):
        return zodiac_signs[zodiac_sign]

    def _request_horoscope(self, zodiac_sign: str):
        url = f"{self.horoscope_URL}{zodiac_sign}"
        response = requests.get(url=url, headers=self._create_headers())
        response.raise_for_status()

        return response.json()['text']

    def get_horoscope(self, zodiac_sign: str):
        pt_zodiac_sign = self._get_portuguese_sign(zodiac_sign)

        try:
            pt_horoscope = self._request_horoscope(pt_zodiac_sign)
        except requests.exceptions.HTTPError as err:
            raise HTTPError(
                f"{error_messages['horoscopeAPI_error']}: {err.response.reason}", response=err.response
            )

        try:
            translated_horoscope = self.translatorAPI.translate_text(pt_horoscope)
        except requests.exceptions.HTTPError as err:
            raise HTTPError(
                f"{error_messages['translatorAPI_error']}: {err.response.reason}", response=err.response
            )

        return translated_horoscope


class TranslatorAPI:
    def __init__(self, auth_key: str, target_lang: str, url: str):
        self.translator_URL = url
        self.auth_key = auth_key
        self.target_lang = target_lang

    def _create_header(self):
        return {"Authorization": f"DeepL-Auth-Key {self.auth_key}"}

    def translate_text(self, text: str):
        params = {'text': text, 'target_lang': self.target_lang}
        print(self._create_header())
        response = requests.post(url=self.translator_URL, headers=self._create_header(), data=params)
        response.raise_for_status()

        return response.json()['translations'][0]['text']


class HoroscopeService:
    def __init__(self):
        self.horoscopeAPI = HoroscopeAPI()

    def get_horoscope(self, zodiac_sign: str, request: Request):
        zodiac_sign = clear_input(zodiac_sign)
        if not validate_sign(zodiac_sign):
            raise HoroscopeServiceException(rc=400, error_msg=error_messages['invalid_sign'])

        try:
            horoscope = self.horoscopeAPI.get_horoscope(zodiac_sign)
        except HTTPError as err:
            raise HoroscopeServiceException(rc=err.response.status_code, error_msg=err.args[0])

        return templates.TemplateResponse(
            "print_horoscope.html", {"request": request, "zodiac_sign": zodiac_sign, "horoscope_text": horoscope}
        )


horoscope_service = HoroscopeService()


@app.exception_handler(HoroscopeServiceException)
async def horoscope_exception_handler(request: Request, exception: HoroscopeServiceException):
    url = f"{exception.doggo_URL}{exception.rc}.jpg"
    return templates.TemplateResponse(
        "error_site.html", {"request": request, "image_url": url, "error_message": exception.error_msg}
    )


@app.get("/{zodiac_sign}")
async def main_horoscope_for_zodiac_sign(request: Request, zodiac_sign: str):
    return horoscope_service.get_horoscope(zodiac_sign, request)


@app.get("/", response_class=HTMLResponse)
async def main_horoscope_form(request: Request):
    return templates.TemplateResponse("zodiac_sign_form.html", {"request": request})


@app.get("/{path:path}", response_class=HTMLResponse)
async def catch_all(path: str):
    raise HoroscopeServiceException(404, error_messages['invalid_path'].format(path=path))
