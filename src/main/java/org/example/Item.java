package org.example;

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

    public Item[] getProductsByCategory(String category){
        ArrayList<Item> itemList = new ArrayList<Item>();
        for (Item item : Main.inventory){
            if (item.getCategory().equals(category)){
                itemList.add(item);
            }
        }
        return itemList.toArray(new Item[0]);
    }

    public static String[] getAllAvailableProductNames(){
        ArrayList<String> itemList = new ArrayList<String>();
        for (Item item : Main.inventory){
            if (item.getAvailability()>0){
                itemList.add(item.getName());
            }
        }
        String[] responseArray = itemList.toArray(new String[0]);
        Arrays.sort(responseArray);
        return responseArray;
    }

    public static Item extractProductSelected(String name){
        ArrayList<Item> itemList = new ArrayList<Item>();
        Item itemSelected = null;
        for (Item item : Main.inventory){
            if (item.getName().equals(name)){
                itemSelected = item;
            }
        }
        return itemSelected;
    }


    public static void setNewAvailability(String name, int count){
        for (Item item : Main.inventory){
            if (item.getName().equals(name)){
                item.setAvailability(item.getAvailability()-count);
                break;
            }
        }

    }

    public static int getProductAvailability(String name){
        int availability = 0;
        for (Item item : Main.inventory){
            if (item.getName().equals(name)){
                availability = item.getAvailability();
                break;
            }
        }
        return availability;
    }

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

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }
}
