package Concurrency;

import View.MyFrame;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket clientSocket;
    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws IOException {
        //MyFrame login = new MyFrame("Login for Client", 480, 400, "login");
        MyFrame buyPackage = new MyFrame("Buy Package", 440, 410, "buyPackage");
        try {
            System.out.println("Connecting to server...");
            clientSocket = new Socket(host, port);

            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            String message = dis.readUTF();
            System.out.println("Received message: " + message);

            dis.close();
        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());
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