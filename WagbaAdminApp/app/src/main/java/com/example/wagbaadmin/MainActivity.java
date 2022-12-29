package com.example.wagbaadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.wagbaadmin.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
    /*Firebase*/
    private DatabaseReference ref;
    private FirebaseDatabase database;

    /*View Binding Variable*/
    private ActivityMainBinding binding;

    RecyclerView orderRecyclerView;
    OrderAdapter orderAdapter;

    public static ArrayList<OrderModel> orderList;

    String dishText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);

        /*Binding View*/
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*** Recycler View Code ***/

        // setup the RecyclerView
        setupRecyclerView();
        // populate the list
        fillRecyclerView();

        /*** End of Recycler View Code ***/
    }

    private void setupRecyclerView()
    {
        orderRecyclerView = binding.ordersRecyclerView;

        orderList = new ArrayList<>();
        orderAdapter = new OrderAdapter(orderList, this);

        orderRecyclerView.setAdapter(orderAdapter);
        orderRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void fillRecyclerView()
    {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("orders");

        ref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                orderList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    for(DataSnapshot orderSnapshot : dataSnapshot.getChildren())
                    {
                        HashMap order = (HashMap) orderSnapshot.getValue();
                        Log.d("Order", order.toString());
                        String orderID = order.get("orderID").toString();
                        String userID = order.get("userID").toString();
                        String restaurantName = order.get("restaurantName").toString();
                        String orderTotalPrice = order.get("totalPrice").toString();
                        String orderStatus = order.get("orderStatus").toString();
                        String orderGate = order.get("orderGate").toString();
                        String orderPeriod = order.get("orderPeriod").toString();
                        String orderDate = order.get("orderDate").toString();
                        dishText = "";

                        for (DataSnapshot dishListSnapshot: orderSnapshot.getChildren())
                        {

                            for(DataSnapshot dishSnapshot: dishListSnapshot.getChildren())
                            {
                                HashMap dish = (HashMap) dishSnapshot.getValue();
                                Log.d("Dish", dish.toString());
                                String dishName = dish.get("dishName").toString();
                                String dishQuantity = dish.get("dishQuantity").toString();
                                dishText += dishName + " x" + dishQuantity + " ";
                            }

                        }
                        OrderModel orderModel = new OrderModel(orderID, userID, restaurantName, orderStatus, orderGate, orderPeriod, orderTotalPrice, orderDate, dishText);
                        orderList.add(orderModel);
                    }

                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}

