package com.example.prm_android_store.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.prm_android_store.R;
import com.example.prm_android_store.data.Customer;
import com.google.android.material.textfield.TextInputLayout;

public class CustomerSearchActivity extends AppCompatActivity {

    // Init view
    private ImageView backArrow;
    private TextView customerName;
    private TextView customerEmail;
    private TextView customerPhone;
    private TextView customerAddress;
    private LinearLayout customerInformationLayout;
    private LinearLayout notFoundLayout;
    private TextInputLayout phoneInput;
    private TextView searchButton;
    private TextView addExistingCustomerButton;
    private TextView addNewCustomerButton;

    // Init data
    private Customer searchedCustomer = new Customer(1, "Duc", "Le", "leduchien09@gmail.com", "0931856541", "Ho Chi Minh city", "Vietnam", "Phuoc Long B Ward", 70000);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_search);

        // Set up view and listener
        setupUI();
        setupListener();
    }

    private void setupUI() {
        // Find View
        backArrow = findViewById(R.id.ivBackArrow);
        phoneInput = findViewById(R.id.tilPhone);
        customerName = findViewById(R.id.tvCustomerName);
        customerEmail = findViewById(R.id.tvCustomerEmail);
        customerPhone = findViewById(R.id.tvCustomerPhone);
        customerAddress = findViewById(R.id.tvCustomerAddress);
        customerInformationLayout = findViewById(R.id.customerInformationLayout);
        notFoundLayout = findViewById(R.id.notFoundLayout);
        searchButton = findViewById(R.id.tvSearchButton);
        addExistingCustomerButton = findViewById(R.id.tvAddExistingCustomerButton);
        addNewCustomerButton = findViewById(R.id.tvAddNewCustomerButton);

        // Set Layout visibility
        customerInformationLayout.setVisibility(LinearLayout.GONE);
        notFoundLayout.setVisibility(LinearLayout.GONE);
    }

    private void setupListener() {
        // Back button
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Search
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Switch to OTP input layout
                if (validatePhone() && phoneInput.getEditText().getText().toString().trim().equals("0931856541")) {
                    notFoundLayout.setVisibility(LinearLayout.GONE);
                    customerInformationLayout.setVisibility(LinearLayout.VISIBLE);
                    // Hide keyboard
                    if (view != null) {
                        InputMethodManager manager= (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    // Get logined user info
                    customerName.setText(searchedCustomer.getLast_name() + " " + searchedCustomer.getFirst_name());
                    customerEmail.setText(searchedCustomer.getEmail());
                    customerPhone.setText(searchedCustomer.getPhone());
                    customerAddress.setText(searchedCustomer.getStreet() + ", " + searchedCustomer.getCity() + ", " + searchedCustomer.getState());
                } else if (validatePhone()) {
                    notFoundLayout.setVisibility(LinearLayout.VISIBLE);
                    customerInformationLayout.setVisibility(LinearLayout.GONE);
                }
            }
        });

        // Add Phone validation
        phoneInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validatePhone();
            }
        });

        // Add existing customer button
        addExistingCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(CustomerSearchActivity.this, CartActivity.class);
                intent.putExtra("firstName", searchedCustomer.getFirst_name());
                intent.putExtra("lastName", searchedCustomer.getLast_name());
                intent.putExtra("email", searchedCustomer.getEmail());
                intent.putExtra("phone", searchedCustomer.getPhone());
                intent.putExtra("state", searchedCustomer.getState());
                intent.putExtra("city", searchedCustomer.getCity());
                intent.putExtra("street", searchedCustomer.getStreet());
                intent.putExtra("zipCode", searchedCustomer.getZip_code());
                startActivity(intent);
            }
        });

        // Add new customer button
        addNewCustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(CustomerSearchActivity.this, CartActivity.class);
                intent.putExtra("phone", searchedCustomer.getPhone());
                startActivity(intent);
            }
        });
    }

    private boolean validatePhone() {
        // Phone validation
        String phone = phoneInput.getEditText().getText().toString().trim();
        if (phone.isEmpty()) {
            phoneInput.setError("Please enter phone number");
            return false;
        } else if (phone.length() != 10) {
            phoneInput.setError("Phone number must be 10 digits");
            return false;
        } else {
            phoneInput.setError(null);
        }

        return true;
    }
}