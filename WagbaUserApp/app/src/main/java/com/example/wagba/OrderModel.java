package com.example.wagba;

import java.io.Serializable;

public class OrderModel implements Serializable
{
    public String oid;
    public String restaurantName;
    public String restaurantImage;
    public String orderStatus;
    public String orderTotalPrice;

    public OrderModel(String oid, String restaurantName, String restaurantImage, String orderStatus, String orderTotalPrice)
    {
        this.oid = oid;
        this.restaurantName = restaurantName;
        this.restaurantImage = restaurantImage;
        this.orderStatus = orderStatus;
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getOid()
    {
        return oid;
    }

    public void setOid(String oid)
    {
        this.oid = oid;
    }

    public String getRestaurantName()
    {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName)
    {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantImage()
    {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage)
    {
        this.restaurantImage = restaurantImage;
    }

    public String getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public String getOrderTotalPrice()
    {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(String orderTotalPrice)
    {
        this.orderTotalPrice = orderTotalPrice;
    }

    @Override
    public String toString()
    {
        return "OrderModel{" +
                "oid='" + oid + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantImage='" + restaurantImage + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderTotalPrice='" + orderTotalPrice + '\'' +
                '}';
    }
}
