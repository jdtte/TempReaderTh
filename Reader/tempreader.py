import Adafruit_DHT
import time
import datetime
import io
import json
import requests

API_ENDPOINT = ""


def mean(numbers):
    return float(sum(numbers)) / max(len(numbers), 1)


def api_poser():
    return null


def avg_list_min():
    min_counter = 0;


if __name__ == '__main__':

    start = time.time()

    minTemplist = []
    minHumiditylist = []

    while True:
        if time.time() - start > 60:
            data = {}
            postreq = requests.post(url=API_ENDPOINT, data=data)
            start = time.time()  # restarts timer

            print(mean(minHumiditylist), mean(minTemplist), postreq.status_code)
            minTemplist = []
            minHumiditylist = []

        humidity, temp = Adafruit_DHT.read(Adafruit_DHT.DHT22, 2)  # reads data
        if temp and humidity:  # checks if error/empty vals
            minHumiditylist.append(humidity)
            minTemplist.append(temp)
