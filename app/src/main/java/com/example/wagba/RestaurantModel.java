package com.example.wagba;

public class RestaurantModel
{
    private String restaurant_name;
    private int restaurant_rating;
    private int restaurant_image;

    public RestaurantModel(String restaurant_name, int restaurant_rating, int restaurant_image)
    {
        this.restaurant_name = restaurant_name;
        this.restaurant_rating = restaurant_rating;
        this.restaurant_image = restaurant_image;
    }

    public String getRestaurantName()
    {
        return restaurant_name;
    }

    public int getRestaurantRating()
    {
        return restaurant_rating;
    }

    public int getRestaurantImage()
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

    public void setRestaurantImage(int restaurant_image)
    {
        this.restaurant_image = restaurant_image;
    }
}
