package com.example.wagba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

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
//        view.setOnClickListener(v -> {
//            if (recyclerViewInterface != null)
//            {
//                int position = orderViewHolder.getBindingAdapterPosition();
//                if (position != RecyclerView.NO_POSITION)
//                {
//                    recyclerViewInterface.onOrderClick(orderList.get(position));
//                }
//            }
//        });
        return orderViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position)
    {
        OrderModel orderModel = orderList.get(position);

        holder.OID.setText(orderModel.getOid());
        holder.orderStatus.setText("" + orderModel.getOrderStatus());
        holder.orderRestaurant.setText("" + orderModel.getRestaurantName());
        holder.orderTotalPrice.setText("" + orderModel.getOrderTotalPrice() + "EGP");
        Glide.with(context).load(orderModel.getRestaurantImage()).into(holder.restaurantImage);

    }

    @Override
    public int getItemCount()
    {
        return orderList.size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder
    {
        TextView OID, orderTotalPrice, orderStatus, orderRestaurant;
        ImageView restaurantImage;

        public OrderViewHolder(@NonNull View itemView)
        {
            super(itemView);
            OID = itemView.findViewById(R.id.order_card_oid);
            restaurantImage = itemView.findViewById(R.id.ocard_image);
            orderStatus = itemView.findViewById(R.id.ocard_order_status);
            orderTotalPrice = itemView.findViewById(R.id.ocard_order_tprice);
            orderRestaurant = itemView.findViewById(R.id.ocard_restaurant_name);

        }
    }
}
