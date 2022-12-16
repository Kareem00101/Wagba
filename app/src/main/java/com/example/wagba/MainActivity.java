package com.example.wagba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.databinding.ActivityMainBinding;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import androidx.recyclerview.widget.LinearLayoutManager;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{

    /*Firebase*/
    // private FirebaseAuth auth;
    // private FirebaseAuth.AuthStateListener authListener;

    /*View Binding Variable*/
    private ActivityMainBinding binding;

    Button nav_profile;
    Button nav_orders;
    Button logout_btn;
    Button nav_cart;

    RecyclerView restaurantRecyclerView;

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

        /*** Recycler View Code ***/

        restaurantRecyclerView = binding.mainRecyclerView;
        ArrayList<RestaurantModel> restaurantList = new ArrayList<>();
        restaurantList.add(new RestaurantModel("Restaurant 1", 4, R.drawable.call_btn));
        restaurantList.add(new RestaurantModel("Restaurant 2", 3, R.drawable.call_btn));
        restaurantList.add(new RestaurantModel("Restaurant 3", 5, R.drawable.call_btn));

        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(restaurantList, this);
        restaurantRecyclerView.setAdapter(restaurantAdapter);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));




        /*** End of Recycler View Code ***/

        /*** Navigation Bar Code ***/


        // # Required Buttons
        nav_profile = binding.navProfileBtn;
        logout_btn = binding.logoutButton;
        nav_orders = binding.navOrderBtn;
        nav_cart = binding.navCartBtn;

        // # On Click
        nav_profile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                navigateToProfileActivity();
            }
        });

        nav_orders.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                navigateToOrdersActivity();
            }
        });

        nav_cart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                navigateToCartActivity();
            }
        });

        /* Logout Functionality */

        logout_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                logoutUser();
            }
        });

        /* End of Logout Functionality */


        /*** End of Navigation Bar Code ***/





    } // end of onCreate

    /*** Logout Supporting Functionalities ***/

    protected void logoutUser()
    {

        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    public void onComplete(Task<Void> task)
                    {
                        // user is now signed out
                        startActivity(new Intent(MainActivity.this, SignInActivity.class));
                        finish();
                    }
                });

    }

    /*** End of LogoutSupporting Functionalities ***/


    /*** Navigation Supporting Functionalities ***/

    void navigateToProfileActivity()
    {
        //finish();
        Intent intent = new Intent(MainActivity.this,ProfileActivity.class);
        startActivity(intent);
    }

    void navigateToCartActivity()
    {
        //finish();
        Intent intent = new Intent(MainActivity.this,CartActivity.class);
        startActivity(intent);
    }

    void navigateToOrdersActivity()
    {
        //finish();
        Intent intent = new Intent(MainActivity.this,OrdersActivity.class);
        startActivity(intent);
    }



    /*** End of Navigation Supporting Functionalities ***/



} // end of class