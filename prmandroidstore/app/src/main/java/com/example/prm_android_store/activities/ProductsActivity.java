package com.example.prm_android_store.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.prm_android_store.R;
import com.example.prm_android_store.adapters.ProductListAdapter;
import com.example.prm_android_store.data.Brand;
import com.example.prm_android_store.data.Category;
import com.example.prm_android_store.data.Product;
import com.example.prm_android_store.utils.ProductAPI;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    // Init view
    private RecyclerView productListRecyclerView;
    private ImageView backArrow;
    private SearchView searchView;
    private TextView productNumber;
    private ChipGroup filterChipGroup;
    private ImageView cartIcon;
    private NestedScrollView nestedScrollView;
    private ProgressBar loadingPB;
    private SwipeRefreshLayout swipeRefreshLayout;

    // Init list
    private List<Product> productList = new ArrayList<>();
    private ArrayList<Category> categoryFilter = new ArrayList<>();

    // Init data
    private String nameSearch = "";
    private String cateSearch = "";
    private boolean hasNext = true;
    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        // Get data from intent
        Intent intent = getIntent();
        initCategoryList();

        // Set up view and listener
        setupUI();
        setupListener();

        // Handle intent
        handleIntent(intent);
        setFilterChipGroup();

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                page = 1;
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
        if(cateSearch.isEmpty()){
            ProductAPI.productApi.getAllProductPaginatedSearchName(page, 8, nameSearch).enqueue(
                    new Callback<List<Product>>() {
                        @Override
                        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                            if(page == 1){
                                productList.clear();
                            }
                            productList.addAll(response.body());
                            buildProductRecyclerView();
                            swipeRefreshLayout.setRefreshing(false);
                            if(response.body().size() < 8){
                                hasNext = false;
                                loadingPB.setVisibility(View.GONE);
                            }
                            // Set product number
                            productNumber.setText(String.valueOf("Showing " + productList.size()) + " products");
                        }

                        @Override
                        public void onFailure(Call<List<Product>> call, Throwable t) {
                            page = 1;
                        }
                    }
            );
            return;
        }
        ProductAPI.productApi.getAllProductPaginatedSearchCate(page, 8, cateSearch).enqueue(
                new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                        if(page == 1){
                            productList.clear();
                        }
                        productList.addAll(response.body());
                        buildProductRecyclerView();
                        swipeRefreshLayout.setRefreshing(false);
                        if(response.body().size() < 8){
                            hasNext = false;
                            loadingPB.setVisibility(View.GONE);
                        }
                        // Set product number
                        productNumber.setText(String.valueOf("Showing " + productList.size()) + " products");
                    }

                    @Override
                    public void onFailure(Call<List<Product>> call, Throwable t) {
                        page = 1;
                    }
                }
        );
    }

    private void handleIntent(Intent intent){
        if(intent.getStringExtra("search") != null){
            nameSearch = intent.getStringExtra("search");
            searchView.setQuery(intent.getStringExtra("search"), false);
        }

        if(intent.getLongExtra("categoryId", 0) != 0){
            cateSearch = String.valueOf(intent.getLongExtra("categoryId", 0));
        }
    }

    private void setFilterChipGroup(){
        for (Category chip: categoryFilter){
            Chip lChip = new Chip(this);
            lChip.setId((int) chip.getId());
            lChip.setText(chip.getName());
            lChip.setCheckable(true);
            filterChipGroup.addView(lChip);
            if(!cateSearch.isEmpty() && cateSearch.equals(String.valueOf(chip.getId()))){
                lChip.setChecked(true);
            }
        }
    }

    private void initCategoryList() {
        categoryFilter.add(new Category(1, "TV", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/Tivi-128x129.png"));
        categoryFilter.add(new Category(2, "Refrigerator", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/Tulanh-128x129.png"));
        categoryFilter.add(new Category(3, "Air Conditioner", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/Maylanh-128x129-128x129-1.png"));
        categoryFilter.add(new Category(4, "Washing Machine", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/Maygiat-128x129.png"));
        categoryFilter.add(new Category(5, "Dryer", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/maysayquanao-128x129-1.png"));
        categoryFilter.add(new Category(6, "Fan", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/quat-128x129.png"));
        categoryFilter.add(new Category(7, "Laptop", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/Laptop-129x129-1.png"));
        categoryFilter.add(new Category(8, "Phone", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/6781763F-DDB5-4E3E-B676-5350244DAE9D-64x64.png"));
        categoryFilter.add(new Category(9, "Household", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn//content/Diengiadung-128x129.png"));
    }

    private void setupUI(){
        // Find View
        productListRecyclerView = findViewById(R.id.rvProductList);
        backArrow = findViewById(R.id.ivBackArrow);
        searchView = findViewById(R.id.searchView);
        productNumber = findViewById(R.id.tvProductsNumber);
        filterChipGroup = findViewById(R.id.cgFilter);
        filterChipGroup.setSingleSelection(true);
        cartIcon = findViewById(R.id.ivCartIcon);
        nestedScrollView = findViewById(R.id.nsv);
        loadingPB = findViewById(R.id.pbLoading);
        swipeRefreshLayout = findViewById(R.id.srlProductList);
    }

    private void setupListener(){
        // Search view handle
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                nameSearch = query;
                cateSearch = "";
                filterChipGroup.clearCheck();
                onRefresh();
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

        // Handling chip group
        filterChipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            searchView.setQuery("", false);
            if(checkedIds.isEmpty()){
                cateSearch = "";
            } else {
                cateSearch = String.valueOf(checkedIds);
            }
            onRefresh();
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

    @Override
    public void onRefresh() {
        page = 1;
        hasNext = true;
        getProductList();
    }
}