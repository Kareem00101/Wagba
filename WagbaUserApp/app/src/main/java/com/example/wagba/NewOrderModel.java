package com.example.wagba;

import java.io.Serializable;
import java.util.ArrayList;

public class NewOrderModel implements Serializable {
    public String orderID;
    public String userID;
    public String restaurantName;
    public String restaurantImage;
    public String orderStatus;
    public String orderGate;
    public String orderPeriod;
    public Double totalPrice;
    public Double itemsPrice;
    public Double deliveryCost;
    public ArrayList<DishModel> orderedDishes;

    public String orderDate;

    public NewOrderModel(String orderID, String userID, String restaurantName, String restaurantImage, String orderStatus, String orderGate, String orderPeriod, Double totalPrice, Double itemsPrice, Double deliveryCost, ArrayList<DishModel> orderedDishes, String dateString) {
        this.orderID = orderID;
        this.userID = userID;
        this.restaurantName = restaurantName;
        this.restaurantImage = restaurantImage;
        this.orderStatus = orderStatus;
        this.orderGate = orderGate;
        this.orderPeriod = orderPeriod;
        this.totalPrice = totalPrice;
        this.itemsPrice = itemsPrice;
        this.deliveryCost = deliveryCost;
        this.orderedDishes = orderedDishes;
        this.orderDate = dateString;
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

    public Double getTotalPrice()
    {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice)
    {
        this.totalPrice = totalPrice;
    }

    public Double getItemsPrice()
    {
        return itemsPrice;
    }

    public void setItemsPrice(Double itemsPrice)
    {
        this.itemsPrice = itemsPrice;
    }

    public Double getDeliveryCost()
    {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost)
    {
        this.deliveryCost = deliveryCost;
    }

    public ArrayList<DishModel> getOrderedDishes()
    {
        return orderedDishes;
    }

    public void setOrderedDishes(ArrayList<DishModel> orderedDishes)
    {
        this.orderedDishes = orderedDishes;
    }

    public String getOrderDate()
    {
        return orderDate;
    }

    public void setOrderDate(String orderDate)
    {
        this.orderDate = orderDate;
    }

    @Override
    public String toString()
    {
        return "NewOrderModel{" +
                "orderID='" + orderID + '\'' +
                ", userID='" + userID + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", restaurantImage='" + restaurantImage + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                ", orderGate='" + orderGate + '\'' +
                ", orderPeriod='" + orderPeriod + '\'' +
                ", totalPrice=" + totalPrice +
                ", itemsPrice=" + itemsPrice +
                ", deliveryCost=" + deliveryCost +
                ", orderedDishes=" + orderedDishes +
                ", dateString='" + orderDate + '\'' +
                '}';
    }
}
