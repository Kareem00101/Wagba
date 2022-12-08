package com.example.wagba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.example.wagba.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity
{

    /*View Binding Variable*/
    private ActivityMainBinding binding;

    Button nav_profile;
    Button nav_cart;
    Button nav_orders;

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

        /*** Navigation Bar Code ***/


        // # Required Buttons
        nav_profile = binding.navProfileBtn;
        nav_orders = binding.navOrderBtn;
        nav_cart = binding.navCartBtn;

        // # On Click
        nav_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToProfileActivity();
            }
        });

        nav_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToOrdersActivity();
            }
        });

        nav_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToCartActivity();
            }
        });


        /*** End of Navigation Bar Code ***/





    } // end of onCreate



    /*** Supporting Functionalities ***/

    void navigateToProfileActivity()
    {
        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
        startActivity(intent);
    }

    void navigateToCartActivity()
    {
        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
        startActivity(intent);
    }

    void navigateToOrdersActivity()
    {
        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
        startActivity(intent);
    }



    /*** End of Supporting Functionalities ***/



} // end of class