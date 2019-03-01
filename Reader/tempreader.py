import Adafruit_DHT
import time
import datetime
import io
import json


def mean(numbers):
    return float(sum(numbers)) / max(len(numbers), 1)


def printer():
    print('\r{:5d}  Humidity: {:2.1f}%  temp: {:2.1f}°C  {}'.format(humidity, temp,
                                                                    datetime.datetime.now().strftime(
                                                                        '%d.%m.%y %H:%M:%S')), end='')


def api_poser():


    # TODO

def avg_list_min():
    min_counter = 0;


if __name__ == '__main__':

    start = time.time()

    minTemplist = []
    minHumiditylist = []

    while True:
        if time.time() - start > 60:
            print("60s over")
            start = time.time()

            print(mean(minHumiditylist), mean(minTemplist), "  60 sec avg")
            minTemplist = []
            minHumiditylist = []

        humidity, temp = Adafruit_DHT.read(Adafruit_DHT.DHT22, 2)
        if (temp and humidity):
            minHumiditylist.append(humidity)
            minTemplist.append(temp)

        # printer()
        # print('\r{:5d}  {:2.1f}%  {:2.1f}°C  {}'.format(temp_id, humidity, temp, datetime.datetime.now().strftime('%d.%m.%y %H:%M:%S')), end='')
