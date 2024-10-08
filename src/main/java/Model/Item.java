package Model;

import Program.Main;

import java.util.ArrayList;
import java.util.Arrays;

public class Item {
    private int id;
    private String category;
    private String name;
    private int availability;
    private int unitPrice;

    public Item(int id, String category, String name, int availability, int unitPrice) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.availability = availability;
        this.unitPrice = unitPrice;
    }

    public Item(String category, String name, int availability, int unitPrice) {
        this.category = category;
        this.name = name;
        this.availability = availability;
        this.unitPrice = unitPrice;
    }

    public Item(Item item) {
        this.id = item.getId();
        this.category = item.getCategory();
        this.name = item.getName();
        this.availability = item.getAvailability();
        this.unitPrice = item.getUnitPrice();
    }

    //Getters & Setters


    public int getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public int getAvailability() {
        return availability;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
}
