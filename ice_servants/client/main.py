import sys, Ice
import time

import Demo

DEMO_ENDPOINTS = "tcp -h 127.0.0.2 -p 10000 -z : udp -h 127.0.0.2 -p 10000 -z"


def handle_get_method(communicator):
    base = communicator.stringToProxy(f"shared/get:{DEMO_ENDPOINTS}")
    try:
        shared_service = Demo.SharedServicePrx.checkedCast(base)
        actual_time = shared_service.getTime()
        print(f"Received time: {actual_time}")
    except Ice.ObjectNotExistException:
        print("Invalid proxy...")
    except Exception as e:
        print(f"Error: {e}")


def handle_hello_method(communicator):
    name = input("Enter your name: ")
    base = communicator.stringToProxy(f"dedicated/{name}:{DEMO_ENDPOINTS}")
    try:
        dedicated_service = Demo.DedicatedServicePrx.checkedCast(base)
        hello_statement = dedicated_service.sayHello(name)
        print(hello_statement)
    except Ice.ObjectNotExistException:
        print("Invalid proxy...")
    except Exception as e:
        print(f"Error: {e}")


def get_method():
    print("Choose 'get', 'hello'")
    return input("::: ")


def handle_invalid_proxy(communicator, exit_code):
    communicator.destroy()
    exit(exit_code)


def main():
    communicator = Ice.initialize(sys.argv)
    try:
        while True:
            method = get_method()
            if method == "get":
                handle_get_method(communicator)
            elif method == "hello":
                handle_hello_method(communicator)
            else:
                print("Invalid method")

    except Ice.ConnectionRefusedException:
        communicator.destroy()
        print("Connection failed... retrying...")
        time.sleep(1)
        main()
    except KeyboardInterrupt:
        communicator.destroy()
        exit(0)


if __name__ == '__main__':
    main()
