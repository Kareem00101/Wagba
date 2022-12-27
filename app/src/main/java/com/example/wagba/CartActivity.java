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

        /* setup recycler view */
        setupRecyclerView();


        /* Navigation Code */

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

        /* End of Navigation Bar Code */


        /* Calculating Total Price */

        //
        setupPrices();

        /* End of Calculating Total Price */


    } // End of onCreate



    /*** Payment Supporting Functionalities ***/

    void setupPrices()
    {
        // # Required Variables
        double total_price = 0.0;
        double delivery_cost = 10.0;

        // # Calculating Total Price
        for (DishModel dish : orderedDishes)
        {
            total_price += dish.getDishPrice()*dish.getDishQuantity();
        }

        // # Setting Total Price
        String ItemsTotal = "Items Price: " + total_price;
        String DeliveryCost = "Delivery Cost: " + delivery_cost;
        String TotalPrice = "Total Price: " + (total_price + delivery_cost);
        binding.cartItemsPriceTxt.setText(ItemsTotal);

        // # Setting Delivery Cost
        binding.cartDeliveryPriceTxt.setText(DeliveryCost);

        // # Setting Total Cost
        binding.cartTotalPriceTxt.setText(TotalPrice);
    }



    /*** End of Payment Functionalities ***/



    /*** Supporting Functionalities ***/

    void setupRecyclerView()
    {
        Intent intent = this.getIntent();
        orderedDishes = (ArrayList<DishModel>) intent.getSerializableExtra("orderedDishes");
        dishAdapter = new DishAdapter(orderedDishes, this);
        cartRecyclerView = binding.cartRecycler;
        cartRecyclerView.setAdapter(dishAdapter);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

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




    /*** Lifecycle Methods ***/
    @Override
    protected void onStop()
    {
        super.onStop();
        finish();
    }
    /*** End of Lifecycle Methods ***/



} // End of Class