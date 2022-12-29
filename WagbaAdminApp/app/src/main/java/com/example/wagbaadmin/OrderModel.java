package com.example.wagbaadmin;

import java.io.Serializable;

public class OrderModel implements Serializable
{
    public String orderID;
    public String userID;
    public String restaurantName;

    public String orderGate;
    public String orderPeriod;

    public String orderStatus;
    public String orderTotalPrice;

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderedDishes() {
        return orderedDishes;
    }

    public void setOrderedDishes(String orderedDishes) {
        this.orderedDishes = orderedDishes;
    }

    public String orderDate;
    public String orderedDishes;

    public OrderModel(String orderID, String userID, String restaurantName, String orderStatus, String orderGate, String orderPeriod, String totalPrice, String dateString, String orderedDishes)
    {
        this.orderID = orderID;
        this.userID = userID;
        this.restaurantName = restaurantName;
        this.orderStatus = orderStatus;
        this.orderGate = orderGate;
        this.orderPeriod = orderPeriod;
        this.orderTotalPrice = String.valueOf(totalPrice);
        this.orderDate = dateString;
        this.orderedDishes = orderedDishes;
    }

    public String getOrderID()
    {
        return orderID;
    }

    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }

    public String getUserID()
    {
        return userID;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public String getRestaurantName()
    {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName)
    {
        this.restaurantName = restaurantName;
    }

    public String getOrderGate()
    {
        return orderGate;
    }

    public void setOrderGate(String orderGate)
    {
        this.orderGate = orderGate;
    }

    public String getOrderPeriod()
    {
        return orderPeriod;
    }

    public void setOrderPeriod(String orderPeriod)
    {
        this.orderPeriod = orderPeriod;
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
                "orderID='" + orderID + '\'' +
                ", userID='" + userID + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", orderGate='" + orderGate + '\'' +
                ", orderPeriod='" + orderPeriod + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderTotalPrice='" + orderTotalPrice + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", orderedDishes='" + orderedDishes + '\'' +
                '}';
    }
}

