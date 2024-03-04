import datetime
import socket
import threading

MAX_CLIENTS_NUM = 3


class ServerException(Exception):
    pass


class Server:
    def __init__(self):
        self.server_port = 5660
        self.clients = []  # (client_socket, nickname)
        self.server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def start_server(self):
        try:
            print('~~~~~ PYTHON SERVER ~~~~~')
            print('Server listening...')
            self.server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
            self.server_socket.bind(('', self.server_port))
            self.server_socket.listen(MAX_CLIENTS_NUM)
            self.connect_with_client()

        except OSError:
            print("Adress already in use! Please end all of your previous connections")
            return 98

    def broadcast(self, message):
        [client[0].send(message) for client in self.clients]

    def handle_message(self, client):
        while True:
            try:
                message = client[0].recv(1024)
                if not message:
                    raise ServerException("Client ended connection.")

                print(f"[{datetime.datetime.now()}] Got message from {client[1]}")
                self.broadcast(message)

            except ServerException:
                nickname = client[1]
                self.clients.remove(client)
                client[0].close()
                print(f"[{datetime.datetime.now()}] {nickname} left the chat")
                self.broadcast(f"{nickname} left the chat.".encode('ascii'))
                return

    def connect_with_client(self):
        while True:
            client, address = self.server_socket.accept()
            print(f"[{datetime.datetime.now()}] Client {address} connected!")

            client.send('NICK'.encode('ascii'))
            nickname = client.recv(1024).decode('ascii')
            self.clients.append((client, nickname))

            print(f"Nickname set to {nickname}")
            self.broadcast(f"{nickname} joined the chat.".encode('ascii'))
            client.send("Connected to server!".encode('ascii'))

            threading.Thread(target=self.handle_message, args=((client, nickname),)).start()

    def close_connections(self):
        for client in self.clients:
            client[0].send("SERVER_SHUTDOWN".encode('ascii'))
            client[0].close()


if __name__ == '__main__':
    try:
        server = Server()
        server.start_server()

    except KeyboardInterrupt:
        server.close_connections()
        print("Server disappeared!")
