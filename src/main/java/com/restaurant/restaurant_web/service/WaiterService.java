package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.WaiterModel;

import java.util.List;

public interface WaiterService {
    public List<WaiterModel> findAllWaiter(int page, int size);
    public List<WaiterModel> listAllWaiter();
    public int countWaiters();
    public WaiterModel findWaiterById(Long id);
    public WaiterModel addWaiter(WaiterModel waiter);
    public WaiterModel updateWaiter(WaiterModel waiter);
    public void deleteWaiterLogically(Long id);
    public void deleteWaiterPhysically(Long id);
}
