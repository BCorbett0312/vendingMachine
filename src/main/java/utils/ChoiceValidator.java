package utils;

import memory.MachineStatus;
import models.Inventory;
import models.products.Product;


public class ChoiceValidator {

    private static Inventory inventory = Inventory.getInventory();
    private static MachineStatus machineStatus = MachineStatus.getMachineStatus();

    public static boolean isValidLocation(String location){

        if(inventory.getProductLocationMap().containsKey(location.toUpperCase())){
            System.out.println("says is valid");
            return true;
        }else {
            return false;
        }
    }

    public static boolean isValidCurrencyAmount(Integer amount){
        if(amount == 1 || amount == 2 || amount == 5 || amount == 10 || amount ==0){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isValidMenuChoice(Integer choice){
        if(choice>3 || choice <0){
            return false;
        }else{
            return true;
        }
    }

    public static boolean hasInventory(Product product){
        if(inventory.getCurrentInventoryMap().get(product)>0){
            return true;
        }else{
            return false;
        }

    }

    public static boolean hasEnoughMoney(Product item){
        if(Double.parseDouble(machineStatus.getCurrentMoney())> item.getDollarCost()){
            return true;
        }else{
            return false;
        }
    }


}
