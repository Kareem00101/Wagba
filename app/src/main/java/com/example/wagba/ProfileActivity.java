package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wagba.databinding.ActivityMainBinding;
import com.example.wagba.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity
{

    /*View Binding Variable*/
    private ActivityProfileBinding binding;
    Button to_home_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /*Binding View*/
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*Main Code Starts Here*/

        //

        /* Navigation to Home */

        to_home_btn = binding.btnBackToHome;
        to_home_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navigateToMainActivity();
            }
        });

        /* End of Navigation Home */



    } // End of onCreate


    /*** Supporting Functionalities ***/

    // # Navigate to Main Intent
    void navigateToMainActivity()
    {
        finish();
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /*** End of Supporting Functionalities ***/



} // End of Class