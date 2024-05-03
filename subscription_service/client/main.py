import time
import grpc
import greenhouse_conditions_pb2
import greenhouse_conditions_pb2_grpc

CHANNEL = grpc.insecure_channel('127.0.0.5:50566')


def run():
    stub = greenhouse_conditions_pb2_grpc.GreenhouseConditionsStub(CHANNEL)
    response = stub.SubscribeGreenhouseConditions(greenhouse_conditions_pb2.GreenhouseConditionsSubscription(
        greenhouses=["1"], clientID=1)
    )
    listen(response)


def listen(response):
    try:
        for i in response:
            print(i)
        print("_____________________________________")
    except grpc._channel._Rendezvous:
        print("Connection lost - reconnecting...")
        time.sleep(1)
        run()


if __name__ == '__main__':
    run()