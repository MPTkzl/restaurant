package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.OrderModel;
import com.restaurant.restaurant_web.repository.OrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryOrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;

    public InMemoryOrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public int countOrders(){
        return (int)orderRepository.count();
    }

    @Override
    public List<OrderModel> findAllOrder(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return orderRepository.findAll(pageable).getContent();
    }

    @Override
    public List<OrderModel> listAllOrder() {
        return orderRepository.findAll();
    }
    @Override
    public OrderModel findOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public OrderModel addOrder(OrderModel order){
        return orderRepository.save(order);
    }

    @Override
    public OrderModel updateOrder(OrderModel order){
        if (orderRepository.existsById(order.getId())){
            return orderRepository.save(order);
        }
        return null;
    }

    @Override
    public void deleteOrderLogically(Long id) {
        OrderModel order = orderRepository.findById(id).orElse(null);
        if (order != null) {
            order.setDeleted(true);
            orderRepository.save(order);
        }
    }

    @Override
    public void deleteOrderPhysically(Long id) {
        orderRepository.deleteById(id);
    }

}
