package com.example.bookstoreapp.model;


public class Book {

    private int id;
    private String name;
    private String title;
    private String description;
    private double price;
    private String type;


    public  Book() {}

    public Book(int id, String name, String title, String description, double price, String type) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.description = description;
        this.price = price;
        this.type = type;
    }

    public int getId() { return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
