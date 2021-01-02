package com.biprom.bram.amcalapp.data.entity;

public class CustomPair {

    public boolean key;
    public String value;

    public CustomPair(boolean key, String value) {
        this.key = key;
        this.value = value;
    }

    public boolean getKey() {
        return key;
    }

    public void setKey(boolean key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
