package com.taikez;

public class Juice {
    private int juiceID;
    private String juiceName;
    private int juicePrice;
    private int juiceStock;

    public Juice(int juiceID, String juiceName, int juicePrice, int juiceStock) {
        this.juiceID = juiceID;
        this.juiceName = juiceName;
        this.juicePrice = juicePrice;
        this.juiceStock = juiceStock;
    }

    public int getJuiceID() {
        return juiceID;
    }

    public void setJuiceID(int juiceID) {
        this.juiceID = juiceID;
    }

    public String getJuiceName() {
        return juiceName;
    }

    public void setJuiceName(String juiceName) {
        this.juiceName = juiceName;
    }

    public int getJuicePrice() {
        return juicePrice;
    }

    public void setJuicePrice(int juicePrice) {
        this.juicePrice = juicePrice;
    }

    public int getJuiceStock() {
        return juiceStock;
    }

    public void setJuiceStock(int juiceStock) {
        this.juiceStock = juiceStock;
    }
}
