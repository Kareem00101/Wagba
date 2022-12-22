package com.example.wagba;

public class DishModel
{
    public String dish_name;
    public double dish_price;
    public String dish_image;

    public DishModel(String dish_name, double dish_price, String dish_image)
    {
        this.dish_name = dish_name;
        this.dish_price = dish_price;
        this.dish_image = dish_image;
    }

    public String getDish_name()
    {
        return dish_name;
    }

    public void setDish_name(String dish_name)
    {
        this.dish_name = dish_name;
    }

    public double getDish_price()
    {
        return dish_price;
    }

    public void setDish_price(double dish_price)
    {
        this.dish_price = dish_price;
    }

    public String getDish_image()
    {
        return dish_image;
    }

    public void setDish_image(String dish_image)
    {
        this.dish_image = dish_image;
    }

    @Override
    public String toString()
    {
        return "DishModel{" +
                "dish_name='" + dish_name + '\'' +
                ", dish_price=" + dish_price +
                ", dish_image='" + dish_image + '\'' +
                '}';
    }
}
