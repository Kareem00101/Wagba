package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wagba.databinding.ActivityOrdersBinding;

public class CartActivity extends AppCompatActivity {

    /*View Binding Variable*/
    private ActivityOrdersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding View*/
        binding = ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}