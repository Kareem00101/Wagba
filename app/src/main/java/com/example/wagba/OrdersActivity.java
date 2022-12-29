package com.example.wagba;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba.databinding.ActivityOrdersBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class OrdersActivity extends AppCompatActivity
{

    /*Firebase*/
    private DatabaseReference ref;
    private FirebaseDatabase database;

    /*View Binding Variable*/
    private ActivityOrdersBinding binding;
    Button back_btn;

    RecyclerView orderRecyclerView;
    OrderAdapter orderAdapter;

    public static ArrayList<OrderModel> orderList;


    //
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /*Binding View*/
        binding = ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        /*** Recycler View Code ***/

        // setup the RecyclerView
        setupRecyclerView();
        // populate the list
        fillRecyclerView();

        /*** End of Recycler View Code ***/



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
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        ref = database.getReference("orders/" + userID);

        ref.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                orderList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    HashMap order = (HashMap) dataSnapshot.getValue();

                    String orderID = order.get("orderID").toString();
                    String restaurantName = order.get("restaurantName").toString();
                    String restaurantImage = order.get("restaurantImage").toString();
                    String orderTotalPrice = order.get("totalPrice").toString();
                    String orderStatus = order.get("orderStatus").toString();

                    OrderModel orderModel = new OrderModel(orderID, restaurantName, restaurantImage, orderStatus, orderTotalPrice);
                    orderList.add(orderModel);

                }
                orderAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {
                Toast.makeText(OrdersActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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