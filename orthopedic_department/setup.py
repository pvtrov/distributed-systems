import pika
from pika.exchange_type import ExchangeType
from constans import *


def create_connection_and_channel():
    connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
    channel = connection.channel()
    return connection, channel


class SetUp:
    def __init__(self):
        self.connection, self.channel = create_connection_and_channel()

    def _declare_exchange(self, exchange_name, exchange_type="topic"):
        if exchange_name == 'Broadcast messages':
            self.channel.exchange_declare(exchange_name, exchange_type=ExchangeType.fanout)
        else:
            self.channel.exchange_declare(exchange_name, exchange_type=ExchangeType(exchange_type))

    def _declare_queue(self, queue_name):
        self.channel.queue_declare(queue_name)

    def _bind_queue_to_exchange(self, queue_name, exchange_name, key=None):
        self.channel.queue_bind(exchange=exchange_name, queue=queue_name, routing_key=key)

    def prepare(self):
        for exchange in ExchangesNames:
            self._declare_exchange(exchange.value)
        print("Exchanges prepared")

        for queue in EmployeesQueues:
            self._declare_queue(queue.value)
        print("Doctor queues prepared")

        for queue in TechnicianQueues:
            self._declare_queue(queue.value)
        print("Technician queues prepared")

        self._bind_queue_to_exchange(EmployeesQueues.doctor_1_queue, ExchangesNames.results, Keys.doctor_1_key)
        self._bind_queue_to_exchange(EmployeesQueues.doctor_2_queue, ExchangesNames.results, Keys.doctor_2_key)
        self._bind_queue_to_exchange(EmployeesQueues.admin_queue, ExchangesNames.results, '#')

        self._bind_queue_to_exchange(EmployeesQueues.doctor_2_queue, ExchangesNames.broadcast_messages)
        self._bind_queue_to_exchange(EmployeesQueues.doctor_1_queue, ExchangesNames.broadcast_messages)
        self._bind_queue_to_exchange(EmployeesQueues.technician_1_queue, ExchangesNames.broadcast_messages)
        self._bind_queue_to_exchange(EmployeesQueues.technician_2_queue, ExchangesNames.broadcast_messages)
        self._bind_queue_to_exchange(EmployeesQueues.admin_queue, ExchangesNames.broadcast_messages)

        self._bind_queue_to_exchange(TechnicianQueues.knee_queue, ExchangesNames.orders, Keys.knee_queue)
        self._bind_queue_to_exchange(TechnicianQueues.elbow_queue, ExchangesNames.orders, Keys.elbow_queue)
        self._bind_queue_to_exchange(TechnicianQueues.hip_queue, ExchangesNames.orders, Keys.hip_queue)
        self._bind_queue_to_exchange(TechnicianQueues.admin_queue, ExchangesNames.orders, '#')

        self.channel.close()
        self.connection.close()


if __name__ == '__main__':
    setup = SetUp()
    setup.prepare()
