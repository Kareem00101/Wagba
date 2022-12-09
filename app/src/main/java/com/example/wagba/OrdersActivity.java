package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wagba.databinding.ActivityMainBinding;
import com.example.wagba.databinding.ActivityOrdersBinding;
import com.example.wagba.databinding.ActivityProfileBinding;

public class OrdersActivity extends AppCompatActivity
{

    /*View Binding Variable*/

    private ActivityOrdersBinding binding;
    Button back_btn;

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
                navigateToMainActivity();
            }
        });

        /*** Navigation Bar Code ***/


    } // End of onCreate

    /*** Supporting Functionalities ***/

    void navigateToMainActivity()
    {
        finish();
        Intent intent = new Intent(OrdersActivity.this,MainActivity.class);
        startActivity(intent);
    }

    /*** End of Supporting Functionalities ***/



} // End of Class