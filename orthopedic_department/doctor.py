import json
import sys
import threading

import pika  # noqa: F401

from constans import Keys, ExchangesNames, EmployeesQueues
from setup import create_connection_and_channel

QUEUES = {
    'doctor_1': EmployeesQueues.doctor_1_queue,
    'doctor_2': EmployeesQueues.doctor_2_queue,
}


class Doctor:
    def __init__(self, doctor_id):
        self.doctor_id = doctor_id
        self.doctor_key = Keys.doctor_1_key if doctor_id == 1 else Keys.doctor_2_key
        self.queue = QUEUES.get(self.doctor_key)

    def _print_info(self):
        print("Available examinations: " + Keys.knee_queue + ", " + Keys.elbow_queue + ", " + Keys.hip_queue)

    def _publish_handler(self):
        connection, channel = create_connection_and_channel()

        while True:
            self._print_info()
            input_type = input("Provide examination type: ")

            if input_type not in ("knee", "elbow", "hip"):
                print("Invalid examination type, try again!")
                continue

            message = {
                "doctor_key": self.doctor_key,
                "input_type": input_type,
                "surname": input("Surname: ")
            }
            channel.basic_publish(
                exchange=ExchangesNames.orders, routing_key=input_type, body=json.dumps(message).encode('utf-8')
            )
            print(f"Sent: examination order, with key: {input_type}, to exchange: {ExchangesNames.orders}\n")

    def _receive_handler(self):
        connection, channel = create_connection_and_channel()

        def callback(ch, method, properties, body):
            message = body.decode("utf-8")
            print(f"\nReceived: {message}")
            ch.basic_ack(delivery_tag=method.delivery_tag)

        channel.basic_consume(queue=self.queue, on_message_callback=callback, auto_ack=False)
        channel.start_consuming()

    def heal(self):
        print(f"Doctor {self.doctor_id} ready for healing...")
        threading.Thread(target=self._receive_handler).start()
        threading.Thread(target=self._publish_handler).start()


if __name__ == '__main__':
    if len(sys.argv) != 2:
        print('Invalid number of arguments! Please specify doctor ID!')
        sys.exit(-1)

    doctor_id = int(sys.argv[1])
    doctor = Doctor(doctor_id)
    doctor.heal()
