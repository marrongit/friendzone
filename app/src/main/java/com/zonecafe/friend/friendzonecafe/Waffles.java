package com.zonecafe.friend.friendzonecafe;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Waffles implements Serializable {
    private int flavor;
    private int cuantity;

    public Waffles(){}

    public Waffles(int flavor, int cuantity){
        this.flavor = flavor;
        this.cuantity = cuantity;
    }

    public int getFlavor() {
        return flavor;
    }

    public void setFlavor(int flavor) {
        this.flavor = flavor;
    }

    public int getCuantity() {
        return cuantity;
    }

    public void setCuantity(int cuantity) {
        this.cuantity = cuantity;
    }

    public String[] getFlavors = {"Cajeta","Lechera","Choco Avellana","Maple"};
}
