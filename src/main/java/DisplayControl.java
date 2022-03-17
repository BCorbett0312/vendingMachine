import models.Transaction;
import models.products.Product;
import utils.MachineConsole;

import java.util.List;
import java.util.Map;

public class DisplayControl {

    MachineConsole machineConsole;

    private String initialMenu = "1) Products\n2) Purchase\n3) Exit";
    private String chooseFrom = "Please choose from the following options:\n";
    private String purchaseMenu = "1) Insert Money\n2) Select Product\n3) Finish Transaction";
    private String howMuchToInsert = "How much would you like to insert?\n$";
    private String availableProducts = "The available products are:";
    private String selectionPrompt = "\nSelection: ";
    private String purchaseMenuHeader= "Purchase Menu\n";
    private String currentBalance = "\n\nCurrent Money Provided: ";
    private String whichItemToBuy = "\n Which item would you like?";
    private String soldOut = "This item is sold out. Choose another item\n";


    public DisplayControl() {
        machineConsole= MachineConsole.getInstance();
    }

    public void displayInitialMenu(){
        machineConsole.println("Hello, Welcome to the Vending Machine!\n" + chooseFrom);
    }

    public Integer getChoiceFromInitialMenu(String toAdd){
        machineConsole.print(toAdd);
        return machineConsole.getIntegerInput(initialMenu + selectionPrompt);
    }

    public Integer getChoiceFromPurchaseMenu(String toAdd, String balance){
        machineConsole.print(toAdd);
        return machineConsole.getIntegerInput(purchaseMenu + currentBalance+ balance+ selectionPrompt);
    }

    public void displayTransactions(List<Transaction> transactionList){
        machineConsole.print(transactionList.toString());
    }

    public void displayInventory(Map<Product, Integer> inventory){
        for(Map.Entry entry: inventory.entrySet()){
            machineConsole.println(entry.getKey().toString()+ "Available: "+ entry.getValue()+"\n");
        }
    }
    public void clearScreen(){
        machineConsole.println("\n\n");
    }

    public void displayProducts(List<Product> products){
        machineConsole.println("");
        for(Product p: products){
            machineConsole.println(p.toString()+"\n");
        }
    }

    public void giveChange(String amountDispensed){
        machineConsole.println(amountDispensed);

    }

    public void soldOut(){
        machineConsole.println(soldOut);
    }


    public Double moneyInserted(){
        Integer toReturn = machineConsole.getDollarsInput(howMuchToInsert);
        return new Double(toReturn);
    }

    public String whichItem(){
        String toReturn = machineConsole.getLocationInput(whichItemToBuy+selectionPrompt);
        return toReturn.toUpperCase();
    }

    public void dispensedProduct(Product product){
        machineConsole.println(product.getPrintStatement());
    }

}
