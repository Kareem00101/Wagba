package com.example.wagbaadmin;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder>
{
    //private final RecyclerViewInterface recyclerViewInterface;
    private static ArrayList<OrderModel> orderList;
    private Context context;

    public OrderAdapter(ArrayList<OrderModel> orderList, Context context)
    {
        this.orderList = orderList;
        this.context = context;
        //this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_card, parent, false);
        OrderViewHolder orderViewHolder = new OrderViewHolder(view);

        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position)
    {
        OrderModel orderModel = orderList.get(position);

        holder.OID.setText(orderModel.getOrderID().toString());
        holder.UID.setText(orderModel.getUserID().toString());
        holder.orderDate.setText("" + orderModel.getOrderDate());
        holder.orderGate.setText("" + orderModel.getOrderGate());
        holder.orderPeriod.setText("" + orderModel.getOrderPeriod());
        holder.orderStatus.setText("" + orderModel.getOrderStatus());
        holder.orderedDishes.setText("" + orderModel.getOrderedDishes());
        holder.orderRestaurant.setText("" + orderModel.getRestaurantName());
        holder.orderTotalPrice.setText("" + orderModel.getOrderTotalPrice() + "EGP");

        if(orderModel.getOrderStatus().equals("Canceled"))
        {
            holder.advanceOrderStateBtn.setVisibility(View.GONE);
        }
        if(orderModel.getOrderStatus().equals("Delivered"))
        {
            holder.cancelOrderBtn.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount()
    {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder
    {
        TextView OID, UID, orderTotalPrice, orderStatus, orderRestaurant, orderGate, orderPeriod, orderedDishes, orderDate;
        Button advanceOrderStateBtn;
        Button cancelOrderBtn;
        String[] orderStates = {"Placed", "Confirmed", "Delivering", "Delivered"};

        public OrderViewHolder(@NonNull View itemView)
        {
            super(itemView);
            OID = itemView.findViewById(R.id.ocard_oid);
            UID = itemView.findViewById(R.id.ocard_uid);
            orderGate = itemView.findViewById(R.id.ocard_gate);
            orderDate = itemView.findViewById(R.id.ocard_odate);
            orderPeriod = itemView.findViewById(R.id.ocard_period);
            orderStatus = itemView.findViewById(R.id.ocard_status);
            orderedDishes = itemView.findViewById(R.id.ocard_dishes);
            orderTotalPrice = itemView.findViewById(R.id.ocard_tprice);
            orderRestaurant = itemView.findViewById(R.id.ocard_rest_name);

            cancelOrderBtn = itemView.findViewById(R.id.ocard_btn_cancel);
            advanceOrderStateBtn = itemView.findViewById(R.id.ocard_btn_advance);
            Calendar calendar = Calendar.getInstance();

            // Advance Button Click Listener
            advanceOrderStateBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    for (int i = 0; i < orderStates.length; i++)
                    {
                        // Handle edge cases
                        if(i == 3)
                        {
                            Toast.makeText(v.getContext(), "Order is already delivered", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        // Advance order state
                        String currentState = orderStatus.getText().toString();
                        if(currentState.equals(orderStates[i]))
                        {

                            int hour = calendar.get(Calendar.HOUR_OF_DAY);
                            int minute = calendar.get(Calendar.MINUTE);
                            // Check for valid period
                            if(orderPeriod.getText().toString().equals("12:00") && orderStates[i + 1].equals("Confirmed") && hour >= 10 && minute >= 30)
                            {

                                // Update database
                                Map<String, Object> updatedState = new HashMap<>();
                                updatedState.put("orderStatus", "Canceled");
                                FirebaseDatabase.getInstance().getReference("orders").child(UID.getText().toString()).child(OID.getText().toString()).updateChildren(updatedState);

                                // Update UI
                                orderStatus.setText("Canceled");
                                advanceOrderStateBtn.setVisibility(View.GONE);

                                Toast.makeText(v.getContext(), "Invalid Period, Order Canceled", Toast.LENGTH_SHORT).show();
                                break;
                            }
                            else if(orderPeriod.getText().toString().equals("3:00") && orderStates[i + 1].equals("Confirmed") && hour >= 13 && minute >= 30)
                            {

                                // Update database
                                Map<String, Object> updatedState = new HashMap<>();
                                updatedState.put("orderStatus", "Canceled");
                                FirebaseDatabase.getInstance().getReference("orders").child(UID.getText().toString()).child(OID.getText().toString()).updateChildren(updatedState);

                                // Update UI
                                orderStatus.setText("Canceled");
                                advanceOrderStateBtn.setVisibility(View.GONE);

                                Toast.makeText(v.getContext(), "Invalid Period, Order Canceled", Toast.LENGTH_SHORT).show();
                                break;
                            }


                            // Update database
                            Map<String, Object> updatedState = new HashMap<>();
                            updatedState.put("orderStatus", orderStates[i+1].toString());
                            FirebaseDatabase.getInstance().getReference("orders").child(UID.getText().toString()).child(OID.getText().toString()).updateChildren(updatedState);

                            // Update UI
                            orderStatus.setText(orderStates[i + 1]);

                            // Change order button visibility
                            if(orderStates[i + 1].equals("Delivered"))
                            {
                                cancelOrderBtn.setVisibility(View.GONE);
                            }

                            // Inform admin of updates
                            Toast.makeText(itemView.getContext(), "Order State Set to: "+orderStates[i+1], Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                }
            });


            // Cancel Button Click Listener
            cancelOrderBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(orderStatus.getText().toString().equals("Canceled"))
                    {
                        Toast.makeText(v.getContext(), "Order is already canceled", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        // Update database
                        Map<String, Object> updatedState = new HashMap<>();
                        updatedState.put("orderStatus", "Canceled");
                        FirebaseDatabase.getInstance().getReference("orders").child(UID.getText().toString()).child(OID.getText().toString()).updateChildren(updatedState);

                        // Update UI
                        orderStatus.setText("Canceled");
                        cancelOrderBtn.setVisibility(View.GONE);

                        // Inform admin of updates
                        Toast.makeText(itemView.getContext(), "Order State Set to: Canceled", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }
}

