package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wagba.databinding.ActivityRestaurantBinding;
import com.example.wagba.databinding.ActivityTrackOrderBinding;

public class RestaurantActivity extends AppCompatActivity {

    /*View Binding Variable*/
    private ActivityRestaurantBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        /*Binding View*/
        binding = ActivityRestaurantBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




    } // end of onCreate

    /*** Supporting Functionalities ***/



    /*** End of Supporting Functionalities ***/


} // end of Class