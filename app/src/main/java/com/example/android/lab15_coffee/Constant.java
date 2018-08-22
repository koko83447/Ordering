package com.example.android.lab15_coffee;

import java.util.ArrayList;
import java.util.List;

public final class Constant {

    public static List<Burger> getProductList() {
        List<Burger> productList = new ArrayList<>();
        productList.add(new Burger(1,"純素貝果",50,"介紹1","burger_menu_1","menu_1","burger_1"));
        productList.add(new Burger(2,"沙拉船",40,"介紹2","burger_menu_2","menu_2","burger_2"));
        productList.add(new Burger(3,"乳酪燒餅",55,"介紹3","burger_menu_3","menu_3","burger_3"));
        productList.add(new Burger(4,"鬆餅堡",45,"介紹4","burger_menu_4","menu_4","burger_4"));
        productList.add(new Burger(5,"火腿貝果",45,"介紹5","burger_menu_5","menu_5","burger_5"));
        productList.add(new Burger(6,"墨西哥捲餅",55,"介紹6","burger_menu_6","menu_6","burger_6"));
        productList.add(new Burger(7,"黃金潛艇堡",65,"介紹7","burger_menu_7","menu_7","burger_7"));   
        return productList;
    }
}
