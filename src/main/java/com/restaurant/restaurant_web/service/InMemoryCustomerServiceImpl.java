package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.CustomerModel;
import com.restaurant.restaurant_web.repository.CustomerRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryCustomerServiceImpl implements CustomerService{
    private final CustomerRepository customerRepository;

    public InMemoryCustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public int countCustomers(){
        return (int)customerRepository.count();
    }

    @Override
    public List<CustomerModel> findAllCustomer(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return customerRepository.findAll(pageable).getContent();
    }

    @Override
    public List<CustomerModel> listAllCustomer() {
        return customerRepository.findAll();
    }
    @Override
    public CustomerModel findCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public CustomerModel addCustomer(CustomerModel customer){
        return customerRepository.save(customer);
    }

    @Override
    public CustomerModel updateCustomer(CustomerModel customer){
        if (customerRepository.existsById(customer.getId())){
            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    public void deleteCustomerLogically(Long id) {
        CustomerModel customer = customerRepository.findById(id).orElse(null);
        if (customer != null) {
            customer.setDeleted(true);
            customerRepository.save(customer);
        }
    }

    @Override
    public void deleteCustomerPhysically(Long id) {
        customerRepository.deleteById(id);
    }

}
