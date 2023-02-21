package com.example.prm_android_store.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prm_android_store.R;
import com.example.prm_android_store.adapters.ProductListAdapter;
import com.example.prm_android_store.data.Brand;
import com.example.prm_android_store.data.Category;
import com.example.prm_android_store.data.Product;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    // Init view
    private RecyclerView productListRecyclerView;
    private ImageView backArrow;
    private SearchView searchView;
    private TextView productNumber;
    private ChipGroup filterChipGroup;
    private ImageView cartIcon;

    // Init list
    private ArrayList<Product> productList = new ArrayList<>();
    private ArrayList<String> categoryFilter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        // Get data from intent
        Intent intent = getIntent();

        // Set up view and listener
        setupUI();
        setupListener();

        // Handle intent
        handleIntent(intent);
        setFilterChipGroup();

        // Init list
        initProductList();

        // Set product number
        productNumber.setText(String.valueOf(productList.size()) + " products");

        // Build recycler view
        buildProductRecyclerView();
    }

    private void handleIntent(Intent intent){
        if(intent.getStringExtra("search") != null){
            searchView.setQuery(intent.getStringExtra("search"), false);
        }

        if(intent.getStringExtra("categoryName") != null){
            categoryFilter.add(intent.getStringExtra("categoryName"));
        }
    }

    private void setFilterChipGroup(){
        for (String chipText: categoryFilter){
            Chip lChip = new Chip(this);
            lChip.setText(chipText);
            filterChipGroup.addView(lChip);
        }
    }

    private void setupUI(){
        // Find View
        productListRecyclerView = findViewById(R.id.rvProductList);
        backArrow = findViewById(R.id.ivBackArrow);
        searchView = findViewById(R.id.searchView);
        productNumber = findViewById(R.id.tvProductsNumber);
        filterChipGroup = findViewById(R.id.cgFilter);
        cartIcon = findViewById(R.id.ivCartIcon);
    }

    private void setupListener(){
        // Search view handle
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(ProductsActivity.this, ProductsActivity.class);
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
                Intent intent = new Intent(ProductsActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void buildProductRecyclerView() {
        // Init recycler view adapter and layout manager
        ProductListAdapter productListAdapter = new ProductListAdapter(ProductsActivity.this, productList, String.valueOf(searchView.getQuery()));
        GridLayoutManager productLayoutManager = new GridLayoutManager(this, 2);
        productListRecyclerView.setHasFixedSize(true);
        productListRecyclerView.setLayoutManager(productLayoutManager);
        productListRecyclerView.setAdapter(productListAdapter);
    }

    private void initProductList(){
        productList.add(new Product(1, "Iphone 14 Pro Max", "iPhone 14 has the same superspeedy chip that’s in iPhone 13 Pro. A15 Bionic, with a 5‑core GPU, powers all the latest features and makes graphically intense games and AR apps feel ultrafluid. An updated internal design delivers better thermal efficiency, so you can stay in the action longer.", "https://cdn.tgdd.vn/Products/Images/42/153856/iphone-11-trang-600x600.jpg", 2022, 29490000, new Brand(1, "Apple"), new Category(8, "Phone", ""), "created_at", "updated_at"));
        productList.add(new Product(2, "Smart TV Casper 43 inch 43FX6200", "Simple and elegant designCasper tivi 43 inch 43FX6200 is designed simplified, luxurious with an edge-to-edge screen that offers a perfect visual experience.", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/1942/234997/casper-43fx6200-1-550x340.jpg", 2020, 4990000, new Brand(2, "Casper"), new Category(1, "TV", ""), "created_at", "updated_at"));
        productList.add(new Product(3, "Samsung Inverter Fridge 647 liters RS62R5001B4/SV", "Samsung Inverter 647 liter refrigerator RS62R5001B4 / SV is a stylish, classy black refrigerator. Also, the fridge has two luxurious wide-open doors, not only a highlight for interior space but also helps you conveniently observe, find and arrange food.", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/1943/201134/samsung-rs62r5001b4-sv-19-300x300.jpg", 2019, 23990000, new Brand(3, "Samsung"), new Category(2, "Refrigerator", ""), "created_at", "updated_at"));
        productList.add(new Product(4, "Toshiba Inverter 8.5 Kg TW-BH95S2V WK", "Toshiba Inverter 8.5 Kg TW-BH95S2V WK washing machine has an elegant and neutral white color, combined with a modern front door design, promising to bring the interior space of the family with exquisite beauty and elegance.", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/1944/197893/toshiba-tw-bh95s2v-wk-10-300x300.jpg", 2019, 7490000, new Brand(4, "Toshiba"), new Category(4, "Washing Machine", ""), "created_at", "updated_at"));
        productList.add(new Product(5, "Daikin Inverter 1 HP FTKB25WMVMV", "Daikin Inverter 1 HP FTKB25WVMMV air conditioner is a line of air conditioners with Inverter technology to help save energy. In particular, the outdoor unit and indoor unit are improved to help the machine operate smoothly, stably, and achieve optimal capacity. In addition, owns Enzyme Blue filter combined with PM2.5 fine dust filter to remove odor-causing agents, allergens, and mold.", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/2002/271496/daikin-inverter-1-hp-ftkb25wmvmv31-550x160.jpg", 2018, 10990000, new Brand(5, "Daikin"), new Category(3, "Air Conditioner", ""), "created_at", "updated_at"));
        productList.add(new Product(6, "Smart TV Samsung 4K Crystal UHD 50 inch UA50AU8100", "Smart TV Samsung 4K 50 inch UA50AU8100 owns AirSlim design, slim frame, ultra-thin border feels like 3-sided borderless, super thin border for overall delicate TV, wide screen, adding aesthetics for living space. The stand is flexible, easy to change the height to fit many different interior designs and TV shelves.", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/1942/235791/led-4k-samsung-ua50au8100-221122-040529-550x340.jpg", 2021, 9890000, new Brand(3, "Samsung"), new Category(1, "TV", ""), "created_at", "updated_at"));
        productList.add(new Product(7, "LG Inverter 266 Lít GV-B262WB", "LG Inverter refrigerator 266 liters GV-B262WB has the ability to save electricity effectively and operate smoothly. Moreover, this refrigerator model can maintain optimal food freshness and cool drinks when placed on the side of the refrigerator door thanks to special technologies.", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/1943/284312/tu-lanh-lg-inverter-266-lit-gv-b262wb50-600x600.jpg", 2020, 6690000, new Brand(6, "LG"), new Category(2, "Refrigerator", ""), "created_at", "updated_at"));
    }
}