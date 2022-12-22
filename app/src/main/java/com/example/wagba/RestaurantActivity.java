package com.example.wagba;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.databinding.ActivityRestaurantBinding;

public class RestaurantActivity extends AppCompatActivity {

    /*View Binding Variable*/
    private ActivityRestaurantBinding binding;

    RecyclerView dishRecyclerView;
    DishAdapter dishAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        /*Binding View*/
        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        dishRecyclerView = binding.dishsRecyclerView;
        dishAdapter = new DishAdapter(MainActivity.restaurantList, this);
        dishRecyclerView.setAdapter(dishAdapter);
        dishRecyclerView.setLayoutManager(new LinearLayoutManager(this));




    } // end of onCreate

    /*** Supporting Functionalities ***/



    /*** End of Supporting Functionalities ***/


} // end of Class