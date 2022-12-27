package com.example.wagba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wagba.databinding.ActivityOrdersBinding;

public class OrdersActivity extends AppCompatActivity
{

    /*View Binding Variable*/

    private ActivityOrdersBinding binding;
    Button back_btn;


    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /*Binding View*/
        binding = ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        /*** Navigation Bar Code ***/

        // # Required Buttons
        back_btn = binding.ordersBackBtn;

        // # On Click
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });

        /*** Navigation Bar Code ***/


    } // End of onCreate

    /*** Supporting Functionalities ***/

    void navigateToMainActivity()
    {

        Intent intent = new Intent(OrdersActivity.this,MainActivity.class);
        startActivity(intent);
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



} // End of Class