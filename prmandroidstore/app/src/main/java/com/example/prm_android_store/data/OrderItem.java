package com.example.prm_android_store.data;

public class OrderItem {
    private long id;
    private double list_price;
    private long quantity;
    private Product product;
    private Order order;

    public OrderItem() {
    }

    public OrderItem(long id, double list_price, long quantity, Product product, Order order) {
        this.id = id;
        this.list_price = list_price;
        this.quantity = quantity;
        this.product = product;
        this.order = order;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getList_price() {
        return list_price;
    }

    public void setList_price(double list_price) {
        this.list_price = list_price;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

