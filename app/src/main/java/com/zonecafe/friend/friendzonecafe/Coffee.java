package com.zonecafe.friend.friendzonecafe;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Coffee implements Serializable {

    private int id;
    private String name;
    private int price;
    private String description;
    private int image;
    private int cuantity;

    public Coffee(){}

    public Coffee(int id, String name, int price, String description, int image, int cuantity){
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.image = image;
        this.cuantity = cuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public ArrayList<String> DescItemCoffee(){
        return new ArrayList<String>(){{
            add("Cafe negro");
            add("Cafe latte, mezcla de cafe negro con un poco de leche");
            add("Cafe mocha, mezcla de cafe negro con leche");
            add("Capucchino, cafe negro con espuma encima que le da un sabor dulce");
        }};
    }

    public ArrayList<String> kindCoffee(){
        return new ArrayList<String>(){{
            add("Café Negro");
            add("Café Latte");
            add("Café Mocha");
            add("Capucchino");
        }};
    }

    public int getCuantity() {
        return cuantity;
    }

    public void setCuantity(int cuantity) {
        this.cuantity = cuantity;
    }
}
