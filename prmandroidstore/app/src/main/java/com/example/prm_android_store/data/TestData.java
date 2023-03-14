package com.example.prm_android_store.data;

public class TestData {
    private long id;
    private String name;
    private String image;
    private String description;
    private int model_year;
    private double list_price;
    private long category;
    private long brand;

    public TestData() {
    }

    public TestData(long id, String name, String image, String description, int model_year, double list_price, long category, long brand) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.model_year = model_year;
        this.list_price = list_price;
        this.category = category;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public long getCategory() {
        return category;
    }

    public void setCategory(long category) {
        this.category = category;
    }

    public long getBrand() {
        return brand;
    }

    public void setBrand(long brand) {
        this.brand = brand;
    }
}
