from constans import ExchangesNames, EmployeesQueues, TechnicianQueues
from setup import create_connection_and_channel


class Cleanup:
    def __init__(self):
        self.connection, self.channel = create_connection_and_channel()

    def _delete_exchange(self, exchange_name):
        self.channel.exchange_delete(exchange=exchange_name)

    def _delete_queue(self, queue_name):
        self.channel.queue_delete(queue=queue_name)

    def do_cleanup(self):
        for exchange in ExchangesNames:
            self._delete_exchange(exchange.value)
        print("Exchanges deleted")

        for queue in EmployeesQueues:
            self._delete_queue(queue.value)
        print("Doctor queues deleted")

        for queue in TechnicianQueues:
            self._delete_queue(queue.value)
        print("Technician queues deleted")

        self.channel.close()
        self.connection.close()


if __name__ == '__main__':
    cleanup = Cleanup()
    cleanup.do_cleanup()
