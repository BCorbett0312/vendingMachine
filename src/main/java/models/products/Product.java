package models.products;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import utils.StringUtil;

@Getter
@Setter
@Data
public class Product implements Comparable<Product> {

    String location;
    String name;
    String cost;
    String type;
    String printStatement;
    Double dollarCost;

    public Product(String location, String name, String cost, String type) {
        this.location = location;
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.printStatement = getPrintStatement(type);
        this.dollarCost = Double.parseDouble(cost);
    }

    private String getPrintStatement(String type){
        switch (type.toLowerCase()){
            case "drink":
                return "Glug Glug, Yum!\n";
            case "gum":
                return "Chew Chew, Yum!\n";
            case "chip":
                return "Crunch Crunch, Yum!\n";
            case "candy":
                return "Munch Munch, Yum!\n";
            default:
                return "";
        }
    }

    @Override
    public String toString() {
        return StringUtil.pad(location, 5)+ " " + StringUtil.pad(name, 20) + " " + StringUtil.pad(cost, 7);
    }

    @Override
    public int compareTo(Product o) {
        int locationToCompare = o.location.compareTo(this.location);

        return locationToCompare;
    }
}
