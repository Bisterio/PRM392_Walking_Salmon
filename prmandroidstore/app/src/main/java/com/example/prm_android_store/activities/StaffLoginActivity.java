package com.example.prm_android_store.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prm_android_store.R;
import com.google.android.material.textfield.TextInputLayout;

public class StaffLoginActivity extends AppCompatActivity {

    // Init view
    private ImageView backArrow;
    private TextView continueButton;
    private TextView submitButton;
    private TextView resendOTPButton;
    private TextView changePhoneButton;
    private TextView phoneVerificationTitle;
    private LinearLayout phoneInputLayout;
    private LinearLayout OTPInputLayout;
    private TextInputLayout phoneInput;
    private TextInputLayout OTPInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);

        // Set up view and listener
        setupUI();
        setupListener();
    }

    private void setupUI() {
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

        // Set Layout visibility
        phoneInputLayout.setVisibility(LinearLayout.VISIBLE);
        OTPInputLayout.setVisibility(LinearLayout.GONE);
    }

    private void setupListener() {
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
                if (validatePhone()) {
                    String phone = phoneInput.getEditText().getText().toString().trim();
                    phoneVerificationTitle.setText("Verification code has been sent to phone number " + phone);
                    phoneInputLayout.setVisibility(LinearLayout.GONE);
                    OTPInputLayout.setVisibility(LinearLayout.VISIBLE);
                    // Hide keyboard
                    if (view != null) {
                        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }
                }
            }
        });

        // Continue to login button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Switch to order history layout
                if (validateOTP() && OTPInput.getEditText().getText().toString().trim().equals("123456")) {
                    Toast.makeText(StaffLoginActivity.this, "Login successfully!", Toast.LENGTH_SHORT).show();
                    // Hide keyboard
                    if (view != null) {
                        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        manager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    SharedPreferences sharedPref = getSharedPreferences("application", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("CURRENT_ACCOUNT", "Le Minh Duc");
                    editor.apply();

                    finish();

                } else if (!OTPInput.getEditText().getText().toString().trim().equals("123456")) {
                    OTPInput.setError("Wrong verification code");
                }
            }
        });

        // Resend OTP button
        resendOTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StaffLoginActivity.this, "New verification code has been sent!", Toast.LENGTH_SHORT).show();
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

        // Add OTP validation
        OTPInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                validateOTP();
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

    private boolean validateOTP() {
        // Phone validation
        String otp = OTPInput.getEditText().getText().toString().trim();
        if (otp.isEmpty()) {
            OTPInput.setError("Please enter verification code");
            return false;
        } else if (otp.length() != 6) {
            OTPInput.setError("Verification code must be 6 digits");
            return false;
        } else {
            OTPInput.setError(null);
        }

        return true;
    }
}