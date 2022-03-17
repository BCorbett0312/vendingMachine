package models;

import models.products.*;
import utils.FileReadWrite;

import java.util.*;

public class Inventory {

    private static Inventory inventory = new Inventory();

    List<Product> productList;
    Map<Product, Integer> productInventoryMap;
    Map<String, Product> productLocationMap;

    private Inventory(){
        initialize();
    }

    public static Inventory getInventory(){
        return inventory;
    }

    private void initialize(){
        loadProductList();
        loadProductInventoryMap();
        loadProductLocationMap();
    }

    public Product dispenseItem(Product product){
        this.productInventoryMap.put(product, (productInventoryMap.get(product)-1));
        return product;
    }

    public Map<Product, Integer> getCurrentInventoryMap(){
        return this.productInventoryMap;
    }

    public void loadProductList(){
        productList = FileReadWrite.readInventoryFromFile();
    }

    public List<Product> getProductList(){
        List<Product> availableProducts = new ArrayList<>();
        for (Product p: productList) {
            if(productInventoryMap.get(p) >0){
                availableProducts.add(p);
            }
        }
        return availableProducts;
    }

    private void loadProductInventoryMap(){
        productInventoryMap = new TreeMap<>(Collections.reverseOrder());
        for (Product p: productList) {
            productInventoryMap.put(p, 5);
        }
    }

    public Map<String, Product> getProductLocationMap() {
        return productLocationMap;
    }

    private void loadProductLocationMap(){
        productLocationMap = new HashMap<>();
        for (Product p: productList) {
            productLocationMap.put(p.getLocation(), p);
        }
    }





}
