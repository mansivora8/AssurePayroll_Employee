package com.example.assurepayroll_employee;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class display_payslips extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_payslips);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}