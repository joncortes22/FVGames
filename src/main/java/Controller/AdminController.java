package Controller;

import Model.Admin;
import Model.AdminModelDB;
import Model.Item;
import Model.ItemModelDB;

import java.util.ArrayList;

public class AdminController {
    private static AdminModelDB amdb = new AdminModelDB();
    static ArrayList<Admin> admins = amdb.getAllAdmins();

    public boolean login(int id, String password){
        boolean userFound = false;
        for (Object admin : admins){
            Admin adminAux = (Admin) admin;
            if (adminAux.getId() == id && adminAux.getPassword().equals(password)){
                userFound = true;
                break;
            }
        }
        return userFound;
    }
}
