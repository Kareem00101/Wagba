package com.example.wagbaadmin;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

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

    }

    @Override
    public int getItemCount()
    {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder
    {
        TextView OID, UID, orderTotalPrice, orderStatus, orderRestaurant, orderGate, orderPeriod, orderedDishes, orderDate;
        Button advanceOrderState;
        Button cancelOrder;
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
            advanceOrderState = itemView.findViewById(R.id.ocard_btn_advance);
            cancelOrder = itemView.findViewById(R.id.ocard_btn_cancel);

            advanceOrderState.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    for (int i = 0; i < orderStates.length; i++)
                    {
                        if(i == 3)
                        {
                            Toast.makeText(v.getContext(), "Order is already delivered", Toast.LENGTH_SHORT).show();
                            break;
                        }

                        String currentState = orderStatus.getText().toString();
                        if(currentState.equals(orderStates[i]))
                        {
                            orderStatus.setText(orderStates[i + 1]);
                            Toast.makeText(itemView.getContext(), "Order State Set to: "+orderStates[i+1], Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }


                }
            });

            cancelOrder.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    advanceOrderState.setVisibility(View.GONE);
                    orderStatus.setText("Canceled");
                    Toast.makeText(itemView.getContext(), "Order Cancelled", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

