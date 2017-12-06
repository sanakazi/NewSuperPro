package com.superapp.fragment;

/**
 * Created by SanaKazi on 11/16/2017.
 */

public class Model {
    public int id;
    public String fruit,color;


    public Model(int id, String fruit, String color) {
        this.id = id;
        this.fruit = fruit;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
