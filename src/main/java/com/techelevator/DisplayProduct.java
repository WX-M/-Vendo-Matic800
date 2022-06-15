package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DisplayProduct {

    private List<Product> productList = new ArrayList<>();


    public List<Product> getProductList() {
        return productList;
    }

    public void stockMachine(){

        // Create a File object using the path
        File file = new File("vendingmachine.csv");
        try(Scanner fileInput = new Scanner(file)){
            while (fileInput.hasNext()){

                // Read the next line into 'lineOfText'
                String lineOfText = fileInput.nextLine();
                String [] productProperties = lineOfText.split("\\|");
                double productPrice = Double.parseDouble(productProperties[2]);

                Product product = new Product(productProperties[0], productProperties[1], productPrice, productProperties[3], 5);
                productList.add(product);

            }


        } catch (FileNotFoundException e){
            // Could not find the file at the specified path.
            System.out.println("The file was not found: " + file.getAbsolutePath());

        }

        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).getSlotId()
                    + " | " + productList.get(i).getName()
                    + " | " + productList.get(i).getPurchasePrice()
                    + " | " + productList.get(i).getType()
                    + " | " + productList.get(i).getQuantity());
        }

    }

    public void display(){
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i).getSlotId()
                    + " | " + productList.get(i).getName()
                    + " | " + productList.get(i).getPurchasePrice()
                    + " | " + productList.get(i).getType()
                    + " | " + productList.get(i).getQuantity());
        }

    }

    public Product getProductBySlotID(String slotID) {
        for (Product product : this.getProductList()) {
            if (slotID.equalsIgnoreCase(product.getSlotId())) {
                return product;
            }
        }
        return null;
    }


    public String getSound(String  type) {
        String sound = "";
        for (int i = 0; i < productList.size(); i++) {
            if (type.equals("Gum")) {
                sound = "Chew Chew, Yum!";
            } else if (type.equals("Drink")) {
                sound = "Glug Glug, Yum!";
            } else if (type.equals("Candy")) {
                sound = "Munch Munch, Yum!";
            } else if (type.equals("Chip")) {
                sound = "Crunch Crunch, Yum!";
            }
        }


        return sound;
    }





}
