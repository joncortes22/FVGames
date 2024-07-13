package org.example;

import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static Connection conectarDb(String DB){
        Connection conexion;
        String host = "jdbc:mysql://127.0.0.1:3308/";
        String user = "FVGamesAdmin";
        String password = "Admin123";

        System.out.println("Conecting DB...");

        try {
            conexion = DriverManager.getConnection(host + DB, user, password);
            System.out.println("Connection Succesful.");
        } catch (SQLException e) {
            System.out.println("DB connection falied.");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return conexion;
    }

    public static void desconexion(Connection cb){
        try {
            cb.close();
            System.out.println("DB connection disconnected...");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Item> inventory = new ArrayList<Item>();
    public static void main(String[] args) throws IOException {
        Connection DB = conectarDb("fvgames");
        Item product1 = new Item(1, "Videogame", "FIFA23", 3, 10);
        Item product2 = new Item(2, "Videogame", "FIFA24", 6, 10);
        Item product3 = new Item(3, "Videogame", "FIFA25", 8, 10);
        Item product4 = new Item(4, "Accesory", "PS5 Remote", 4, 10);
        Item product5 = new Item(5, "Console", "PS5", 12, 10);
        Item product6 = new Item(6, "Subscription", "Play Station Plus", 1, 10);
        inventory.add(product1);
        inventory.add(product2);
        inventory.add(product3);
        inventory.add(product4);
        inventory.add(product5);
        inventory.add(product6);

        /*MyFrame login = new MyFrame("Create User", 480, 310, "buy");*/
        MyFrame login = new MyFrame("Package", 750, 310, "packages");
        /*MyFrame login = new MyFrame("Create User", 480, 550, "user");*/
        /*MyFrame login = new MyFrame("Login", 500, 280, "login");*/
        desconexion(DB);
    }
}