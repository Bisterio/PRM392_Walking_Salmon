package com.example.prm_android_store.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.prm_android_store.R;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetailActivity extends AppCompatActivity {

    // Init view
    private ImageView backArrow;
    private ImageView productImage;
    private TextView productId;
    private TextView productName;
    private TextView productPrice;
    private TextView productBrand;
    private TextView productCategory;
    private TextView productModelYear;
    private TextView productDescription;
    private SearchView searchView;
    private ImageView cartIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Get data from intent
        Intent intent = getIntent();

        // Set up view and listener
        setupUI();
        setupListener();

        // Set search query
        if(intent.getStringExtra("search") != null){
            searchView.setQuery(intent.getStringExtra("search"), false);
        }

        // Set data for view
        Picasso.get().load(intent.getStringExtra("productImage")).into(productImage);
        productId.setText(intent.getStringExtra("productId"));
        productName.setText(intent.getStringExtra("productName"));
        productPrice.setText(String.format("%,.0f", intent.getDoubleExtra("productPrice", 0f)) + "đ");
        productBrand.setText("Brand: " +intent.getStringExtra("productBrand"));
        productCategory.setText("Category: " +intent.getStringExtra("productCategory"));
        productModelYear.setText("Model Year: " + String.valueOf(intent.getIntExtra("productModelYear", 0)));
        productDescription.setText(intent.getStringExtra("productDescription"));
    }

    private void setupUI(){
        // Find View
        backArrow = findViewById(R.id.ivBackArrow);
        productImage = findViewById(R.id.ivProduct);
        productId = findViewById(R.id.tvProductId);
        productName = findViewById(R.id.tvProductName);
        productPrice = findViewById(R.id.tvProductPrice);
        productBrand = findViewById(R.id.tvProductBrand);
        productCategory = findViewById(R.id.tvProductCategory);
        productModelYear = findViewById(R.id.tvProductModelYear);
        productDescription = findViewById(R.id.tvProductDescription);
        searchView = findViewById(R.id.searchView);
        cartIcon = findViewById(R.id.ivCartIcon);
    }

    private void setupListener(){
        // Search view handle
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(ProductDetailActivity.this, ProductsActivity.class);
                intent.putExtra("search", query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // Back button
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Accessing cart
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
}