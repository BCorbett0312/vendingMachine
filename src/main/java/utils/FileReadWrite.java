package utils;

import models.Transaction;
import models.TransactionLog;
import models.products.Product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileReadWrite {



    public static List<Product> readInventoryFromFile(){
        List<Product> productList = new ArrayList<>();
        Path path = Paths.get("src/main/resources/inventory.txt");
        System.out.println(path);

        try(BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII)){
            String line = br.readLine();
            while (line != null){
                String[] attributes = line.split("\\|");

                Product product = createProduct(attributes);
                productList.add(product);
                line = br.readLine();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return productList;
    }

    private static Product createProduct(String[] metadata){
        String location = metadata[0];
        String name = metadata[1];
        String cost = metadata[2];
        String type = metadata[3];
        return new Product(location, name, cost, type);
    }


    public static void writeToFile(TransactionLog transaction){
        try(FileWriter fw = new FileWriter("src/main/resources/logs.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw)){
            pw.println(transaction.toString());
        } catch (Exception e){
        }
    }



}
