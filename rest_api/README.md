# :sparkles: Horoscope API App :sparkles:

This FastAPI application serves as a tool for generating today's horoscope. You can simply select your zodiac sign to receive a personalized horoscope prediction.

---

## How to use?
### Installation
* Enter repository which contains **horoscope_service.py**
* Use pip to install requirements
    ```bash
    pip install -r requiements.txt
   ```

### Usage
* Start app by running:
    ```bash
  uvicorn horoscope_service:app --reload
   ```
  You can now access the application by visiting http://localhost:8000 in your web browser.

---

## API Endpoints
### GET /
Homepage where you can enter your zodiac sign.

### GET /{zodiac_sign}
Here you can view your today's horoscope.