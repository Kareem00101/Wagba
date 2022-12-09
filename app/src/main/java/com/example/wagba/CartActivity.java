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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding View*/
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        /*** Navigation Bar Code ***/

        // # Required Buttons
        back_btn = binding.cartBackBtn;

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



    /*** Payment Supporting Functionalities ***/




    /*** End of Payment Functionalities ***/



    /*** Supporting Functionalities ***/

    void navigateToMainActivity()
    {
        finish();
        Intent intent = new Intent(CartActivity.this,MainActivity.class);
        startActivity(intent);
    }

    /*** End of Supporting Functionalities ***/


} // End of Class