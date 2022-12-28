package com.example.wagba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.databinding.ActivityRestaurantBinding;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {

    /*View Binding Variable*/
    private ActivityRestaurantBinding binding;

    RecyclerView dishRecyclerView;
    DishAdapter dishAdapter;

    Button back_btn;
    Button go_to_cart_btn;

    RestaurantModel myRestaurant;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        /*Binding View*/
        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        /*** Recycler View Code ***/

        myRestaurant = (RestaurantModel) getIntent().getSerializableExtra("restaurant");
        dishRecyclerView = binding.dishesRecyclerView;
        dishAdapter = new DishAdapter(myRestaurant.getRestaurant_dishes(), this);
        dishRecyclerView.setAdapter(dishAdapter);
        dishRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        /*** End of Recycler View Code ***/




        /*** Add to Cart Functionality ***/



        /*** End of Add to Cart Functionality ***/



        /*** Navigation Functionality ***/

        // to home
        back_btn = binding.restaurantBackBtn;
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });


        // to cart
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


    /*** get ordered items from cart ***/
    protected ArrayList<DishModel> getOrderedDishes()
    {
        return dishAdapter.getOrderedDishes();
    }


    /*** End of get ordered items from cart ***/




    /*** Supporting Functionalities ***/


    void navigateToCartActivity()
    {
        Intent intent = new Intent(RestaurantActivity.this,CartActivity.class);
        ArrayList<DishModel> orderedDishes = getOrderedDishes();
        if (orderedDishes.size() > 0)
        {
            intent.putExtra("orderedDishes", orderedDishes);
            intent.putExtra("restaurantName", myRestaurant.getRestaurantName());
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "No items selected", Toast.LENGTH_SHORT).show();
        }
    }

    /*** End of Supporting Functionalities ***/



//    /*** Handle Back Button ***/
//    @Override
//    public void onBackPressed()
//    {
//        navigateToMainActivity();
//    }
//    /*** End of Handle Back Button ***/



    /*** Lifecycle Methods ***/
    @Override
    protected void onStop()
    {
        super.onStop();
        finish();
    }
    /*** End of Lifecycle Methods ***/


} // end of Class