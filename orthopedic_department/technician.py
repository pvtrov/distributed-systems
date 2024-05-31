import json
import sys
import threading
import pika  # noqa: F401

from setup import create_connection_and_channel
from constans import ExchangesNames, TechnicianQueues, EmployeesQueues, Keys

EXAMINATIONS = {
    "knee": TechnicianQueues.knee_queue,
    "elbow": TechnicianQueues.elbow_queue,
    "hip": TechnicianQueues.hip_queue,
}

QUEUES = {
    'technician_1': EmployeesQueues.technician_1_queue,
    'technician_2': EmployeesQueues.technician_2_queue,
}


class Technician:
    def __init__(self, technician_id, queue_1, queue_2):
        self.id = technician_id
        self.technician_key = Keys.technician_1_key if technician_id == 1 else Keys.technician_2_key
        self.order_queue_1 = queue_1
        self.order_queue_2 = queue_2
        self.message_queue = QUEUES.get(self.technician_key)

    def _message_receive_handler(self):
        connection, channel = create_connection_and_channel()

        def callback(ch, method, properties, body):
            message = body.decode("utf-8")
            print(f"\nReceived: {message}")
            ch.basic_ack(delivery_tag=method.delivery_tag)

        channel.basic_consume(queue=self.message_queue, on_message_callback=callback, auto_ack=False)
        channel.start_consuming()

    def _order_receive_handler(self, queue_name):
        connection, channel = create_connection_and_channel()

        def callback(ch, method, properties, body):
            order = json.loads(body.decode('utf-8'))
            doctor_key = order['doctor_key']
            examination_type = order['input_type']
            surname = order['surname']

            print(f"Received order for {examination_type} examination: {surname}, from doctor: {doctor_key}\n")

            ch.basic_ack(delivery_tag=method.delivery_tag)
            message = f"{surname}: {examination_type} done\n"
            ch.basic_publish(exchange=ExchangesNames.results, routing_key=doctor_key, body=message.encode("utf-8"))

        channel.basic_consume(queue=queue_name, on_message_callback=callback, auto_ack=False)
        channel.start_consuming()

    def examinate(self):
        print(f"Technician ready for orders for {self.order_queue_1} and {self.order_queue_2} examinations...")
        threading.Thread(target=self._order_receive_handler, args=(EXAMINATIONS[self.order_queue_1],)).start()
        threading.Thread(target=self._order_receive_handler, args=(EXAMINATIONS[self.order_queue_2],)).start()
        threading.Thread(target=self._message_receive_handler).start()


if __name__ == '__main__':
    if len(sys.argv) != 4:
        print('Invalid number of arguments! Please specify technician ID and examination types!')
        sys.exit(-1)

    technician_id = int(sys.argv[1])
    queue_1 = sys.argv[2]
    queue_2 = sys.argv[3]

    technician = Technician(technician_id, queue_1, queue_2)
    technician.examinate()
