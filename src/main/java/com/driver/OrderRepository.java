package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.util.Objects.isNull;

@Repository
public class OrderRepository {

    HashMap<String,Order> orderHashMap=new HashMap<>();
    HashMap<String,DeliveryPartner> partnerHm=new HashMap<>();
    HashMap<String, List<String>> pairHm=new HashMap<>();
    HashMap<String, String> pairHmOrder=new HashMap<>();
    public void addOrderRepo(Order order) {
        orderHashMap.put(order.getId(),order);
    }

    public void addPartnerRepo(String partnerId) {
        partnerHm.put(partnerId,new DeliveryPartner(partnerId));
    }

    public void addOrderPartnerPairServiceRepo(String orderId, String partnerId) {
        List<String> list;
        if(pairHm.containsKey(partnerId)){
             list=pairHm.get(partnerId);

        }
        else {
             list=new ArrayList<>();

        }


        list.add(orderId);
        pairHm.put(partnerId,list);
        pairHmOrder.put(orderId,partnerId);
        if (partnerHm.containsKey(partnerId)) {
            DeliveryPartner deliveryPartner = partnerHm.get(partnerId);
            deliveryPartner.setNumberOfOrders(list.size());
        }


    }

    public Order getOrderByIdServiceRepo(String orderId) {
        if(orderHashMap.containsKey(orderId)){
            return orderHashMap.get(orderId);
        }
        else{
            return null;
        }
    }

    public DeliveryPartner getPartnerByIdRepo(String partnerId) {
        if(partnerHm.containsKey(partnerId)){
            return partnerHm.get(partnerId);
        }
        else{
            return null;
        }
    }

    public Integer getOrderCountByPartnerIdRepo(String partnerId) {
        if(partnerHm.containsKey(partnerId)){
            DeliveryPartner partner=partnerHm.get(partnerId);
            return partner.getNumberOfOrders();
        }
        return 0;
    }

    public List<String> getOrdersByPartnerIdRepo(String partnerId) {
        List<String >list=new ArrayList<>();
        if(pairHm.containsKey(partnerId)){

            return pairHm.get(partnerId);

        }
        return list;
    }

    public List<String> getAllOrdersRepo() {
        List<String> list= new ArrayList<>();
        if (!orderHashMap.isEmpty()){

            for (String s:
                    orderHashMap.keySet()) {
                list.add(s);
                
            }

        }
        return list;
    }

    public Integer getCountOfUnassignedOrdersRepo() {
        return (orderHashMap.size()-pairHmOrder.size());
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerIdRepo(String time, String partnerId) {
        int count=0;
        String ans []=time.split(":",2);
        int HH=Integer.valueOf(ans[0]);
        int MM=Integer.valueOf(ans[1]);
        int timeCurr=HH*60+MM;
        if (pairHm.containsKey(partnerId)){
            List<String> list = pairHm.get(partnerId);
            for (String s : list
            ) {
                Order order = orderHashMap.get(s);
                if (order.getDeliveryTime() > timeCurr) {
                    count++;
                }
            }
        }
        return count;
    }

    public String getLastDeliveryTimeByPartnerIdRepo(String partnerId) {
        int time=0;
        String ans="";
        if (pairHm.containsKey(partnerId)){
            List<String> list = pairHm.get(partnerId);

            for (String s : list
            ) {
                Order order = orderHashMap.get(s);
                if (order.getDeliveryTime() > time) {
                    time=order.getDeliveryTime();
                }
            }
        }
        if (time>0){

            int MM=time%60;
            int HH=(time-MM)/60;
            ans=String.valueOf(HH)+":"+String.valueOf(MM);
        }
        return ans;
    }

    public void deletePartnerByIdRepo(String partnerId) {
        if (pairHm.containsKey(partnerId)){
            pairHm.remove(partnerId);
            partnerHm.remove(partnerId);
        }

    }

    public void deleteOrderByIdRepo(String orderId) {
        String partnerid=pairHmOrder.get(orderId);
        if (!isNull(partnerid)){
            if(pairHm.containsKey(partnerid)){
                List<String> list= pairHm.get(partnerid);
                for (String s:list
                     ) {
                    if(s.equals(orderId)){
                        list.remove(orderId);
                    }
                }
            }

        }
        orderHashMap.remove(orderId);
    }
}
