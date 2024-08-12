package Controller;

import Model.Admin;
import Model.AdminModelDB;
import Program.Main;

import java.util.ArrayList;

public class AdminController {
    private AdminModelDB amdb;


    public boolean login(int id, String password){
        ArrayList admins = amdb.getAllAdmins();
        boolean userFound = false;
        for (Object admin : admins){
            admin = 
            if (admin.getId() == id && admin.getPassword().equals(password)){
                userFound = true;
                break;
            }
        }
        return userFound;
    }
}
