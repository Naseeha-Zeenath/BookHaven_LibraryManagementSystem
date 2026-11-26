package model;

import java.time.Year;

public class Book {

    private Integer id;
    private String title;
    private String isbn;
    private Integer year;
    private Integer quantity;
    private Integer availableQty;

    private Integer authorId;

    public Book(Integer id, String title, String isbn, Integer year, Integer quantity, Integer availableQty, Integer authorId, Integer categoryId) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.quantity = quantity;
        this.availableQty = availableQty;
        this.authorId = authorId;
        this.categoryId = categoryId;
    }

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAvailableQty() {
        return availableQty;
    }

    public void setAvailableQty(Integer availableQty) {
        this.availableQty = availableQty;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
    private Integer categoryId;
}
