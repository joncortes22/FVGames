package Model;

import java.util.Date;

public class Sale {
    private int customerId;
    private String items;
    private Date date;
    private float total;

    public Sale(int customerId, String items, Date date, float total) {
        this.customerId = customerId;
        this.items = items;
        this.date = date;
        this.total = total;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }


    public float getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String toString() {
        // Formato de fecha: yyyy-MM-dd
        return customerId + ", " + items + ", " + date.toString() + ", $" + total;
    }
}
