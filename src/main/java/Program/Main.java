package Program;

import Concurrency.Client;
import Concurrency.Server;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new Thread(() -> {
            int port = 6060;
            Server server = new Server(port);
            server.start();
        }).start();

        int numberOfClients = 1;
        for (int i = 0; i < numberOfClients; i++) {
            final int clientId = i;
            new Thread(() -> {
                Client client = new Client("127.0.0.1", 6060);
                try {
                    client.start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }, "Client-Thread-" + clientId).start();
        }
    }
}