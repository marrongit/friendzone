package com.zonecafe.friend.friendzonecafe;

import java.io.Serializable;

public class MenuPrincipal implements Serializable {

    private int id;
    private String title;
    private String description;
    private int image;

    public MenuPrincipal(){}

    public MenuPrincipal(int id, String title, String description, int image){
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public static MenuPrincipal[] ITEMS = {
                new MenuPrincipal(0,"cafe", "", R.drawable.coffee),
                new MenuPrincipal(1,"waffles", "", R.drawable.waffles)
    };

    public static MenuPrincipal getItem(int id){
        for (MenuPrincipal item : ITEMS) {
            if (item.getId() == id) return item;
        }
        return null;
    }
}
