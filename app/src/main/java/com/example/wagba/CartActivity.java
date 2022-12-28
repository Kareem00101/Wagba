package com.example.wagba;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.databinding.ActivityCartBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class CartActivity extends AppCompatActivity {

    /*View Binding Variable*/
    private ActivityCartBinding binding;
    Button back_btn;
    Button order_now;

    ArrayList<DishModel> orderedDishes;
    RecyclerView cartRecyclerView;
    DishAdapter dishAdapter;

    RadioGroup radioPeriodGroup;
    RadioGroup radioGateGroup;

    Boolean periodFlag = false;
    String selectedPeriod = "";
    String selectedGate = "";

    Calendar calendar;

    Double totalPrice;
    Double itemsPrice;
    Double deliveryCost;

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

        /*** Order Functionality ***/

        order_now.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getCheckedRadioGate();
                getCheckedRadioPeriod();
                if(periodFlag == false) return;
                addOrderToFirebase();
                navigateToTrackOrderActivity();
            }
        });

        /*** End of Order Functionality ***/

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
        itemsPrice = 0.0;
        deliveryCost = 10.0;

        // # Calculating Total Price
        for (DishModel dish : orderedDishes)
        {
            itemsPrice += dish.getDishPrice()*dish.getDishQuantity();
        }
        totalPrice = itemsPrice + deliveryCost;

        // # Setting Total Price
        String ItemsTotal = "Items Price: " + itemsPrice;
        String DeliveryCost = "Delivery Cost: " + deliveryCost;
        String TotalPrice = "Total Price: " + (totalPrice);
        binding.cartItemsPriceTxt.setText(ItemsTotal);

        // # Setting Delivery Cost
        binding.cartDeliveryPriceTxt.setText(DeliveryCost);

        // # Setting Total Cost
        binding.cartTotalPriceTxt.setText(TotalPrice);
    }


    /*** End of Payment Functionalities ***/

    /*** Handling Radios Selection ***/

    void getCheckedRadioGate()
    {
        radioGateGroup = binding.cartDeliveryRadioGroup;
        int selectedId = radioGateGroup.getCheckedRadioButtonId();
        switch (selectedId)
        {
            case R.id.radio_gate_3:
                selectedGate = "Gate 3";
                break;
            case R.id.radio_gate_4:
                selectedGate = "Gate 4";
                break;
            default:
                Toast.makeText(this, "Please Select a Gate", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    void getCheckedRadioPeriod()
    {
        radioPeriodGroup = binding.cartDeliveryTimeRadioGroup;
        int selectedId = radioPeriodGroup.getCheckedRadioButtonId();
        switch (selectedId)
        {
            case R.id.radio_period_1:
                selectedPeriod = "12:00";
                handlePeriodFlag();
                break;
            case R.id.radio_period_2:
                selectedPeriod = "3:00";
                handlePeriodFlag();
                break;
            case R.id.radio_period_3:
                selectedPeriod = "test";
                periodFlag = true;
                break;
            default:
                Toast.makeText(this, "Please Select a Period", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    void handlePeriodFlag()
    {
        calendar = Calendar.getInstance();
        if (selectedPeriod.equals("12:00"))
        {
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            if (hour <= 10)
            {
                periodFlag = true;
            }
            else
            {
                Toast.makeText(this, "Please Select a Valid Period", Toast.LENGTH_SHORT).show();
                periodFlag = false;
            }
        }
        else if (selectedPeriod.equals("3:00"))
        {
            int hour = calendar.get(Calendar.HOUR_OF_DAY);

            if (hour <= 13)
            {
                periodFlag = true;
            }
            else
            {
                Toast.makeText(this, "Please Select a Valid Period", Toast.LENGTH_SHORT).show();
                periodFlag = false;
            }
        }
        else
        {
            Toast.makeText(this, "Unexpected Error", Toast.LENGTH_SHORT).show();
            periodFlag = false;
        }
    }


    /*** End of Handling Radios Selection ***/


    /*** Supporting Functionalities ***/

    void setupRecyclerView()
    {
        Intent intent = this.getIntent();
        orderedDishes = (ArrayList<DishModel>) intent.getSerializableExtra("orderedDishes");
        Log.d("orderedDishes", orderedDishes.toString());
        dishAdapter = new DishAdapter(orderedDishes, this);
        cartRecyclerView = binding.cartRecycler;
        cartRecyclerView.setAdapter(dishAdapter);
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    void navigateToTrackOrderActivity()
    {
        Intent intent = new Intent(CartActivity.this,TrackOrderActivity.class);
        intent.putExtra("selectedGate", selectedGate);
        intent.putExtra("selectedPeriod", selectedPeriod);
        startActivity(intent);
    }


    /*** End of Supporting Functionalities ***/

    /*** Firebase Functionalities ***/

    void addOrderToFirebase()
    {
        // # Required Variables
        String orderID = UUID.randomUUID().toString();
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String orderStatus = "Placed";
        String orderGate = selectedGate;
        String orderPeriod = selectedPeriod;
        String restaurantName = getIntent().getStringExtra("restaurantName");
        // # Creating Order Object
        OrderModel order = new OrderModel(orderID, userID, restaurantName, orderStatus, orderGate, orderPeriod, totalPrice, itemsPrice, deliveryCost, orderedDishes);
        // # Adding Order to Firebase
        FirebaseDatabase.getInstance().getReference("orders").child(userID).child(orderID).setValue(order);
    }


    /*** End of Firebase Functionalities ***/


    /*** Lifecycle Methods ***/

    @Override
    protected void onStop()
    {
        super.onStop();
        finish();
    }

    /*** End of Lifecycle Methods ***/



} // End of Class