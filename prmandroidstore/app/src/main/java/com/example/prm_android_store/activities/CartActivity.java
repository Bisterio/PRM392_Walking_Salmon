package com.example.prm_android_store.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prm_android_store.R;
import com.example.prm_android_store.adapters.CartListAdapter;
import com.example.prm_android_store.adapters.ProductListAdapter;
import com.example.prm_android_store.data.Brand;
import com.example.prm_android_store.data.CartItem;
import com.example.prm_android_store.data.Category;
import com.example.prm_android_store.data.Product;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    // Init view
    private ImageView backArrow;
    private RecyclerView cartListRecyclerView;
    private TextView totalQuantity;
    private TextView totalPrice;

    // Init list
    private ArrayList<CartItem> cartList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Set up view and listener
        setupUI();
        setupListener();

        // Set up cart list
        initCartList();

        // Handle cart information
        totalQuantity.setText("Total prices (" + calculatedCartQuantity() + " products):");
        totalPrice.setText(String.format("%,.0f", calculatedCartPrice()) + "₫");

        // Build recycler view
        buildCartRecyclerView();
    }

    private int calculatedCartQuantity(){
        int quantity = 0;
        for(CartItem item : cartList){
            quantity += item.getQuantity();
        }
        return quantity;
    }

    private float calculatedCartPrice(){
        float price = 0;
        for(CartItem item : cartList){
            price += item.getQuantity() * item.getProduct().getList_price();
        }
        return price;
    }

    private void initCartList(){
        cartList.add(new CartItem(new Product(1, "Iphone 14 Pro Max", "iPhone 14 has the same superspeedy chip that’s in iPhone 13 Pro. A15 Bionic, with a 5‑core GPU, powers all the latest features and makes graphically intense games and AR apps feel ultrafluid. An updated internal design delivers better thermal efficiency, so you can stay in the action longer.", "https://cdn.tgdd.vn/Products/Images/42/153856/iphone-11-trang-600x600.jpg", 2022, 29490000, new Brand(1, "Apple"), new Category(8, "Phone", ""), "created_at", "updated_at"), 2));
        cartList.add(new CartItem(new Product(2, "Smart TV Casper 43 inch 43FX6200", "Simple and elegant designCasper tivi 43 inch 43FX6200 is designed simplified, luxurious with an edge-to-edge screen that offers a perfect visual experience.", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/1942/234997/casper-43fx6200-1-550x340.jpg", 2020, 4990000, new Brand(2, "Casper"), new Category(1, "TV", ""), "created_at", "updated_at"), 1));
    }

    private void setupUI(){
        // Find View
        backArrow = findViewById(R.id.ivBackArrow);
        cartListRecyclerView = findViewById(R.id.rvCartList);
        totalQuantity = findViewById(R.id.tvCartsTotalQuantity);
        totalPrice = findViewById(R.id.tvCartsTotalPrice);
    }

    private void setupListener(){
        // Back button
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void buildCartRecyclerView() {
        // Init recycler view adapter and layout manager
        CartListAdapter cartListAdapter = new CartListAdapter(CartActivity.this, cartList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        cartListRecyclerView.setHasFixedSize(true);
        cartListRecyclerView.setLayoutManager(linearLayoutManager);
        cartListRecyclerView.setAdapter(cartListAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(cartListRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        cartListRecyclerView.addItemDecoration(dividerItemDecoration);
    }
}