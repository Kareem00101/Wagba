package com.example.wagba;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderModel implements Serializable
{
    public String orderID;
    public String userID;
    public String restaurantName;
    public String orderStatus;
    public String orderGate;
    public String orderPeriod;
    public Double totalPrice;
    public Double itemsPrice;
    public Double deliveryCost;
    public ArrayList<DishModel> orderedDishes;

    public OrderModel(String orderID, String userID, String restaurantName, String orderStatus, String orderGate, String orderPeriod, Double totalPrice, Double itemsPrice, Double deliveryCost, ArrayList<DishModel> orderedDishes)
    {
        this.orderID = orderID;
        this.userID = userID;
        this.restaurantName = restaurantName;
        this.orderStatus = orderStatus;
        this.orderGate = orderGate;
        this.orderPeriod = orderPeriod;
        this.totalPrice = totalPrice;
        this.itemsPrice = itemsPrice;
        this.deliveryCost = deliveryCost;
        this.orderedDishes = orderedDishes;
    }
}
