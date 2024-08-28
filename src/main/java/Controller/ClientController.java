package Controller;

import Model.*;

import java.util.ArrayList;

public class ClientController {
    private static ClientModelDB cmdb = new ClientModelDB();
    static ArrayList<Client> clients = cmdb.getAllUsers();

    public boolean login(int id, String password){
        boolean userFound = false;
        for (Object client : clients){
            Client clientAux = (Client) client;
            if (clientAux.getId() == id && clientAux.getPassword().equals(password)){
                userFound = true;
                clientAux.setSessionOpen(true);
                break;
            }
        }
        return userFound;
    }

    public static void addNewProduct(int id, String name, String lastName, String address, String email, int moneySum, String paymentMethod, String password){
        Client newClient = new Client(id, name, lastName, address, email, moneySum, paymentMethod, password);
        clients.add(newClient);
        //cmdb.insertNewProduct(category, name, availability, unitPrice);
    }
}
