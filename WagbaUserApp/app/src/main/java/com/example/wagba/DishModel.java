package com.example.wagba;

import java.io.Serializable;

public class DishModel implements Serializable
{
    public String dishName;
    public double dishPrice;
    public String dishImage;
    public int dishQuantity = 0;

    public DishModel(String dish_name, double dish_price, String dish_image)
    {
        this.dishName = dish_name;
        this.dishPrice = dish_price;
        this.dishImage = dish_image;
    }

    public String getDishName()
    {
        return dishName;
    }

    public void setDishName(String dish_name)
    {
        this.dishName = dish_name;
    }

    public double getDishPrice()
    {
        return dishPrice;
    }

    public void setDishPrice(double dish_price)
    {
        this.dishPrice = dish_price;
    }

    public String getDishImage()
    {
        return dishImage;
    }

    public void setDishImage(String dish_image)
    {
        this.dishImage = dish_image;
    }

    public int getDishQuantity()
    {
        return dishQuantity;
    }

    public void setDishQuantity(int dish_quantity)
    {
        this.dishQuantity = dish_quantity;
    }

    @Override
    public String toString() {
        return "DishModel{" +
                "dish_name='" + dishName + '\'' +
                ", dish_price=" + dishPrice +
                ", dish_image='" + dishImage + '\'' +
                '}';
    }
}
