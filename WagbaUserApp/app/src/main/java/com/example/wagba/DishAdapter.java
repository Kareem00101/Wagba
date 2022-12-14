package com.example.wagba;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder>
{
    public ArrayList<DishModel> dishList;
    public Context context;

    public DishAdapter(ArrayList<DishModel> dishList, Context context)
    {
        this.dishList = dishList;
        this.context = context;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dish_card, parent, false);
        DishViewHolder dishViewHolder = new DishViewHolder(view, this);
        return dishViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DishViewHolder holder, int position)
    {
        DishModel dishModel = dishList.get(position);

        holder.dishName.setText(dishModel.getDishName());
        holder.dishPrice.setText(String.valueOf(dishModel.getDishPrice()));
        Glide.with(context).load(dishModel.getDishImage()).into(holder.dishImage);

        holder.noOfItems.setText(String.valueOf(dishModel.getDishQuantity()));

    }

    @Override
    public int getItemCount()
    {
        return dishList.size();
    }

    public ArrayList<DishModel> getOrderedDishes()
    {
        ArrayList<DishModel> orderedDishes = new ArrayList<>();

        for (DishModel dish : dishList)
        {
            if (dish.getDishQuantity() > 0)
            {
                orderedDishes.add(dish);
            }
        }
        return orderedDishes;
    }

    public static class DishViewHolder extends RecyclerView.ViewHolder
    {
        TextView dishName;
        TextView dishPrice;
        ImageView dishImage;

        TextView noOfItems;
        ImageFilterButton incBtn;
        ImageFilterButton decBtn;

        public DishViewHolder(@NonNull View itemView, DishAdapter dishAdapter)
        {
            super(itemView);

            dishName = itemView.findViewById(R.id.dish_card_title_txt);
            dishPrice = itemView.findViewById(R.id.dish_card_dish_price);
            dishImage = itemView.findViewById(R.id.dish_card_image);

            incBtn = itemView.findViewById(R.id.dish_increment_btn);
            decBtn = itemView.findViewById(R.id.dish_decrement_btn);
            noOfItems = itemView.findViewById(R.id.dish_quantity);


            if(dishAdapter.context.getClass().getSimpleName().equals("CartActivity"))
            {
                incBtn.setVisibility(View.GONE);
                decBtn.setVisibility(View.GONE);

                //DishModel currentDish = dishAdapter.dishList.get(getBindingAdapterPosition());
                //noOfItems.setText(currentDish.getDish_quantity() + "");
            }


            incBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int currentQuantity = Integer.parseInt(noOfItems.getText().toString());
                    currentQuantity++;
                    noOfItems.setText(String.valueOf(currentQuantity));
                    dishAdapter.dishList.get(getBindingAdapterPosition()).setDishQuantity(currentQuantity);
                }
            });

            decBtn.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int currentQuantity = Integer.parseInt(noOfItems.getText().toString());
                    if (currentQuantity > 0)
                    {
                        currentQuantity--;
                        noOfItems.setText(String.valueOf(currentQuantity));
                    }
                    dishAdapter.dishList.get(getBindingAdapterPosition()).setDishQuantity(currentQuantity);
                }
            });
        }
    }
}
