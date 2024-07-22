package org.example;

public class Admin {
    private int id;
    private String password;

    public Admin(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public static boolean login(int id, String password){
        boolean userFound = false;
        for (Admin admin : Main.admins){
            if (admin.getId() == id && admin.getPassword().equals(password)){
                userFound = true;
                break;
            }
        }
        return userFound;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
