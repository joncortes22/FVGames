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
    }
    public static ArrayList<Sale> filterSales(ArrayList<Sale> sales, String customerId, String fromDateString, String toDateString) {
        ArrayList<Sale> filteredSales = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date fromDate = sdf.parse(fromDateString);
            Date toDate = sdf.parse(toDateString);

            for (Sale sale : sales) {
                if (
                        (customerId.isEmpty() || String.valueOf(sale.getCustomerId()).equals(customerId)) &&
                                (fromDate.before(sale.getDate()) || fromDate.equals(sale.getDate())) &&
                                (toDate.after(sale.getDate()) || toDate.equals(sale.getDate()))
                ) {
                    filteredSales.add(sale);
                }
            }
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Formato de fecha inválido.");
        }

        return filteredSales;
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
