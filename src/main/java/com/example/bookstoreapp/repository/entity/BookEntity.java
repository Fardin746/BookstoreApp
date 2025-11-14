package com.example.bookstoreapp.repository.entity;

import com.example.bookstoreapp.model.Book;

import javax.persistence.*;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name" ,nullable = false)
    private String name;
    @Column(name = "title" ,nullable = false)
    private String title;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "price",nullable = false)
    private Double price;
    @Column(name = "type",nullable = false)
    private String type;
    @Column(name = "deleted",nullable = false)
    private Boolean deleted = false;



    public BookEntity() {
    }


    public BookEntity(Book book){
        this.name = book.getName();
        this.title = book.getTitle();
        this.description = book.getDescription();
        this.price = book.getPrice();
        this.type = book.getType();
    }

    public Book toBook(){
        return new Book(id,name,title,description,price,type);
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
