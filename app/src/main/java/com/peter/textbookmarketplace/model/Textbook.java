package com.peter.textbookmarketplace.model;

public class Textbook {

    private String title;
    private String sellerName;
    private int copies;
    private double price;
    private String bankInfo;
    private String bankingInfo;

    public Textbook(String title, String sellerName, int copies, double price, String bankInfo) {
        this.title = title;
        this.sellerName = sellerName;
        this.copies = copies;
        this.price = price;
        this.bankInfo = bankInfo;
        this.bankingInfo = bankingInfo;
    }

    public String getTitle() { return title; }
    public String getSellerName() { return sellerName; }
    public int getCopies() { return copies; }
    public double getPrice() { return price; }
    public String getBankInfo() { return bankInfo; }
    public String getBankingInfo() {
        return bankingInfo;
    }
}