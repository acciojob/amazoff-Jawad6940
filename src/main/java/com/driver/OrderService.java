package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public void addOrderService (Order order){
            orderRepository.addOrderRepo(order);
    }

    public void addPartnerService(String partnerId) {
        orderRepository.addPartnerRepo(partnerId);
    }

    public void addOrderPartnerPairService(String orderId, String partnerId) {
        orderRepository.addOrderPartnerPairServiceRepo(orderId,partnerId);
    }

    public Order getOrderByIdService(String orderId) {
        return orderRepository.getOrderByIdServiceRepo(orderId);
    }

    public DeliveryPartner getPartnerByIdService(String partnerId) {
        return orderRepository.getPartnerByIdRepo(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return orderRepository.getOrderCountByPartnerIdRepo(partnerId);
    }

    public List<String> getOrdersByPartnerIdService(String partnerId) {
        return orderRepository.getOrdersByPartnerIdRepo(partnerId);
    }

    public List<String> getAllOrdersService() {
       return orderRepository.getAllOrdersRepo();
    }

    public Integer getCountOfUnassignedOrders() {
        return orderRepository.getCountOfUnassignedOrdersRepo();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerIdRepo(time,partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {
        return orderRepository.getLastDeliveryTimeByPartnerIdRepo(partnerId);
    }

    public void deletePartnerById(String partnerId) {
        orderRepository.deletePartnerByIdRepo(partnerId);
    }

    public void deleteOrderById(String orderId) {
        orderRepository.deleteOrderByIdRepo(orderId);
    }
}
