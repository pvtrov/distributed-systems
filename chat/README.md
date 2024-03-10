# Chat command line application

### How to run?

1. Clone repository
2. Enter chat directory
3. Run server and clients in several separate terminals:
    ```bash
   python server.py
   ```
    ```bash
    python client.py
   ```
4. By default messages are sent via TCP. To use UDP or Multicast connection use below flags in your client terminal
    ```bash
    -U # UDP
    -M # Multicast
   ```
   