package com.example.api.model;

import com.google.gson.annotations.SerializedName;

public class Phone {

    private Integer id;

    @SerializedName("phone_name")
    private String phoneName;

    @SerializedName("price")
    private Integer price;

    public Phone(Integer id, String phoneName, Integer price) {
        this.id = id;
        this.phoneName = phoneName;
        this.price = price;
    }

    public Phone() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
