package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.CustomerModel;

import java.util.List;

public interface CustomerService {
    public List<CustomerModel> findAllCustomer(int page, int size);
    public List<CustomerModel> listAllCustomer();
    public int countCustomers();
    public CustomerModel findCustomerById(Long id);
    public CustomerModel addCustomer(CustomerModel customer);
    public CustomerModel updateCustomer(CustomerModel customer);
    public void deleteCustomerLogically(Long id);
    public void deleteCustomerPhysically(Long id);
}
