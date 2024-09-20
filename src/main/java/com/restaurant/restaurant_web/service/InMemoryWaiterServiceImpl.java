package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.WaiterModel;
import com.restaurant.restaurant_web.repository.WaiterRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryWaiterServiceImpl implements WaiterService{
    private final WaiterRepository waiterRepository;

    public InMemoryWaiterServiceImpl(WaiterRepository waiterRepository) {
        this.waiterRepository = waiterRepository;
    }

    @Override
    public int countWaiters(){
        return (int)waiterRepository.count();
    }

    @Override
    public List<WaiterModel> findAllWaiter(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return waiterRepository.findAll(pageable).getContent();
    }

    @Override
    public List<WaiterModel> listAllWaiter() {
        return waiterRepository.findAll();
    }
    @Override
    public WaiterModel findWaiterById(Long id) {
        return waiterRepository.findById(id).orElse(null);
    }

    @Override
    public WaiterModel addWaiter(WaiterModel waiter){
        return waiterRepository.save(waiter);
    }

    @Override
    public WaiterModel updateWaiter(WaiterModel waiter){
        if (waiterRepository.existsById(waiter.getId())){
            return waiterRepository.save(waiter);
        }
        return null;
    }

    @Override
    public void deleteWaiterLogically(Long id) {
        WaiterModel waiter = waiterRepository.findById(id).orElse(null);
        if (waiter != null) {
            waiter.setDeleted(true);
            waiterRepository.save(waiter);
        }
    }

    @Override
    public void deleteWaiterPhysically(Long id) {
        waiterRepository.deleteById(id);
    }

}
