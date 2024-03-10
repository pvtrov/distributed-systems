import select
import socket
import struct
import threading
from server import ServerException

CODING = 'utf-8'
MULTICAST_IP = '224.1.1.1'


class ClientException(Exception):
    pass


class Client:
    def __init__(self):
        self.server_port = 5660
        self.multi_port = 5661
        self.server_ip = "127.0.0.1"
        self.nickname = input("Set your nickname: ")
        self.sending_mode = 'tcp'
        self.tcp_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.udp_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.multi_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM, socket.IPPROTO_UDP)

    def start_client(self):
        print('~~~~~ PYTHON CLIENT ~~~~~')
        self.tcp_socket.connect((self.server_ip, self.server_port))

        _, tcp_port = self.tcp_socket.getsockname()

        self.udp_socket.bind((self.server_ip, tcp_port))

        self.multi_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.multi_socket.bind(('', self.multi_port))
        multi_req = struct.pack("4sl", socket.inet_aton(MULTICAST_IP), socket.INADDR_ANY)
        self.multi_socket.setsockopt(socket.IPPROTO_IP, socket.IP_ADD_MEMBERSHIP, multi_req)

        connecting_thread = threading.Thread(target=self._receive_message)
        connecting_thread.start()

        sending_thread = threading.Thread(target=self._write_message)
        sending_thread.start()

    def _receive_message(self):
        while True:
            ready_sockets, _, _ = select.select([self.tcp_socket, self.udp_socket, self.multi_socket], [], [])

            for sock in ready_sockets:
                try:
                    if sock is self.tcp_socket:
                        self._receive_tcp()
                    elif sock is self.udp_socket:
                        self._receive_udp()
                    elif sock is self.multi_socket:
                        self._receive_multicast()

                except (ConnectionResetError, ConnectionAbortedError, ServerException):
                    print("Error occured!")
                    exit(1)

    def _receive_tcp(self):
        message = self.tcp_socket.recv(1024).decode(CODING)
        if message == "NICK":
            self.tcp_socket.send(self.nickname.encode(CODING))
        elif message == "SERVER_SHUTDOWN":
            print("Server closed connection.")
            raise ServerException("Server closed connection.")
        else:
            message = "[TCP] " + message
            print(message)

    def _receive_udp(self):
        message = "[UDP] " + self.udp_socket.recv(1024).decode(CODING)
        print(message)

    def _receive_multicast(self):
        message = "[MULTI] " + self.multi_socket.recv(1024).decode(CODING)
        print(message)

    def _write_message(self):
        while True:
            input_message = input('')

            if input_message == '-U':
                self.sending_mode = 'udp'
                continue
            elif input_message == '-T':
                self.sending_mode = 'tcp'
                continue
            elif input_message == '-M':
                self.sending_mode = 'multicast'
                continue

            message = f"{self.nickname}: {input_message}"
            if self.sending_mode == 'tcp':
                self.tcp_socket.send(message.encode(CODING))
                print("[TCP] " + message)
            elif self.sending_mode == 'udp':
                self.udp_socket.sendto(message.encode(CODING), (self.server_ip, self.server_port))
                print("[UDP] " + message)
            elif self.sending_mode == 'multicast':
                self.multi_socket.sendto(message.encode(CODING), (MULTICAST_IP, self.multi_port))


if __name__ == '__main__':
    try:
        client = Client()
        client.start_client()

    except KeyboardInterrupt:
        print("Client disappeared!")
