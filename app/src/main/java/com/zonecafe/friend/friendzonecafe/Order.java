package com.zonecafe.friend.friendzonecafe;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private String name;
    private String flavor;
    private int cuantity;
    private Coffee coffee;
    private Waffles waffles;
    private int price;

    public Order(){}

    public Order(int id, String name, String flavor,int cuantity,int price) {
        this.id = id;
        this.name = name;
        this.cuantity = cuantity;
        this.flavor = flavor;
        this.price = price;
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

    public int getCuantity() {
        return cuantity;
    }

    public void setCuantity(int cuantity) {
        this.cuantity = cuantity;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffees) {
        this.coffee = coffee;
    }

    public Waffles getWaffle() {
        return waffles;
    }

    public void setWaffle(Waffles waffles) {
        this.waffles = waffles;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
