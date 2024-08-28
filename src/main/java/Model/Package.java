package Model;

public class Package {
    private int id;
    private String items;
    private int discount;

    public Package(int id, String items, int discount) {
        this.id = id;
        this.items = items;
        this.discount = discount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
