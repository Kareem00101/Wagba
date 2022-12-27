package com.example.wagba;

import java.io.Serializable;

public class DishModel implements Serializable
{
    public String dish_name;
    public double dish_price;
    public String dish_image;
    public int dish_quantity = 0;

    public DishModel(String dish_name, double dish_price, String dish_image)
    {
        this.dish_name = dish_name;
        this.dish_price = dish_price;
        this.dish_image = dish_image;
    }

    public String getDishName()
    {
        return dish_name;
    }

    public void setDishName(String dish_name)
    {
        this.dish_name = dish_name;
    }

    public double getDishPrice()
    {
        return dish_price;
    }

    public void setDishPrice(double dish_price)
    {
        this.dish_price = dish_price;
    }

    public String getDishImage()
    {
        return dish_image;
    }

    public void setDishImage(String dish_image)
    {
        this.dish_image = dish_image;
    }

    public int getDishQuantity()
    {
        return dish_quantity;
    }

    public void setDishQuantity(int dish_quantity)
    {
        this.dish_quantity = dish_quantity;
    }

    @Override
    public String toString() {
        return "DishModel{" +
                "dish_name='" + dish_name + '\'' +
                ", dish_price=" + dish_price +
                ", dish_image='" + dish_image + '\'' +
                '}';
    }
}
