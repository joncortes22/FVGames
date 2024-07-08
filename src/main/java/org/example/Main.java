package org.example;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static ArrayList<Item> inventory = new ArrayList<Item>();
    public static void main(String[] args) throws IOException {
        Item product1 = new Item(1, "Videogame", "FIFA23", 3, 10);
        Item product2 = new Item(2, "Videogame", "FIFA24", 6, 10);
        Item product3 = new Item(3, "Videogame", "FIFA25", 8, 10);
        Item product4 = new Item(4, "Accesory", "PS5 Remote", 4, 10);
        Item product5 = new Item(5, "Console", "PS5", 12, 10);
        Item product6 = new Item(6, "Subscription", "Play Station Plus", 14, 10);
        inventory.add(product1);
        inventory.add(product2);
        inventory.add(product3);
        inventory.add(product4);
        inventory.add(product5);
        inventory.add(product6);

        /*MyFrame login = new MyFrame("Create User", 480, 310, "buy");*/
        /*MyFrame login = new MyFrame("Create User", 480, 550, "user");*/
        /*MyFrame login = new MyFrame("Login", 500, 280, "login");*/
    }
}