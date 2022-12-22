package com.example.wagba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.databinding.ActivityRestaurantBinding;

public class RestaurantActivity extends AppCompatActivity {

    /*View Binding Variable*/
    private ActivityRestaurantBinding binding;

    RecyclerView dishRecyclerView;
    DishAdapter dishAdapter;

    Button back_btn;
    Button go_to_cart_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        /*Binding View*/
        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        /*** Recycler View Code ***/

        RestaurantModel myRestaurant = (RestaurantModel) getIntent().getSerializableExtra("restaurant");
        dishRecyclerView = binding.dishesRecyclerView;
        dishAdapter = new DishAdapter(myRestaurant.getRestaurant_dishes(), this);
        dishRecyclerView.setAdapter(dishAdapter);
        dishRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        /*** End of Recycler View Code ***/




        /*** Add to Cart Functionality ***/



        /*** End of Add to Cart Functionality ***/



        /*** Navigation Functionality ***/

        back_btn = binding.restaurantBackBtn;
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navigateToMainActivity();
            }
        });

        go_to_cart_btn = binding.restToCartBtn;
        go_to_cart_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navigateToCartActivity();
            }
        });

        /*** End of Navigation Functionality ***/


    } // end of onCreate

    /*** Supporting Functionalities ***/

    // # Navigate to Main Intent
    void navigateToMainActivity()
    {
        Intent intent = new Intent(RestaurantActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    void navigateToCartActivity()
    {
        Intent intent = new Intent(RestaurantActivity.this,CartActivity.class);
        startActivity(intent);
        finish();
    }

    /*** End of Supporting Functionalities ***/


} // end of Class