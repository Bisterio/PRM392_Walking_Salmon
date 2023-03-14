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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.example.prm_android_store.utils.ProductAPI;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements
        NavigationView.OnNavigationItemSelectedListener, SwipeRefreshLayout.OnRefreshListener {

    // Init view
    private NestedScrollView nestedScrollView;
    private RecyclerView productListRecyclerView;
    private RecyclerView categoryListRecyclerView;
    private SearchView searchView;
    private TextView viewAll;
    private ImageView cartIcon;
    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private ImageView menuButton;
    private TextView staffLoginButton;
    private ProgressBar loadingPB;
    private SwipeRefreshLayout swipeRefreshLayout;

    // Init list
    private List<Product> productList = new ArrayList<>();
    private ArrayList<Category> categoryList = new ArrayList<>();

    // Init data
    private boolean hasNext = true;
    private int page = 1;
    private boolean isStaff = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Set up view and listener
        setupUI();
        setupListener();

        // Staff authorization
        if (isStaff) {
            staffLoginButton.setText("Logout");
        }

        // Init list
        initCategoryList();
        buildCategoryRecyclerView();

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                productList.clear();
                getProductList();
            }
        });

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    if (hasNext) {
                        page++;
                        loadingPB.setVisibility(View.VISIBLE);
                        getProductList();
                    }
                }
            }
        });
    }

    private void getProductList() {
        ProductAPI.productApi.getAllProductPaginatedSearchName(page, 8, "").enqueue(
                new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        productList.addAll(response.body());
                        buildProductRecyclerView();
                        swipeRefreshLayout.setRefreshing(false);
                        if(response.body().size() < 8){
                            hasNext = false;
                            loadingPB.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        productList.clear();
                    }
                }
        );
    }

    private void setupUI() {
        // Find View
        nestedScrollView = findViewById(R.id.nsv);
        productListRecyclerView = findViewById(R.id.rvProductList);
        categoryListRecyclerView = findViewById(R.id.rvCategoryList);
        searchView = findViewById(R.id.searchView);
        viewAll = findViewById(R.id.tvViewAll);
        cartIcon = findViewById(R.id.ivCartIcon);
        navView = findViewById(R.id.nav_view);
        loadingPB = findViewById(R.id.pbLoading);
        swipeRefreshLayout = findViewById(R.id.srlProductList);
        if (navView != null) {
            navView.setNavigationItemSelectedListener(this);
            if (!isStaff) {
                Menu nav_Menu = navView.getMenu();
                nav_Menu.findItem(R.id.nav_search_customer).setVisible(false);
            }
        }
        menuButton = findViewById(R.id.ivMenu);
        drawerLayout = findViewById(R.id.drawer_layout);
        staffLoginButton = findViewById(R.id.nav_footer_staff);
    }

    private void setupListener() {
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
                if (!isStaff) {
                    Intent intent = new Intent(HomeActivity.this, StaffLoginActivity.class);
                    startActivity(intent);
                } else {
                    isStaff = false;
                    SharedPreferences sharedPref = getSharedPreferences("application", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.remove("CURRENT_ACCOUNT");
                    editor.apply();
                    finish();
                    startActivity(getIntent());
                    Toast.makeText(HomeActivity.this, "Logout successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
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

    private void initCategoryList() {
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

        if (id == R.id.nav_search_customer) {
            intent = new Intent(HomeActivity.this, CustomerSearchActivity.class);
            startActivity(intent);
        }

        return false;
    }

    @Override
    protected void onResume() {
        SharedPreferences sharedPref = getSharedPreferences("application", Context.MODE_PRIVATE);
        if (!sharedPref.getString("CURRENT_ACCOUNT", "").trim().isEmpty()) {
            isStaff = true;
            staffLoginButton.setText("Logout");
            Menu nav_Menu = navView.getMenu();
            nav_Menu.findItem(R.id.nav_search_customer).setVisible(true);
        }

        super.onResume();
    }

    @Override
    public void onRefresh() {
        page = 1;
        hasNext = true;
        productList.clear();
        getProductList();
    }
}