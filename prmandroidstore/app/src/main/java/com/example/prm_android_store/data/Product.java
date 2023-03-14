package com.example.prm_android_store.data;

public class Product {
    private long id;
    private String name;
    private String description;
    private String image;
    private int model_year;
    private double list_price;
    private long category_id;
    private String category_name;
    private String brand;

    public Product() {
    }

    public Product(long id, String name, String description, String image, int model_year, double list_price, long category_id, String category_name, String brand) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.model_year = model_year;
        this.list_price = list_price;
        this.category_id = category_id;
        this.category_name = category_name;
        this.brand = brand;
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

    public long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(long category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}

