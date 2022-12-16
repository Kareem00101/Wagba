package com.example.wagba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;



public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>
{
    private ArrayList<RestaurantModel> restaurantList;
    private Context context;

    public RestaurantAdapter(ArrayList<RestaurantModel> restaurantList, Context context)
    {
        this.restaurantList = restaurantList;
        this.context = context;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_card, parent, false);
        RestaurantViewHolder restaurantViewHolder = new RestaurantViewHolder(view);
        return restaurantViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position)
    {
        RestaurantModel restaurantModel = restaurantList.get(position);
        holder.restaurantName.setText(restaurantModel.getRestaurantName());
        holder.restaurantRating.setText("" + restaurantModel.getRestaurantRating());
        holder.restaurantImage.setImageResource(restaurantModel.getRestaurantImage());
    }

    @Override
    public int getItemCount()
    {
        return restaurantList.size();
    }

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder
    {
        TextView restaurantName;
        TextView restaurantRating;
        ImageView restaurantImage;

        public RestaurantViewHolder(@NonNull View itemView)
        {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.restaurant_name);
            restaurantRating = itemView.findViewById(R.id.restaurant_rating);
            restaurantImage = itemView.findViewById(R.id.restaurant_image);
        }
    }
}
