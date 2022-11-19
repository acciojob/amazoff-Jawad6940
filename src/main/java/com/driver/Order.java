package com.driver;

import org.springframework.stereotype.Component;


public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        String ans []=deliveryTime.split(":",2);
        int HH=Integer.valueOf(ans[0]);
        int MM=Integer.valueOf(ans[1]);
        this.deliveryTime=HH*60+MM;
        this.id=id;
//        System.out.println(this.id+" " +this.deliveryTime);

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }



    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", deliveryTime=" + deliveryTime +
                '}';
    }

}
