package com.ianchang.chipure.entity;

public class ShoppingCartData {
    public Integer id;
    public Integer productID;
    public String productName;
    public String productImg;
    public Double price;
    public Integer num;
    public Integer maxNum;
    public boolean check;

    public ShoppingCartData(Integer id, Integer productID, String productName, String productImg, Double price, Integer num){
        this.id = id;
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.num = num;
        this.maxNum = 5;
        this.check = false;
    }
}
