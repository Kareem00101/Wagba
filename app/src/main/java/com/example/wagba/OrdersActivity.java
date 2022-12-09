package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wagba.databinding.ActivityMainBinding;
import com.example.wagba.databinding.ActivityOrdersBinding;
import com.example.wagba.databinding.ActivityProfileBinding;

public class OrdersActivity extends AppCompatActivity
{

    /*View Binding Variable*/
    private ActivityOrdersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /*Binding View*/
        binding = ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}