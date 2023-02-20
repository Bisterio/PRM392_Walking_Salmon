package com.example.prm_android_store.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.prm_android_store.R;
import com.example.prm_android_store.adapters.CategoryListAdapter;
import com.example.prm_android_store.adapters.ProductListAdapter;
import com.example.prm_android_store.data.Brand;
import com.example.prm_android_store.data.Category;
import com.example.prm_android_store.data.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView productListRecyclerView;
    private RecyclerView categoryListRecyclerView;
    private NestedScrollView nestedScrollView;
    private ProgressBar loadingPB;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ProductListAdapter productListAdapter;
    private CategoryListAdapter categoryListAdapter;
    private ArrayList<Product> productList = new ArrayList<>();
    private ArrayList<Category> categoryList = new ArrayList<>();
    private RequestQueue requestQueue;
    private JsonArrayRequest jsonArrayRequest;
    private GridLayoutManager productLayoutManager;
    private GridLayoutManager categoryLayoutManager;

    private int page = 1;
    String url = "https://63e21a4aad0093bf29c7c118.mockapi.io/api/products" + "?limit=8&page=";
    private boolean hasNext = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find View
        productListRecyclerView = findViewById(R.id.rvProductList);
        categoryListRecyclerView = findViewById(R.id.rvCategoryList);
        nestedScrollView = findViewById(R.id.nsv);
        loadingPB = findViewById(R.id.pbLoading);
        swipeRefreshLayout = findViewById(R.id.srlProductList);

        initCategoryList();
        initProductList();

        buildCategoryRecyclerView();
        buildProductRecyclerView();

//        // Swipe Refresh Layout
//        swipeRefreshLayout.setOnRefreshListener(this);
//        swipeRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                productList.clear();
//                initCategoryList();
//                getData();
//                buildCategoryRecyclerView();
//            }
//        });
//
//        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
//                    if (hasNext) {
//                        page++;
//                        loadingPB.setVisibility(View.VISIBLE);
//                        getData();
//                    }
//                }
//            }
//        });
    }

//    private void getData() {
//        jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url + page, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                if(response.length() == 0){
//                    hasNext = false;
//                    loadingPB.setVisibility(View.INVISIBLE);
//                    return;
//                }
//                JSONObject jsonObject = null;
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        jsonObject = response.getJSONObject(i);
//
//                        Product product = new Product();
//                        product.setId(jsonObject.getLong("id"));
//                        product.setName(jsonObject.getString("name"));
//                        product.setDescription(jsonObject.getString("description"));
//                        product.setBrand(new Brand(jsonObject.getLong("brand"), "Apple"));
//                        product.setCategory(new Category(jsonObject.getLong("category"), "Phone", "a"));
//                        product.setModel_year(jsonObject.getInt("model_year"));
//                        product.setList_price(jsonObject.getDouble("list_price"));
//                        product.setCreated_at(jsonObject.getString("created_at"));
//                        product.setUpdated_at(jsonObject.getString("updated_at"));
//                        product.setImage(jsonObject.getString("image"));
//
//                        productList.add(product);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                buildProductRecyclerView();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(MainActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(jsonArrayRequest);
//    }

//    @Override
//    public void onRefresh() {
//        page = 1;
//        productList.clear();
//        getData();
//    }

    private void buildProductRecyclerView() {
        productListAdapter = new ProductListAdapter(MainActivity.this, productList);
        productLayoutManager = new GridLayoutManager(this,2);
        productListRecyclerView.setHasFixedSize(true);
        productListRecyclerView.setLayoutManager(productLayoutManager);
        productListRecyclerView.setAdapter(productListAdapter);
    }

    private void buildCategoryRecyclerView() {
        categoryListAdapter = new CategoryListAdapter(MainActivity.this, categoryList);
        categoryLayoutManager = new GridLayoutManager(this,2, GridLayoutManager.HORIZONTAL, false);
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
        productList.add(new Product(1, "Iphone 14 Pro Max", "The newest from the Iphone series", "https://cdn.tgdd.vn/Products/Images/42/153856/iphone-11-trang-600x600.jpg", 2022, 29490000, new Brand(1, "Apple"), new Category(8, "Phone", ""), "created_at", "updated_at"));
    }
}