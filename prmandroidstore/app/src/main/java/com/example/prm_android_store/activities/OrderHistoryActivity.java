package com.example.prm_android_store.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm_android_store.R;
import com.example.prm_android_store.adapters.OrderListAdapter;
import com.example.prm_android_store.adapters.ProductListAdapter;
import com.example.prm_android_store.data.CartItem;
import com.example.prm_android_store.data.Customer;
import com.example.prm_android_store.data.Order;
import com.example.prm_android_store.data.OrderItem;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrderHistoryActivity extends AppCompatActivity {

    // Init view
    private ImageView backArrow;
    private TextView continueButton;
    private TextView submitButton;
    private TextView resendOTPButton;
    private TextView changePhoneButton;
    private TextView phoneVerificationTitle;
    private LinearLayout phoneInputLayout;
    private LinearLayout OTPInputLayout;
    private NestedScrollView orderHistoryLayout;
    private TextInputLayout phoneInput;
    private TextInputLayout OTPInput;
    private TextView customerName;
    private TextView customerEmail;
    private TextView customerPhone;
    private RecyclerView orderListRecyclerView;

    // Init data
    private Customer loginedCustomer = new Customer(1, "Minh Duc", "Le", "leduchien09@gmail.com", "0931856541", "Ho Chi Minh city", "Viet Nam", "Phuoc Long B ward", 70000);
    private ArrayList<Order> orderList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        // Set up view and listener
        setupUI();
        setupListener();

    }

    private void buildOrderListRecyclerView() {
        // Init recycler view adapter and layout manager
        OrderListAdapter orderListAdapter = new OrderListAdapter(OrderHistoryActivity.this, orderList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        orderListRecyclerView.setHasFixedSize(true);
        orderListRecyclerView.setLayoutManager(linearLayoutManager);
        orderListRecyclerView.setAdapter(orderListAdapter);
    }

    private void initOrderList() {
        orderList.add(new Order(2, 1, 1, 6400000, "02/22/2022", "02/25/2022", null, loginedCustomer));
        orderList.add(new Order(1, 4, 3, 12380000, "05/03/2022", "05/05/2022", "05/04/2022", loginedCustomer));
    }

    private void setupUI(){
        // Find View
        backArrow = findViewById(R.id.ivBackArrow);
        continueButton = findViewById(R.id.tvContinueButton);
        submitButton = findViewById(R.id.tvSubmitButton);
        phoneInputLayout = findViewById(R.id.phoneInputLayout);
        OTPInputLayout = findViewById(R.id.OTPInputLayout);
        phoneInput = findViewById(R.id.tilPhone);
        OTPInput = findViewById(R.id.tilOTP);
        resendOTPButton = findViewById(R.id.tvReSendOTP);
        changePhoneButton = findViewById(R.id.tvChangePhone);
        phoneVerificationTitle = findViewById(R.id.tvPhoneVerificationTitle);
        orderHistoryLayout = findViewById(R.id.orderHistoryLayout);
        customerName = findViewById(R.id.tvCustomerName);
        customerEmail = findViewById(R.id.tvCustomerEmail);
        customerPhone = findViewById(R.id.tvCustomerPhone);
        orderListRecyclerView = findViewById(R.id.rvOrderList);

        // Set Layout visibility
        phoneInputLayout.setVisibility(LinearLayout.VISIBLE);
        OTPInputLayout.setVisibility(LinearLayout.GONE);
        orderHistoryLayout.setVisibility(LinearLayout.GONE);
    }

    private void setupListener(){
        // Back button
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Continue to otp input button
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Switch to OTP input layout
                if(validatePhone()){
                    String phone = phoneInput.getEditText().getText().toString().trim();
                    phoneVerificationTitle.setText("Verification code has been sent to phone number " + phone);
                    phoneInputLayout.setVisibility(LinearLayout.GONE);
                    OTPInputLayout.setVisibility(LinearLayout.VISIBLE);
                }
            }
        });

        // Continue to order history button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Switch to order history layout
                if(validateOTP() && OTPInput.getEditText().getText().toString().trim().equals("123456")){
                    OTPInputLayout.setVisibility(LinearLayout.GONE);
                    orderHistoryLayout.setVisibility(LinearLayout.VISIBLE);

                    // Get logined user info
                    customerName.setText(loginedCustomer.getLast_name() + " " + loginedCustomer.getFirst_name());
                    customerEmail.setText(loginedCustomer.getEmail());
                    customerPhone.setText(loginedCustomer.getPhone());

                    // Get orders info
                    initOrderList();
                    buildOrderListRecyclerView();
                } else if (!OTPInput.getEditText().getText().toString().trim().equals("123456")){
                    OTPInput.setError("Wrong verification code");
                }
            }
        });

        // Resend OTP button
        resendOTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OrderHistoryActivity.this, "New verification code has been sent!", Toast.LENGTH_SHORT).show();
            }
        });

        // Back to phone input page
        changePhoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneInputLayout.setVisibility(LinearLayout.VISIBLE);
                OTPInputLayout.setVisibility(LinearLayout.GONE);
            }
        });

        // Add Phone validation
        phoneInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                validatePhone();
            }
        });

        // Add OTP validation
        OTPInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                validateOTP();
            }
        });
    }

    private boolean validatePhone(){
        // Phone validation
        String phone = phoneInput.getEditText().getText().toString().trim();
        if (phone.isEmpty()) {
            phoneInput.setError("Please enter phone number");
            return false;
        } else if (phone.length() != 10){
            phoneInput.setError("Phone number must be 10 digits");
            return false;
        }
        else {
            phoneInput.setError(null);
        }

        return true;
    }

    private boolean validateOTP(){
        // Phone validation
        String otp = OTPInput.getEditText().getText().toString().trim();
        if (otp.isEmpty()) {
            OTPInput.setError("Please enter verification code");
            return false;
        } else if (otp.length() != 6){
            OTPInput.setError("Verification code must be 6 digits");
            return false;
        }
        else {
            OTPInput.setError(null);
        }

        return true;
    }
}