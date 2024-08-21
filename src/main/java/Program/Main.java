package Program;

import Model.Admin;
import Model.Client;
import Model.Item;
import View.MyFrame;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        //Seguimos trabajando en la unión de las diferentes ventanas que ya han sido programadas
        //por ahora puede descomentar algunas de las ventanas listas que luego serán unidas en un solo sistema

        //MyFrame login = new MyFrame("Login", 480, 400, "login");
        MyFrame admin = new MyFrame("Login", 480, 315, "admin");
        //MyFrame buyItem = new MyFrame("Buy Item", 480, 400, "buy");
        //MyFrame newClient = new MyFrame("Create User", 480, 550, "newClient");
    }
}