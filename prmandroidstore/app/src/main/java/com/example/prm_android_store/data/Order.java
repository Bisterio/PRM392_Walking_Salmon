package com.example.prm_android_store.data;

public class Order {
    private long id;
    private int status;
    private long totalQuantity;
    private double totalPrice;
    private String order_date;
    private String required_date;
    private String shipped_date;
    private Customer customer;

    public Order() {
    }

    public Order(long id, int status, long totalQuantity, double totalPrice, String order_date, String required_date, String shipped_date, Customer customer) {
        this.id = id;
        this.status = status;
        this.totalQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.order_date = order_date;
        this.required_date = required_date;
        this.shipped_date = shipped_date;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }

    public String getRequired_date() {
        return required_date;
    }

    public void setRequired_date(String required_date) {
        this.required_date = required_date;
    }

    public String getShipped_date() {
        return shipped_date;
    }

    public void setShipped_date(String shipped_date) {
        this.shipped_date = shipped_date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

