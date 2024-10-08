package Controller;

import Model.Client;
import Model.ClientModelDB;
import Model.Sale;
import Model.SaleModelDB;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SaleController {
    private static SaleModelDB smdb = new SaleModelDB();
    static ArrayList<Sale> sales = smdb.getAllSales();

    public static void newSale(int customerId, String items, Date date, float total){
        Sale newSale = new Sale(customerId, items, date, total);
        sales.add(newSale);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);
        smdb.registerSale(customerId, items, formattedDate, total);
        sales = smdb.getAllSales();
    }


    public static ArrayList<Sale> filterSales(int customerId, String fromDateString, String toDateString, String filterType) {
        ArrayList<Sale> filteredSales = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        switch (filterType){
            case "customerId":
                filteredSales = smdb.getSalesByCustomer(customerId);
                break;
            case "date":
                try {
                    filteredSales = smdb.getSalesByIntervalTime(fromDateString, toDateString);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Date Format.");
                    return filteredSales;
                }
                break;
            default:
        }
        return filteredSales;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
