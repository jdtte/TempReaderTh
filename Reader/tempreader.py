import Adafruit_DHT
import time
import datetime
import io
import json
import requests

API_ENDPOINT = "http://127.0.0.1:8090/api/temps/add"


def mean(numbers):
    return float(sum(numbers)) / max(len(numbers), 1)


if __name__ == '__main__':

    start = time.time()

    minTemplist = []
    minHumiditylist = []

    while True:
        if time.time() - start > 60:
            #     "date": "27.02.122 15:11:44",
            #     "humidity": 32.1,
            #     "temperature": 21
            json = {
                "date": str(datetime.datetime.now().strftime("%d.%m.%y %H:%M:%S")),
                "humidity": mean(minHumiditylist),
                "temperature": mean(minTemplist)
            }

            try:
                postreq = requests.post(url=API_ENDPOINT, json=json, headers={'content-type': 'application/json'})
                print(mean(minHumiditylist), mean(minTemplist), postreq.status_code, postreq)
            except:
                print(error)
            start = time.time()  # restarts timer
            minTemplist = []
            minHumiditylist = []

        humidity, temp = Adafruit_DHT.read(Adafruit_DHT.DHT22, 2)  # reads data
        if temp and humidity:  # checks if error/empty vals
            minHumiditylist.append(humidity)
            minTemplist.append(temp)
