package Controller;

import Model.Admin;
import Model.AdminModelDB;
import Model.Item;
import Model.ItemModelDB;
import Program.Main;

import java.util.ArrayList;
import java.util.Arrays;

public class ItemController {
    private static ItemModelDB imdb = new ItemModelDB();
    static ArrayList<Item> inventory = imdb.getAllStock();

    //Logic & Methods

    public static void addNewProduct(String category, String name, int availability, int unitPrice){
        Item newItem = new Item(category, name, availability, unitPrice);
        inventory.add(newItem);
        imdb.insertNewProduct(category, name, availability, unitPrice);
        inventory = imdb.getAllStock();
    }

    public Item[] getProductsByCategory(String category){
        ArrayList<Item> itemList = new ArrayList<Item>();
        for (Item item : inventory){
            if (item.getCategory().equals(category)){
                itemList.add(item);
            }
        }
        return itemList.toArray(new Item[0]);
    }


    public static int getPriceByName(String name){
        int unitPrice = 0;
        for (Item item : inventory){
            if (item.getName().equals(name)){
                unitPrice = item.getUnitPrice();
            }
        }
        return unitPrice;
    }

    public static void editItem(int id, String name, int availability, int unitPrice){
        for (Item item : inventory){
            if (item.getId() == id){
                item.setName(name);
                item.setAvailability(availability);
                item.setUnitPrice(unitPrice);
                imdb.updateProduct(id, name, availability, unitPrice);
                break;
            }
        }
    }


    public static Item getItemSelected(String name){
        Item returnItem = null;
        for (Item item : inventory){
            if (item.getName().equals(name)){
                returnItem = new Item(item);
                break;
            }
        }
        return returnItem;
    }


    public static String[] getAllAvailableProductNames(){
        ArrayList<String> itemList = new ArrayList<String>();
        for (Item item : inventory){
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
        for (Item item : inventory){
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
        for (Item item : inventory){
            if (item.getName().equals(name)){
                itemSelected = item;
            }
        }
        return itemSelected;
    }

    public static String[] getAvailableCategories(){
        ArrayList<String> categoryList = new ArrayList<String>();
        for (Item item : inventory){
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
        for (Item item : inventory){
            if (item.getName().equalsIgnoreCase(name)){
                found = true;
                break;
            }
        }
        return found;
    }

    public static int getProductAvailability(String name){
        int availability = 0;
        for (Item item : inventory){
            if (item.getName().equals(name)){
                availability = item.getAvailability();
                break;
            }
        }
        return availability;
    }
}
