package Controller;

import Model.Sale;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SaleController {
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

    private String formatDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
