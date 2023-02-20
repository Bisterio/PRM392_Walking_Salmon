package com.example.prm_android_store.data;

public class Product {
    private long id;
    private String name;
    private String description;
    private String image;
    private int model_year;
    private double list_price;
    private Brand brand;
    private Category category;
    private String created_at;
    private String updated_at;

    public Product() {
    }

    public Product(long id, String name, String description, String image, int model_year, double list_price, Brand brand, Category category, String created_at, String updated_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.model_year = model_year;
        this.list_price = list_price;
        this.brand = brand;
        this.category = category;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getModel_year() {
        return model_year;
    }

    public void setModel_year(int model_year) {
        this.model_year = model_year;
    }

    public double getList_price() {
        return list_price;
    }

    public void setList_price(double list_price) {
        this.list_price = list_price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}

