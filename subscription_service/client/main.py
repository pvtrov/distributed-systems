import random
import sys
import time
import grpc
import greenhouse_conditions_pb2
import greenhouse_conditions_pb2_grpc

CHANNEL = grpc.insecure_channel('127.0.0.5:50566')


def generate_greenhouses_to_subscribe():
    greenhouses_number = random.randint(1, 4)
    greenhouses = []

    for _ in range(greenhouses_number):
        num = random.randint(1, 4)
        while num in greenhouses:
            num = random.randint(1, 4)
        greenhouses.append(str(num))

    return greenhouses


class Client:
    def __init__(self, client_id):
        self.client_id = client_id
        self.greenhouses = generate_greenhouses_to_subscribe()

    def run(self):
        try:
            while True:
                try:
                    stub = greenhouse_conditions_pb2_grpc.GreenhouseConditionsStub(CHANNEL)
                    response = stub.SubscribeGreenhouseConditions(
                        greenhouse_conditions_pb2.GreenhouseConditionsSubscription(
                            greenhouses=self.greenhouses, clientID=self.client_id
                        )
                    )
                    self.listen(response)
                except grpc.RpcError as e:
                    print(f"Error: {e.details()}")
                    print("Reconnecting in 1 second...")
                    time.sleep(1)
        except KeyboardInterrupt:
            print(f"Client stopped {client_id}")

    def listen(self, response):
        try:
            for i in response:
                print(i)
                print("_____________________________________")

        except grpc._channel._Rendezvous:
            print("Connection lost - reconnecting...")
            time.sleep(1)
            self.run()


if __name__ == '__main__':
    if len(sys.argv) < 2:
        print("Give me client ID")
        exit(0)
    else:
        client_id = int(sys.argv[1])

    client = Client(client_id)
    client.run()
