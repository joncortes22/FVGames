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

    public Item(Item item) {
        this.id = item.getId();
        this.category = item.getCategory();
        this.name = item.getName();
        this.availability = item.getAvailability();
        this.unitPrice = item.getUnitPrice();
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

    public static String[] getAllProductNamesByCategory(String category){
        ArrayList<String> itemList = new ArrayList<String>();
        for (Item item : Main.inventory){
            if (item.getAvailability()>0 && item.getCategory().equals(category)){
                itemList.add(item.getName());
            }
        }
        String[] responseArray = itemList.toArray(new String[0]);
        Arrays.sort(responseArray);
        return responseArray;
    }

    public static ArrayList<Item> getProductsForPackage(ArrayList<Item> inventory, int packageCount){
        ArrayList<Item> itemList = new ArrayList<Item>();
        for (Item item : inventory){
            if (item.getAvailability() + packageCount <= 5 && item.getAvailability() > 0 && packageCount == 0){
                itemList.add(new Item(item));
            } else if (item.getAvailability() > 0) {
                Item temp = new Item(item);
                int remainingCapacity = 5 - packageCount;
                temp.setAvailability(Math.min(remainingCapacity, item.getAvailability()));
                item.setAvailability(Math.min(remainingCapacity, item.getAvailability()));
                itemList.add(temp);
            }
        }
        inventory = itemList;
        return itemList;
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


    public static String[] getAvailableCategories(){
        ArrayList<String> categoryList = new ArrayList<String>();
        for (Item item : Main.inventory){
            boolean categoryFound = false;
            for (String category : categoryList){
                if (item.getCategory().equals(category)){
                    categoryFound = true;
                    break;
                }
            }
            if (!categoryFound){
                categoryList.add(item.getCategory());
            }
        }
        String[] categoryArray = categoryList.toArray(new String[0]);
        Arrays.sort(categoryArray);
        String[] copyCategoryArray = new String[categoryArray.length + 1];
        copyCategoryArray[0] = "All";
        System.arraycopy(categoryArray, 0, copyCategoryArray, 1, categoryArray.length);
        return copyCategoryArray;
    }

    public static void setNewAvailability(ArrayList<Item> inventory,  String name, int count){
        for (Item item : inventory){
            if (item.getName().equals(name)){
                item.setAvailability(item.getAvailability()-count);
                break;
            }
        }

    }

    public static boolean validateNameExistance(String name){
        boolean found = false;
        for (Item item : Main.inventory){
            if (item.getName().equalsIgnoreCase(name)){
                found = true;
                break;
            }
        }
        return found;
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

    public static int getNewId(){
        return Main.inventory.get(Main.inventory.size() - 1).getId() + 1;
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
