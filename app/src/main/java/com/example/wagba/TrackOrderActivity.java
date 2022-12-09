package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.wagba.databinding.ActivityMainBinding;
import com.example.wagba.databinding.ActivityTrackOrderBinding;

public class TrackOrderActivity extends AppCompatActivity
{


    /*View Binding Variable*/
    private ActivityTrackOrderBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        /*Binding View*/
        binding = ActivityTrackOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




    } // end of onCreate


    /*** Supporting Functionalities ***/



    /*** End of Supporting Functionalities ***/


} // end of Class