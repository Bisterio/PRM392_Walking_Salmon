package com.example.prm_android_store.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm_android_store.R;
import com.example.prm_android_store.adapters.CategoryListAdapter;
import com.example.prm_android_store.adapters.ProductListAdapter;
import com.example.prm_android_store.data.Brand;
import com.example.prm_android_store.data.Category;
import com.example.prm_android_store.data.Product;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener{

    // Init view
    private RecyclerView productListRecyclerView;
    private RecyclerView categoryListRecyclerView;
    private SearchView searchView;
    private TextView viewAll;
    private ImageView cartIcon;
    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private ImageView menuButton;
    private TextView staffLoginButton;

    // Init list
    private ArrayList<Product> productList = new ArrayList<>();
    private ArrayList<Category> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up view and listener
        setupUI();
        setupListener();

        // Init list
        initCategoryList();
        initProductList();

        // Build recycler view
        buildCategoryRecyclerView();
        buildProductRecyclerView();
    }

    private void setupUI(){
        // Find View
        productListRecyclerView = findViewById(R.id.rvProductList);
        categoryListRecyclerView = findViewById(R.id.rvCategoryList);
        searchView = findViewById(R.id.searchView);
        viewAll = findViewById(R.id.tvViewAll);
        cartIcon = findViewById(R.id.ivCartIcon);
        navView = findViewById(R.id.nav_view);
        if (navView != null) {
            navView.setNavigationItemSelectedListener(this);
        }
        menuButton = findViewById(R.id.ivMenu);
        drawerLayout = findViewById(R.id.drawer_layout);
        staffLoginButton = findViewById(R.id.nav_footer_staff);
    }

    private void setupListener(){
        // Search view handle
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(HomeActivity.this, ProductsActivity.class);
                intent.putExtra("search", query);
                startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        // View all button navigate to all products activity
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProductsActivity.class);
                startActivity(intent);
            }
        });

        // Accessing cart
        cartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });

        // Open navigation drawer
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Staff login activity
        staffLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StaffLoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    private void buildProductRecyclerView() {
        // Init recycler view adapter and layout manager
        ProductListAdapter productListAdapter = new ProductListAdapter(HomeActivity.this, productList, String.valueOf(searchView.getQuery()));
        GridLayoutManager productLayoutManager = new GridLayoutManager(this, 2);
        productListRecyclerView.setHasFixedSize(true);
        productListRecyclerView.setLayoutManager(productLayoutManager);
        productListRecyclerView.setAdapter(productListAdapter);
    }

    private void buildCategoryRecyclerView() {
        CategoryListAdapter categoryListAdapter = new CategoryListAdapter(HomeActivity.this, categoryList);
        GridLayoutManager categoryLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        categoryListRecyclerView.setHasFixedSize(true);
        categoryListRecyclerView.setLayoutManager(categoryLayoutManager);
        categoryListRecyclerView.setAdapter(categoryListAdapter);
    }

    private void initCategoryList(){
        categoryList.add(new Category(1, "TV", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/Tivi-128x129.png"));
        categoryList.add(new Category(2, "Refrigerator", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/Tulanh-128x129.png"));
        categoryList.add(new Category(3, "Air Conditioner", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/Maylanh-128x129-128x129-1.png"));
        categoryList.add(new Category(4, "Washing Machine", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/Maygiat-128x129.png"));
        categoryList.add(new Category(5, "Dryer", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/maysayquanao-128x129-1.png"));
        categoryList.add(new Category(6, "Fan", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/quat-128x129.png"));
        categoryList.add(new Category(7, "Laptop", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/Laptop-129x129-1.png"));
        categoryList.add(new Category(8, "Phone", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/6781763F-DDB5-4E3E-B676-5350244DAE9D-64x64.png"));
        categoryList.add(new Category(9, "Household", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/Diengiadung-128x129.png"));
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        if (id == R.id.nav_cart) {
            intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        }

        if (id == R.id.nav_products) {
            intent = new Intent(HomeActivity.this, ProductsActivity.class);
            startActivity(intent);
        }

        if (id == R.id.nav_history) {
            intent = new Intent(HomeActivity.this, OrderHistoryActivity.class);
            startActivity(intent);
        }

        return false;
    }
}