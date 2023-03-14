package com.example.prm_android_store.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.prm_android_store.R;
import com.example.prm_android_store.data.Product;
import com.example.prm_android_store.utils.ProductAPI;
import com.squareup.picasso.Picasso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    // Init data
    private Product product = new Product();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        // Get data from intent
        Intent intent = getIntent();
        product.setId(intent.getLongExtra("productId", 0));

        // Set up view and listener
        setupUI();
        setupListener();

        // Set search query
        if(intent.getStringExtra("search") != null){
            searchView.setQuery(intent.getStringExtra("search"), false);
        }

        // Get Product detail
        getProductDetail();
    }

    private void setProductDataToView(Product response){
        // Set data for view
        Picasso.get().load(response.getImage()).into(productImage);
        productId.setText(String.valueOf(response.getId()));
        productName.setText(response.getName());
        productPrice.setText(String.format("%,.0f đ", response.getList_price()));
        productBrand.setText("Brand: " + response.getBrand());
        productCategory.setText("Category: " + response.getCategory_name());
        productModelYear.setText("Model Year: " + String.valueOf(response.getModel_year()));
        productDescription.setText(response.getDescription());
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

    private void getProductDetail() {
        ProductAPI.productApi.getById(product.getId()).enqueue(
                new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        product = response.body();
                        setProductDataToView(product);
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Toast.makeText(ProductDetailActivity.this, "Can't find this product!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
        );
    }
}