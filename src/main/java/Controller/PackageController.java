package Controller;

import Model.Client;
import Model.ClientModelDB;
import Model.Package;
import Model.PackageModelDB;

import java.util.ArrayList;

public class PackageController {
    private static PackageModelDB pmdb = new PackageModelDB();
    static ArrayList<Package> packages = pmdb.getAllPackages();

    public static void addNewPackage(String items, int discount){
        pmdb.insertNewPackage(items, discount);
        packages = pmdb.getAllPackages();
    }


}
