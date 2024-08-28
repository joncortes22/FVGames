package Concurrency;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);
        } catch (IOException e) {
            throw new RuntimeException("Error starting server", e);
        }
    }

    public void start() {
        while (true) {
            try {
                System.out.println("Waiting for client connections...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected");

                // Create a new ClientHandler thread for each client
                new ClientHandler(clientSocket).start();
            } catch (IOException e) {
                System.err.println("Error accepting client connection: " + e.getMessage());
            }
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try {
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
                dos.writeUTF("Hello from server");
                dos.flush();
                System.out.println("Sent greeting to client");

            } catch (IOException e) {
                System.err.println("Error handling client: " + e.getMessage());
            } finally {
                try {
                    if (clientSocket != null && !clientSocket.isClosed()) {
                        clientSocket.close();
                    }
                } catch (IOException e) {
                    System.err.println("Error closing client socket: " + e.getMessage());
                }
            }
        }
    }
}