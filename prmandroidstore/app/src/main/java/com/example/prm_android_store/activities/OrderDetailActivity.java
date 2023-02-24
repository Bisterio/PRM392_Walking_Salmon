package com.example.prm_android_store.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prm_android_store.R;
import com.example.prm_android_store.adapters.CartListAdapter;
import com.example.prm_android_store.adapters.OrderItemListAdapter;
import com.example.prm_android_store.data.Brand;
import com.example.prm_android_store.data.CartItem;
import com.example.prm_android_store.data.Category;
import com.example.prm_android_store.data.Customer;
import com.example.prm_android_store.data.Order;
import com.example.prm_android_store.data.OrderItem;
import com.example.prm_android_store.data.Product;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    // Init view
    private ImageView backArrow;
    private TextView orderId;
    private TextView orderStatus;
    private TextView totalQuantity;
    private TextView totalPrice;
    private TextView orderDate;
    private TextView requiredDate;
    private TextView shippedDate;
    private TextView customerName;
    private TextView customerEmail;
    private TextView customerPhone;
    private TextView customerAddress;
    private RecyclerView orderItemRecyclerView;

    // Init order, customer, order item list
    private Customer loginedCustomer = new Customer(1, "Minh Duc", "Le", "leduchien09@gmail.com", "0931856541", "Ho Chi Minh city", "Viet Nam", "Phuoc Long B ward", 70000);
    private Order currentOrder = new Order(2, 1, 3, 6400000, "02/22/2022", "02/25/2022", null, loginedCustomer);
    private ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        // Set up view and listener
        setupUI();
        setupListener();

        // Init data
        initOrderItemList();

        // Set view
        inflateData();

        // Build recycler view
        buildOrderItemRecyclerView();
    }

    private void initOrderItemList(){
        orderItems.add(new OrderItem(1, 58980000, 2, new Product(1, "Iphone 14 Pro Max", "iPhone 14 has the same superspeedy chip that’s in iPhone 13 Pro. A15 Bionic, with a 5‑core GPU, powers all the latest features and makes graphically intense games and AR apps feel ultrafluid. An updated internal design delivers better thermal efficiency, so you can stay in the action longer.", "https://cdn.tgdd.vn/Products/Images/42/153856/iphone-11-trang-600x600.jpg", 2022, 29490000, new Brand(1, "Apple"), new Category(8, "Phone", ""), "created_at", "updated_at"), currentOrder));
        orderItems.add(new OrderItem(2, 4990000, 1, new Product(2, "Smart TV Casper 43 inch 43FX6200", "Simple and elegant designCasper tivi 43 inch 43FX6200 is designed simplified, luxurious with an edge-to-edge screen that offers a perfect visual experience.", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/1942/234997/casper-43fx6200-1-550x340.jpg", 2020, 4990000, new Brand(2, "Casper"), new Category(1, "TV", ""), "created_at", "updated_at"), currentOrder));
    }

    private void buildOrderItemRecyclerView() {
        // Init recycler view adapter and layout manager
        OrderItemListAdapter orderItemListAdapter = new OrderItemListAdapter(OrderDetailActivity.this, orderItems);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        orderItemRecyclerView.setHasFixedSize(true);
        orderItemRecyclerView.setLayoutManager(linearLayoutManager);
        orderItemRecyclerView.setAdapter(orderItemListAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(orderItemRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        orderItemRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void setupUI() {
        // Find view
        backArrow = findViewById(R.id.ivBackArrow);
        orderId = findViewById(R.id.tvOrderId);
        orderStatus = findViewById(R.id.tvOrderStatus);
        totalQuantity = findViewById(R.id.tvTotalQuantity);
        totalPrice = findViewById(R.id.tvTotalPrice);
        orderDate = findViewById(R.id.tvOrderDate);
        requiredDate = findViewById(R.id.tvRequiredDate);
        shippedDate = findViewById(R.id.tvShippedDate);
        customerName = findViewById(R.id.tvCustomerName);
        customerEmail = findViewById(R.id.tvCustomerEmail);
        customerPhone = findViewById(R.id.tvCustomerPhone);
        customerAddress = findViewById(R.id.tvCustomerAddress);
        orderItemRecyclerView = findViewById(R.id.rvOrderItemList);
    }

    private void inflateData(){
        // Inflate data into view
        orderId.setText("DETAIL OF ORDER #" + String.valueOf(currentOrder.getId()));
        setupOrderStatus();
        totalQuantity.setText(String.valueOf(currentOrder.getTotalQuantity()));
        totalPrice.setText(String.format("%,.0f", currentOrder.getTotalPrice()) + "₫");
        orderDate.setText("Order Date: " + currentOrder.getOrder_date());
        requiredDate.setText("Required Date: " + currentOrder.getRequired_date());
        if(currentOrder.getShipped_date()!=null){
            shippedDate.setText("Shipped Date: " + currentOrder.getShipped_date());
        } else {
            shippedDate.setText("This order is on its way");
        }
        customerName.setText(currentOrder.getCustomer().getLast_name() + " " + currentOrder.getCustomer().getFirst_name());
        customerEmail.setText(currentOrder.getCustomer().getEmail());
        customerPhone.setText(currentOrder.getCustomer().getPhone());
        customerAddress.setText(currentOrder.getCustomer().getStreet() + ", " + currentOrder.getCustomer().getCity() + ", " + currentOrder.getCustomer().getState());
    }

    private void setupOrderStatus(){
        switch (currentOrder.getStatus()){
            case 1:
                orderStatus.setText("Pending");
                orderStatus.setTextColor(Color.rgb(233,178,8));
                break;
            case 2:
                orderStatus.setText("Processing");
                orderStatus.setTextColor(Color.rgb(124,124,124));
                break;
            case 3:
                orderStatus.setText("Rejected");
                orderStatus.setTextColor(Color.rgb(210,0,0));
                break;
            case 4:
                orderStatus.setText("Completed");
                orderStatus.setTextColor(Color.rgb(60,130,36));
                break;
        }
    }

    private void setupListener() {
        // Back button
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}