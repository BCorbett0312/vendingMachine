package memory;

import models.Inventory;
import models.Transaction;
import models.TransactionLog;
import models.products.Product;
import utils.FileReadWrite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MachineStatus {

    List<Transaction> transactionList;
    Inventory inventory;
    Double currentInsertedMoney;

    private static MachineStatus machineStatus = new MachineStatus();

    private MachineStatus(){
        transactionList = new ArrayList<>();
        inventory = Inventory.getInventory();
        this.currentInsertedMoney = 0.00;
    }

    public void initialize(){
        this.currentInsertedMoney = 0.00;
    }

    public static MachineStatus getMachineStatus(){
        return machineStatus;
    }

    public void addTransaction(Double amount, String type){
        Transaction transaction = new Transaction(amount, type);
        new TransactionLog(transaction, getCurrentMoney(), getMoneyAfter(transaction));
        transactionList.add(transaction);
    }

    public void addToInsertedMoney(Double toAdd){
        currentInsertedMoney += toAdd;
    }

    public void subtractFromInsertedMoney(Double toSubtract){
        currentInsertedMoney -= toSubtract;
    }

    public Product dispense(Product product){
        return inventory.dispenseItem(product);
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public List<Product> getProducts(){
        return inventory.getProductList();
    }

    public String getCurrentMoney(){
        return String.format("%.2f", this.currentInsertedMoney);
    }

    private String getMoneyAfter(Transaction transaction){
        Double response = 0.0;
        if(transaction.getTransactionType().equals("FEED MONEY")){
            response= currentInsertedMoney+transaction.getAmount();
        }else if(transaction.getTransactionType().equals("GIVE CHANGE")){
            response= 0.00;
        } else{
            response = currentInsertedMoney-transaction.getAmount();
        }
        return String.format("%.2f", response);
    }
}
