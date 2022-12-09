package com.example.wagba;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wagba.databinding.ActivityCartBinding;
import com.example.wagba.databinding.ActivityOrdersBinding;

public class CartActivity extends AppCompatActivity {

    /*View Binding Variable*/
    private ActivityCartBinding binding;
    Button back_btn;
    Button order_now;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding View*/
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*** Navigation Bar Code ***/

        // # Required Buttons
        back_btn = binding.cartBackBtn;
        order_now = binding.cartPayBtn;

        // # On Click
        back_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navigateToMainActivity();
            }
        });

        order_now.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                navigateToTrackOrderActivity();
            }
        });

        /*** Navigation Bar Code ***/



        /*** Calculating Total Price ***/



        /*** End of Calculating Total Price ***/



    } // End of onCreate



    /*** Payment Supporting Functionalities ***/




    /*** End of Payment Functionalities ***/



    /*** Supporting Functionalities ***/

    void navigateToMainActivity()
    {
        Intent intent = new Intent(CartActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    void navigateToTrackOrderActivity()
    {
        Intent intent = new Intent(CartActivity.this,TrackOrderActivity.class);
        startActivity(intent);
        finish();
    }


    /*** End of Supporting Functionalities ***/


} // End of Class