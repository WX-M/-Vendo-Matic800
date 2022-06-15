package com.techelevator;

public class Product {


    private String slotId;
    private String name;
    private double purchasePrice;
    private String type;
    private int quantity;

    public Product(){}

    public Product(String slotId, String name, double purchasePrice, String type, int quantity) {
        this.slotId = slotId;
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.type = type;
        this.quantity = quantity;
    }

    public String getSlotId() {
        return slotId;
    }

    public String getName() {
        return name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
