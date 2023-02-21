package com.example.prm_android_store.data;

public class CartItem {
    private Product product;
    private long quantity;

    public CartItem() {
    }

    public CartItem(Product product, long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}

