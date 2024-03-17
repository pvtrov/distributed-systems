import configparser
from typing import Union

import requests
from fastapi import FastAPI, Request
from fastapi.templating import Jinja2Templates
from fastapi.responses import HTMLResponse
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


def remove_polish_signs(text):
    text = text.lower()
    text = unidecode(text)
    return text


class HoroscopeService:
    def __init__(self):
        self.horoscope_URL = "https://horoscope-api.p.rapidapi.com/pt/"
        self.translator_URL = "https://api-free.deepl.com/v2/translate"
        self.doggo_URL = "https://http.dog/"
        self._parse_config()

    def _parse_config(self):
        config = configparser.ConfigParser()
        config.read('config.ini')

        self.auth_key = config['DeepL']['auth_key']
        self.target_lang = config['DeepL']['target_lang']

        self.x_rapid_key = config['Horoscope']['X-RapidAPI-Key']
        self.x_rapid_host = config['Horoscope']['X-RapidAPI-Host']

    def _create_headers(self, is_translator=False):
        if is_translator:
            return {"Authorization": f"DeepL-Auth-Key {self.auth_key}"}

        return {"X-RapidAPI-Key": self.x_rapid_key, "X-RapidAPI-Host": self.x_rapid_host}

    def _translate_horoscope(self, text_to_translate: str):
        params = {'text': text_to_translate, 'target_lang': self.target_lang}
        try:
            response = requests.post(
                url=self.translator_URL, headers=self._create_headers(is_translator=True), data=params
            )
            response.raise_for_status()
            json_data = response.json()
            return json_data['translations'][0]['text']

        except requests.exceptions.HTTPError:
            return response.status_code

    def get_horoscope(self, zodiac_sign_pl: str, request: Request):
        zodiac_sign = zodiac_signs[remove_polish_signs(zodiac_sign_pl)]
        url = f"{self.horoscope_URL}{zodiac_sign}"
        try:
            response = requests.get(url=url, headers=self._create_headers())
            response.raise_for_status()

            json_data = response.json()
            horoscope_text = self._translate_horoscope(json_data['text'])

            if isinstance(horoscope_text, int):
                err_msg = "Wystąpił nieoczekiwany błąd w tłumaczeniu!"
                return self.return_error_site(request, response.status_code, err_msg)

            return templates.TemplateResponse(
                "print_horoscope.html",
                {"request": request, "zodiac_sign": zodiac_sign_pl, "horoscope_text": horoscope_text}
            )

        except requests.exceptions.HTTPError:
            if response.status_code == 404:
                err_msg = f"Nie znaleziono horoskopu dla znaku {zodiac_sign_pl}"
                return self.return_error_site(request, response.status_code, err_msg)

            err_msg = "Wystąpił nieoczekiwany błąd w tworzeniu horoskopu!"
            return self.return_error_site(request, response.status_code, err_msg)

    def return_error_site(self, request: Request, error_code: Union[int, str], error_message: str):
        url = f"{self.doggo_URL}{error_code}.jpg"
        return templates.TemplateResponse(
            "error_site.html", {"request": request, "image_url": url, "error_message": error_message}
        )


horoscope_service = HoroscopeService()


@app.get("/{zodiac_sign}")
async def main_horoscope_for_zodiac_sign(request: Request, zodiac_sign: str):
    if zodiac_sign not in zodiac_signs.keys():
        err_msg = "Zły znak zodiaku, spróbój ponownie!"
        return horoscope_service.return_error_site(request, "500", err_msg)

    return horoscope_service.get_horoscope(zodiac_sign, request)


@app.get("/", response_class=HTMLResponse)
async def main_horoscope_form(request: Request):
    return templates.TemplateResponse("zodiac_sign_form.html", {"request": request})


@app.get("/{path:path}", response_class=HTMLResponse)
async def catch_all(request: Request, path: str):
    err_msg = f"Nie znaleziono strony dla ścieżki: {path}"
    return horoscope_service.return_error_site(request, "404", err_msg)
