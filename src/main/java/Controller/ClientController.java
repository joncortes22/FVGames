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

    public static boolean validateExistance(int id){
        boolean found = false;
        for (Client client : clients){
            if (client.getId() == id){
                found = true;
            }
        }
        return found;
    }

    public static int getCurrentUser(){
        int id = 0;
        for (Client client : clients){
            if (client.isSessionOpen()){
                id =  client.getId();
            }
        }
        return id;
    }

    public static void addNewClient(int id, String name, String lastName, String address, String email, int moneySum, String paymentMethod, String password){
        Client newClient = new Client(id, name, lastName, address, email, moneySum, paymentMethod, password);
        clients.add(newClient);
        cmdb.insertNewCustomer(String.valueOf(id), password, name, lastName, address, email, moneySum, paymentMethod);
        clients = cmdb.getAllUsers();
    }
}
