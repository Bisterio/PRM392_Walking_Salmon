package com.example.prm_android_store.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prm_android_store.R;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetailActivity extends AppCompatActivity {

    // Init view
    ImageView backArrow;
    ImageView productImage;
    TextView productId;
    TextView productName;
    TextView productPrice;
    TextView productBrand;
    TextView productCategory;
    TextView productModelYear;
    TextView productDescription;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Get data from intent
        Intent intent = getIntent();


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

        // Set data for view
        Picasso.get().load(intent.getStringExtra("productImage")).into(productImage);
        productId.setText(intent.getStringExtra("productId"));
        productName.setText(intent.getStringExtra("productName"));
        productPrice.setText(String.format("%,.0f", intent.getDoubleExtra("productPrice", 0f)) + "đ");
        productBrand.setText("Brand: " +intent.getStringExtra("productBrand"));
        productCategory.setText("Category: " +intent.getStringExtra("productCategory"));
        productModelYear.setText("Model Year: " + String.valueOf(intent.getIntExtra("productModelYear", 0)));
        productDescription.setText(intent.getStringExtra("productDescription"));


        // Set on click listener
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}