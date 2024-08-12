package Controller;

import Model.Admin;
import Model.AdminModelDB;

import java.util.ArrayList;

public class AdminController {
    private AdminModelDB amdb;


    public boolean login(int id, String password){
        ArrayList<Admin> admins = amdb.getAllAdmins();
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
