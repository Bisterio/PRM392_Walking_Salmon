package com.example.prm_android_store.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm_android_store.R;
import com.example.prm_android_store.adapters.CartListAdapter;
import com.example.prm_android_store.adapters.ProductListAdapter;
import com.example.prm_android_store.data.Brand;
import com.example.prm_android_store.data.CartItem;
import com.example.prm_android_store.data.Category;
import com.example.prm_android_store.data.Product;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    // Init view
    private ImageView backArrow;
    private RecyclerView cartListRecyclerView;
    private TextView totalQuantity;
    private TextView totalPrice;
    private TextView orderButton;
    private TextView totalPriceBottom;
    private TextView cartEmpty;
    private TextInputLayout customerFirstNameInput;
    private TextInputLayout customerLastNameInput;
    private TextInputLayout customerEmailInput;
    private TextInputLayout customerPhoneInput;
    private TextInputLayout customerStateInput;
    private TextInputLayout customerCityInput;
    private TextInputLayout customerStreetInput;
    private TextInputLayout customerZipCodeInput;
    private TextView searchCustomerButton;

    // Init list
    private ArrayList<CartItem> cartList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Set up view and listener
        setupUI();
        setupListener();

        // Check if staff
        SharedPreferences sharedPref = getSharedPreferences("application", Context.MODE_PRIVATE);
        if (sharedPref.getString("CURRENT_ACCOUNT", "").trim().isEmpty()) {
            searchCustomerButton.setVisibility(View.GONE);
        }

        // Set up cart list
        initCartList();
        //cartList.clear();

        // Handle cart empty
        if(cartList.isEmpty()){
            cartListRecyclerView.setVisibility(View.GONE);
            cartEmpty.setVisibility(View.VISIBLE);
        } else {
            cartListRecyclerView.setVisibility(View.VISIBLE);
            cartEmpty.setVisibility(View.GONE);
        }

        // Handle cart information
        totalQuantity.setText("Total prices (" + calculatedCartQuantity() + " products):");
        totalPrice.setText(String.format("%,.0f", calculatedCartPrice()) + "₫");
        totalPriceBottom.setText(String.format("%,.0f", calculatedCartPrice()) + "₫");
        orderButton.setText("ORDER (" + calculatedCartQuantity() + ")");

        // Get data from intent
        Intent intent = getIntent();
        if(intent.getStringExtra("phone") != null){
            getUserData(intent);
        }

        // Build recycler view
        buildCartRecyclerView();
    }

    private int calculatedCartQuantity(){
        int quantity = 0;
        for(CartItem item : cartList){
            quantity += item.getQuantity();
        }
        return quantity;
    }

    private float calculatedCartPrice(){
        float price = 0;
        for(CartItem item : cartList){
            price += item.getQuantity() * item.getProduct().getList_price();
        }
        return price;
    }

    private void initCartList(){
        //cartList.add(new CartItem(new Product(1, "Iphone 14 Pro Max", "iPhone 14 has the same superspeedy chip that’s in iPhone 13 Pro. A15 Bionic, with a 5‑core GPU, powers all the latest features and makes graphically intense games and AR apps feel ultrafluid. An updated internal design delivers better thermal efficiency, so you can stay in the action longer.", "https://cdn.tgdd.vn/Products/Images/42/153856/iphone-11-trang-600x600.jpg", 2022, 29490000, new Brand(1, "Apple"), new Category(8, "Phone", ""), "created_at", "updated_at"), 2));
        //cartList.add(new CartItem(new Product(2, "Smart TV Casper 43 inch 43FX6200", "Simple and elegant designCasper tivi 43 inch 43FX6200 is designed simplified, luxurious with an edge-to-edge screen that offers a perfect visual experience.", "https://img.tgdd.vn/imgt/f_webp,fit_outside,quality_100/https://cdn.tgdd.vn/Products/Images/1942/234997/casper-43fx6200-1-550x340.jpg", 2020, 4990000, new Brand(2, "Casper"), new Category(1, "TV", ""), "created_at", "updated_at"), 1));
    }

    private void setupUI(){
        // Find View
        backArrow = findViewById(R.id.ivBackArrow);
        cartListRecyclerView = findViewById(R.id.rvCartList);
        totalQuantity = findViewById(R.id.tvCartsTotalQuantity);
        totalPrice = findViewById(R.id.tvCartsTotalPrice);
        totalPriceBottom = findViewById(R.id.tvTotalPrice);
        orderButton = findViewById(R.id.tvOrderButton);
        customerFirstNameInput = findViewById(R.id.tilFirstName);
        customerLastNameInput = findViewById(R.id.tilLastName);
        customerEmailInput = findViewById(R.id.tilEmail);
        customerPhoneInput = findViewById(R.id.tilPhone);
        customerStateInput = findViewById(R.id.tilState);
        customerCityInput = findViewById(R.id.tilCity);
        customerStreetInput = findViewById(R.id.tilStreet);
        customerZipCodeInput = findViewById(R.id.tilZipCode);
        searchCustomerButton = findViewById(R.id.tvSearchCustomerCart);
        cartEmpty = findViewById(R.id.tvEmptyCart);
    }

    private void setupListener(){
        // Back button
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Search customer activity
        searchCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, CustomerSearchActivity.class);
                finish();
                startActivity(intent);
            }
        });

        // Order Button
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFirstName() && validateLastName() && validateEmail() && validatePhone()
                && validateState() && validateCity() && validateStreet() && validateZipCode()){
                    Toast.makeText(CartActivity.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(CartActivity.this, "Not validated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Add validation to input field
        customerFirstNameInput.getEditText().addTextChangedListener(new ValidationTextWatcher(customerFirstNameInput));
        customerLastNameInput.getEditText().addTextChangedListener(new ValidationTextWatcher(customerLastNameInput));
        customerEmailInput.getEditText().addTextChangedListener(new ValidationTextWatcher(customerEmailInput));
        customerPhoneInput.getEditText().addTextChangedListener(new ValidationTextWatcher(customerPhoneInput));
        customerStateInput.getEditText().addTextChangedListener(new ValidationTextWatcher(customerStateInput));
        customerCityInput.getEditText().addTextChangedListener(new ValidationTextWatcher(customerCityInput));
        customerStreetInput.getEditText().addTextChangedListener(new ValidationTextWatcher(customerStreetInput));
        customerZipCodeInput.getEditText().addTextChangedListener(new ValidationTextWatcher(customerZipCodeInput));
    }

    private void buildCartRecyclerView() {
        // Init recycler view adapter and layout manager
        CartListAdapter cartListAdapter = new CartListAdapter(CartActivity.this, cartList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        cartListRecyclerView.setHasFixedSize(true);
        cartListRecyclerView.setLayoutManager(linearLayoutManager);
        cartListRecyclerView.setAdapter(cartListAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(cartListRecyclerView.getContext(),
                linearLayoutManager.getOrientation());
        cartListRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    private class ValidationTextWatcher implements TextWatcher {
        private View view;
        private ValidationTextWatcher(View view) {
            this.view = view;
        }
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }
        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.tilFirstName:
                    validateFirstName();
                    break;
                case R.id.tilLastName:
                    validateLastName();
                    break;
                case R.id.tilEmail:
                    validateEmail();
                    break;
                case R.id.tilPhone:
                    validatePhone();
                    break;
                case R.id.tilState:
                    validateState();
                    break;
                case R.id.tilCity:
                    validateCity();
                    break;
                case R.id.tilStreet:
                    validateStreet();
                    break;
                case R.id.tilZipCode:
                    validateZipCode();
                    break;
            }
        }
    }

    private boolean validateFirstName(){
        // First name validation
        String firstName = customerFirstNameInput.getEditText().getText().toString().trim();
        if (firstName.isEmpty()) {
            customerFirstNameInput.setError("Please enter first name");
            return false;
        } else {
            customerFirstNameInput.setError(null);
        }
        return true;
    }

    private boolean validateLastName(){
        // Last name validation
        String lastName = customerLastNameInput.getEditText().getText().toString().trim();
        if (lastName.isEmpty()) {
            customerLastNameInput.setError("Please enter last name");
            return false;
        } else {
            customerLastNameInput.setError(null);
        }
        return true;
    }

    private boolean validateEmail(){
        // Email validation
        String email = customerEmailInput.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            customerEmailInput.setError("Please enter email");
            return false;
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            customerEmailInput.setError("Invalid email format, ex: abc@example.com");
            return false;
        } else {
            customerEmailInput.setError(null);
        }
        return true;
    }

    private boolean validatePhone(){
        // Phone validation
        String phone = customerPhoneInput.getEditText().getText().toString().trim();
        if (phone.isEmpty()) {
            customerPhoneInput.setError("Please enter phone number");
            return false;
        } else if (phone.length() != 10){
            customerPhoneInput.setError("Phone number must be 10 digits");
            return false;
        }
        else {
            customerPhoneInput.setError(null);
        }

        return true;
    }

    private boolean validateState(){
        // State validation
        String state = customerStateInput.getEditText().getText().toString().trim();
        if (state.isEmpty()) {
            customerStateInput.setError("Please enter state");
            return false;
        } else {
            customerStateInput.setError(null);
        }
        return true;
    }

    private boolean validateCity(){
        // City validation
        String city = customerCityInput.getEditText().getText().toString().trim();
        if (city.isEmpty()) {
            customerCityInput.setError("Please enter city");
            return false;
        } else {
            customerCityInput.setError(null);
        }

        return true;
    }

    private boolean validateStreet(){
        // Street validation
        String street = customerStreetInput.getEditText().getText().toString().trim();
        if (street.isEmpty()) {
            customerStreetInput.setError("Please enter street");
            return false;
        } else {
            customerStreetInput.setError(null);
        }

        return true;
    }

    private boolean validateZipCode(){
        // Zip code validation
        String zipCode = customerZipCodeInput.getEditText().getText().toString().trim();
        if (zipCode.isEmpty()) {
            customerZipCodeInput.setError("Please enter zip code");
            return false;
        } else if (zipCode.length() != 5){
            customerZipCodeInput.setError("Zip code must be 5 digits");
            return false;
        }
        else {
            customerZipCodeInput.setError(null);
        }

        return true;
    }

    private void getUserData(Intent intent){
        if(intent.getStringExtra("email") != null){
            customerFirstNameInput.getEditText().setText(intent.getStringExtra("firstName"));
            customerLastNameInput.getEditText().setText(intent.getStringExtra("lastName"));
            customerEmailInput.getEditText().setText(intent.getStringExtra("email"));
            customerPhoneInput.getEditText().setText(intent.getStringExtra("phone"));
            customerStateInput.getEditText().setText(intent.getStringExtra("state"));
            customerCityInput.getEditText().setText(intent.getStringExtra("city"));
            customerStreetInput.getEditText().setText(intent.getStringExtra("street"));
            customerZipCodeInput.getEditText().setText(String.valueOf(intent.getIntExtra("zipCode", 0)));
        }
        else {
            customerPhoneInput.getEditText().setText(intent.getStringExtra("phone"));
        }
    }
}