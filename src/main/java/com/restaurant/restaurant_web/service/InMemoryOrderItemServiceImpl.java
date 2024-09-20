package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.OrderItemModel;
import com.restaurant.restaurant_web.repository.OrderItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryOrderItemServiceImpl implements OrderItemService{
    private final OrderItemRepository orderItemRepository;

    public InMemoryOrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }

    @Override
    public int countOrderItems(){
        return (int)orderItemRepository.count();
    }

    @Override
    public List<OrderItemModel> findAllOrderItem(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return orderItemRepository.findAll(pageable).getContent();
    }

    @Override
    public List<OrderItemModel> listAllOrderItem() {
        return orderItemRepository.findAll();
    }
    @Override
    public OrderItemModel findOrderItemById(Long id) {
        return orderItemRepository.findById(id).orElse(null);
    }

    @Override
    public OrderItemModel addOrderItem(OrderItemModel orderItem){
        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItemModel updateOrderItem(OrderItemModel orderItem){
        if (orderItemRepository.existsById(orderItem.getId())){
            return orderItemRepository.save(orderItem);
        }
        return null;
    }

    @Override
    public void deleteOrderItemLogically(Long id) {
        OrderItemModel orderItem = orderItemRepository.findById(id).orElse(null);
        if (orderItem != null) {
            orderItem.setDeleted(true);
            orderItemRepository.save(orderItem);
        }
    }

    @Override
    public void deleteOrderItemPhysically(Long id) {
        orderItemRepository.deleteById(id);
    }

}
