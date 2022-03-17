import memory.MachineStatus;
import models.products.Product;
import utils.ChoiceValidator;

public class Orchestrator {


    private DisplayControl displayControl;
    private MachineStatus machineStatus;

    public Orchestrator() {
        this.displayControl = new DisplayControl();
        this.machineStatus = MachineStatus.getMachineStatus();
        runApp();
    }

    private void runApp(){
        displayControl.displayInitialMenu();

        determineFromMainMenu(displayControl.getChoiceFromInitialMenu(""));
    }

    private void determineFromPurchaseMenu(Integer choice){
        displayControl.clearScreen();

        switch(choice){
            case 1: addMoney();
            case 2: buyItem();
            case 3: endTransactions();
            default: determineFromPurchaseMenu(displayControl.getChoiceFromPurchaseMenu("Invalid Selection Try Again", machineStatus.getCurrentMoney()));

        }
    }
    private void determineFromMainMenu(Integer choice){
        displayControl.clearScreen();
        switch(choice){
            case 1: chooseProductsOption();
            case 2: choosePurchaseOption();
            case 3: killApp();
            default: determineFromMainMenu(displayControl.getChoiceFromInitialMenu(""));
        }
    }

    public void chooseProductsOption(){
        displayControl.displayInventory(machineStatus.getInventory().getCurrentInventoryMap());
        determineFromMainMenu(displayControl.getChoiceFromInitialMenu("What would you like to do?\n"));
    }

    public void choosePurchaseOption(){
        determineFromPurchaseMenu(displayControl.getChoiceFromPurchaseMenu("", machineStatus.getCurrentMoney()));
    }

    public void killApp(){
        parseChange(machineStatus.getCurrentMoney());
        machineStatus.addTransaction(Double.parseDouble(machineStatus.getCurrentMoney()), "GIVE CHANGE");
        machineStatus.initialize();
        System.exit(0);
    }

    public void addMoney(){
        Double  amount = displayControl.moneyInserted();
        machineStatus.addTransaction(amount, "FEED MONEY");
        machineStatus.addToInsertedMoney(amount);
        choosePurchaseOption();
    }

    public void buyItem(){
        String location = displayControl.whichItem();
        Product chosenProduct =machineStatus.getInventory().getProductLocationMap().get(location);
        if(!ChoiceValidator.hasInventory(chosenProduct)){
            soldOut();
        }else {
            if(ChoiceValidator.hasEnoughMoney(chosenProduct)){
                completePurchase(chosenProduct);
            }
        }
    }

    public void completePurchase(Product product){
        machineStatus.addTransaction(product.getDollarCost(), (product.getName())+ " "+product.getLocation());
        machineStatus.subtractFromInsertedMoney(product.getDollarCost());
        machineStatus.dispense(product);
        displayControl.dispensedProduct(product);
        runApp();
    }

    public void soldOut(){
        displayControl.soldOut();
        choosePurchaseOption();
    }

    public void endTransactions(){
        parseChange(machineStatus.getCurrentMoney());
        machineStatus.addTransaction(Double.parseDouble(machineStatus.getCurrentMoney()), "GIVE CHANGE");
        machineStatus.initialize();
        runApp();
    }

    public void parseChange(String dollarAmount){
        Double amount = Double.parseDouble(dollarAmount);
        while (amount > 0.24){
            displayControl.giveChange("Quarter Dispensed\n");
            amount-=0.25;
        }
        while (amount>0.09){
            displayControl.giveChange("Dime Dispensed\n");
            amount -=0.10;
        }
        while(amount > 0.04){
            displayControl.giveChange("Nickel Dispensed\n");
            amount -=0.05;
        }
        while (amount >0.01){
            displayControl.giveChange("Penny Dispensed\n");
            amount -=0.01;
        }
    }
}
