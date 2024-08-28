package Controller;

import Model.*;
import Model.Package;

import java.util.ArrayList;

public class PackageController {
    private static PackageModelDB pmdb = new PackageModelDB();
    static ArrayList<Package> packages = pmdb.getAllPackages();

    public static void addNewPackage(String items, int discount){
        pmdb.insertNewPackage(items, discount);
        packages = pmdb.getAllPackages();
    }

    public static boolean findPackage(int id){
        boolean found = false;
        for (Package pack : packages){
            if (pack.getId() == id){
                found = true;
                break;
            }
        }
        return found;
    }

    public static Package getPackage(int id){
        Package returnPack = new Package();
        for (Package pack : packages){
            if (pack.getId() == id){
                returnPack = pack;
                break;
            }
        }
        return returnPack;
    }


}
