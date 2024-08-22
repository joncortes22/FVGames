package Controller;

import Model.Client;
import Model.ClientModelDB;
import Model.Item;
import Model.ItemModelDB;

import java.util.ArrayList;

public class ClientController {
    private static ClientModelDB cmdb = new ClientModelDB();
    static ArrayList<Client> clients = cmdb.getAllUsers();

    public static void addNewProduct(int id, String name, String lastName, String address, String email, int moneySum, String paymentMethod){
        Client newClient = new Client(id, name, lastName, address, email, moneySum, paymentMethod);
        clients.add(newClient);
        //cmdb.insertNewProduct(category, name, availability, unitPrice);
    }
}
