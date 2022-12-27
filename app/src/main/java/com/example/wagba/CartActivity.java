package com.example.wagba;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.databinding.ActivityCartBinding;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    /*View Binding Variable*/
    private ActivityCartBinding binding;
    Button back_btn;
    Button order_now;

    ArrayList<DishModel> orderedDishes;
    RecyclerView cartRecyclerView;
    DishAdapter dishAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Binding View*/
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        orderedDishes = (ArrayList<DishModel>) intent.getSerializableExtra("orderedDishes");
        dishAdapter = new DishAdapter(orderedDishes, this);
        cartRecyclerView = binding.cartRecycler;
        cartRecyclerView.setAdapter(dishAdapter);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));


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
                onBackPressed();
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

        /*** End of Navigation Bar Code ***/



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
    }

    void navigateToTrackOrderActivity()
    {
        Intent intent = new Intent(CartActivity.this,TrackOrderActivity.class);
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