package com.example.bookstore.model;
import jakarta.persistence.*;

@Entity
public class Book {

    @Id @GeneratedValue
    private Long id;

    private String title;
    private String category;
    private int noOfPages;

    public Book(){};

    public Long getId(){return id;}
    public void setId(Long id){ this.id = id;}

    public String getTitle(){return title;}
    public void setTitle(String title){ this.title = title;}

    public String getCategory(){return category;}
    public void setCategory(String catergory){this.category = catergory;}

    public int getNoOfPages(){return noOfPages;}
    public void setNoOfPages(int noOfPages){this.noOfPages = noOfPages;}

}

