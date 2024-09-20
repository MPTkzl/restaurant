package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.OrderModel;

import java.util.List;

public interface OrderService {
    public List<OrderModel> findAllOrder(int page, int size);
    public List<OrderModel> listAllOrder();
    public int countOrders();
    public OrderModel findOrderById(Long id);
    public OrderModel addOrder(OrderModel order);
    public OrderModel updateOrder(OrderModel order);
    public void deleteOrderLogically(Long id);
    public void deleteOrderPhysically(Long id);
}
