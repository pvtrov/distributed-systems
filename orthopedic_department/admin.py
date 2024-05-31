import logging
import threading

from setup import create_connection_and_channel
from constans import *

import pika  # noqa: F401

log_lock = threading.Lock()

logger = logging.getLogger('orthopedic_department')
logger.setLevel(logging.INFO)
handler = logging.FileHandler(LOGGING_FILE)
handler.setFormatter(logging.Formatter('%(asctime)s - %(levelname)s - %(message)s'))
logger.addHandler(handler)


def clear_log_file():
    with open(LOGGING_FILE, 'w') as file:
        file.truncate(0)


class Admin:
    def __init__(self):
        self.messages_queue = EmployeesQueues.admin_queue
        self.orders_queue = TechnicianQueues.admin_queue

    def _publish_handler(self):
        connection, channel = create_connection_and_channel()

        while True:
            message = input("Provide info to all employees: ")
            message = 'ADMIN: ' + message
            channel.basic_publish(
                exchange=ExchangesNames.broadcast_messages, routing_key='', body=message.encode('utf-8')
            )

    def _receive_handler(self, queue):
        connection, channel = create_connection_and_channel()

        def callback(ch, method, properties, body):
            message = body.decode("utf-8")
            ch.basic_ack(delivery_tag=method.delivery_tag)
            with log_lock:
                logger.info(message)

        channel.basic_consume(queue=queue, on_message_callback=callback, auto_ack=False)
        channel.start_consuming()

    def run(self):
        print("Admin ready...")
        threading.Thread(target=self._receive_handler, args=(EmployeesQueues.admin_queue,)).start()
        threading.Thread(target=self._receive_handler, args=(TechnicianQueues.admin_queue,)).start()
        threading.Thread(target=self._publish_handler).start()


if __name__ == "__main__":
    clear_log_file()
    admin = Admin()
    admin.run()
