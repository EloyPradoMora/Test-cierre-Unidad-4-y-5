package com.example.demo;

import java.time.LocalDateTime;

public class Producto {
    private String code;
    private String name;
    private int stock;
    private int price;
    private String providerRUT;
    private String providerEmail;

    public Producto() {
    }

    boolean giveName(Object name){
        return false;
    }

    void createCode(LocalDateTime timeOfCreation){
        return;
    }

    boolean giveStock(Object stock){
        return false;
    }

    boolean givePrice(Object price){
        return false;
    }

    boolean ingresarRUTProveedor(Object providerRUT){
        return false;
    }

    boolean insertProviderEmail(Object providerEmail){
        return false;
    }


    public String getCode() {
        return code;
    }
}