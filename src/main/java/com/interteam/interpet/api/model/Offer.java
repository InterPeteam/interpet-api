package com.interteam.interpet.api.model;

public class Offer {
    private String id;
    private String userId;
    private String animal;
    private float price;
    private String start;
    private String end;
    private String text;

    public Offer(String id, String userId, String animal, float price, String start, String end, String text) {
        this.id = id;
        this.userId = userId;
        this.animal = animal;
        this.price = price;
        this.start = start;
        this.end = end;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getAnimal() {
        return animal;
    }

    public float getPrice() {
        return price;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public String getText() {
        return text;
    }
}
