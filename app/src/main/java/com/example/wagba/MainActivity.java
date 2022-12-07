package com.example.wagba;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.wagba.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{

    /*View Binding Variable*/
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        /*Disabling Night Mode*/
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        /*Reloading Saved Instance State*/
        super.onCreate(savedInstanceState);

        /*Binding View*/
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*Main Code Starts Here*/


    } // end of onCreate

} // end of class