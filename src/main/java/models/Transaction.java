package models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;


@Getter
@Setter
@ToString
public class Transaction {

    Double amount;
    String transactionType;
    Timestamp timestamp;

    public Transaction(Double amount, String transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
        this.timestamp = new Timestamp(System.currentTimeMillis());
    }


}

