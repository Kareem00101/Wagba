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

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder>
{
    private ArrayList<RestaurantModel> dishList;
    private Context context;

    public DishAdapter(ArrayList<RestaurantModel> dishList, Context context)
    {
        this.dishList = dishList;
        this.context = context;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_card, parent, false);
        DishViewHolder dishViewHolder = new DishViewHolder(view);
        return dishViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position)
    {
        RestaurantModel dishModel = dishList.get(position);
        holder.dishName.setText(dishModel.getRestaurant_dishes().get(position).getDish_name());
        holder.dishPrice.setText(String.valueOf(dishModel.getRestaurant_dishes().get(position).getDish_price()));
        Glide.with(context).load(dishModel.getRestaurant_dishes().get(position).getDish_image()).into(holder.dishImage);

    }

    @Override
    public int getItemCount()
    {
        return dishList.size();
    }

    public static class DishViewHolder extends RecyclerView.ViewHolder
    {
        TextView dishName;
        TextView dishPrice;
        ImageView dishImage;

        public DishViewHolder(@NonNull View itemView)
        {
            super(itemView);
            dishName = itemView.findViewById(R.id.dish_card_title_txt);
            dishPrice = itemView.findViewById(R.id.dish_card_dish_price);
            dishImage = itemView.findViewById(R.id.dish_card_image);
        }
    }
}
