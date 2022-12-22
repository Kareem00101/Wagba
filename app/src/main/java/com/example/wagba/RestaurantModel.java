package com.example.wagba;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;

@IgnoreExtraProperties
public class RestaurantModel implements Serializable
{
    public String restaurant_name;
    public int restaurant_rating;
    public String restaurant_image;
    public ArrayList<DishModel> restaurant_dishes;

    public RestaurantModel(String restaurant_name, int restaurant_rating, String restaurant_image, ArrayList<DishModel> restaurant_dishes)
    {
        this.restaurant_name = restaurant_name;
        this.restaurant_rating = restaurant_rating;
        this.restaurant_image = restaurant_image;
        this.restaurant_dishes = restaurant_dishes;
    }


    public String getRestaurantName()
    {
        return restaurant_name;
    }

    public int getRestaurantRating()
    {
        return restaurant_rating;
    }

    public String getRestaurantImage()
    {
        return restaurant_image;
    }

    public void setRestaurantName(String restaurant_name)
    {
        this.restaurant_name = restaurant_name;
    }

    public void setRestaurantRating(int restaurant_rating)
    {
        this.restaurant_rating = restaurant_rating;
    }

    public void setRestaurantImage(String restaurant_image)
    {
        this.restaurant_image = restaurant_image;
    }

    public ArrayList<DishModel> getRestaurant_dishes()
    {
        return restaurant_dishes;
    }

    public void setRestaurant_dishes(ArrayList<DishModel> restaurant_dishes)
    {
        this.restaurant_dishes = restaurant_dishes;
    }

    @Override
    public String toString()
    {
        return "RestaurantModel{" +
                "restaurant_name='" + restaurant_name + '\'' +
                ", restaurant_rating=" + restaurant_rating +
                ", restaurant_image='" + restaurant_image + '\'' +
                ", restaurant_dishes=" + restaurant_dishes +
                '}';
    }
}
