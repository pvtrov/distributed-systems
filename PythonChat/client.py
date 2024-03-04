import socket
import threading
from server import ServerException


class ClientException(Exception):
    pass


class Client:
    def __init__(self):
        self.server_port = 5660
        self.server_ip = "127.0.0.1"
        self.nickname = input("Set your nickname: ")
        self.client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def start_client(self):
        self.client_socket.connect((self.server_ip, self.server_port))

        connecting_thread = threading.Thread(target=self.connect_with_server)
        connecting_thread.start()

        sending_thread = threading.Thread(target=self.write_message())
        sending_thread.start()

    def connect_with_server(self):
        while True:
            try:
                message = self.client_socket.recv(1024).decode('ascii')
                if message == "NICK":
                    self.client_socket.send(self.nickname.encode('ascii'))
                elif message == "SERVER_SHUTDOWN":
                    print("Server closed connection.")
                    raise ServerException("Server closed connection.")
                else:
                    print(message)

            except:
                self.client_socket.close()
                return 0

    def write_message(self):
        while True:
            message = f"{self.nickname}: {input('')}"
            self.client_socket.send(message.encode('ascii'))


if __name__ == '__main__':
    try:
        client = Client()
        client.start_client()

    except KeyboardInterrupt:
        print("Client disappeared!")
