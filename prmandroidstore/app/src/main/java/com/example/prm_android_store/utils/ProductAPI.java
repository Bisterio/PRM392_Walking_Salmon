package com.example.prm_android_store.utils;

import com.example.prm_android_store.data.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductAPI {
    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-mm-dd hh:MM:ss")
            .create();

    ProductAPI productApi = new Retrofit.Builder()
            .baseUrl("https://63e21a4aad0093bf29c7c118.mockapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductAPI.class);

    @GET("api/products?sortBy=model_year&order=desc")
    Call<List<Product>> getAllProduct();

    @GET("api/products/{id}")
    Call<Product> getById(@Path("id") long id);

    @GET("api/products?sortBy=model_year&order=desc")
    Call<List<Product>> getAllProductPaginatedSearchName(@Query("page") int page,
                                               @Query("limit") int limit,
                                               @Query("name") String nameSearch);
    @GET("api/products?sortBy=model_year&order=desc")
    Call<List<Product>> getAllProductPaginatedSearchCate(@Query("page") int page,
                                                         @Query("limit") int limit,
                                                         @Query("category_id") String categorySearch);
}
