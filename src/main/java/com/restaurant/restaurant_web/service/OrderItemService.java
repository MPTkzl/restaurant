package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.OrderItemModel;

import java.util.List;

public interface OrderItemService {
    public List<OrderItemModel> findAllOrderItem(int page, int size);
    public List<OrderItemModel> listAllOrderItem();
    public int countOrderItems();
    public OrderItemModel findOrderItemById(Long id);
    public OrderItemModel addOrderItem(OrderItemModel orderItem);
    public OrderItemModel updateOrderItem(OrderItemModel orderItem);
    public void deleteOrderItemLogically(Long id);
    public void deleteOrderItemPhysically(Long id);
}
