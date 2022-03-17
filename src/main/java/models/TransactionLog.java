package models;

import lombok.ToString;
import utils.FileReadWrite;
import utils.StringUtil;

import java.sql.Timestamp;

public class TransactionLog {


    Double amount;
    String transactionType;
    Timestamp timestamp;
    String amountBefore;
    String amountAfter;

    public TransactionLog(Transaction transaction, String amountBefore, String amountAfter){
        this.amount=transaction.amount;
        this.transactionType=transaction.transactionType;
        this.timestamp= transaction.getTimestamp();
        this.amountBefore=amountBefore;
        this.amountAfter=amountAfter;
        FileReadWrite.writeToFile(this);
    }

    @Override
    public String toString(){
        return StringUtil.pad(timestamp.toString(), 27) + " " +
                StringUtil.pad(transactionType, 20) + " $" +
                StringUtil.pad(amountBefore, 10) + " $" +
                StringUtil.pad(amountAfter, 10);
    }

}
