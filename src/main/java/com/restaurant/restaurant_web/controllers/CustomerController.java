package com.restaurant.restaurant_web.controllers;

import com.restaurant.restaurant_web.model.CustomerModel;
import com.restaurant.restaurant_web.model.OrderModel;
import com.restaurant.restaurant_web.service.CustomerService;
import com.restaurant.restaurant_web.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/customers")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public String getAllCustomers(@RequestParam(defaultValue = "0") int page,
                                  @RequestParam(defaultValue = "3") int size,
                                  Model model) {
        List<CustomerModel> customers = customerService.findAllCustomer(page, size);
        int totalCustomers = customerService.countCustomers();
        int totalPages = (int) Math.ceil((double) totalCustomers / size);
        model.addAttribute("customers", customers);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("customer", new CustomerModel());
        model.addAttribute("orders", orderService.listAllOrder());
        return "customerList";
    }

    @PostMapping("/customers/add")
    public String addCustomer(@Valid @ModelAttribute("customer") CustomerModel customer, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("customers", customerService.findAllCustomer(0,3));
            model.addAttribute("currentPage", 0);
            model.addAttribute("totalPages", (int) Math.ceil((double) customerService.countCustomers() / 3));
            model.addAttribute("orders", orderService.listAllOrder());
            return "customerList";
        }
        customerService.addCustomer(customer);
        return "redirect:/customers";
    }

    @PostMapping("/customers/update")
    public String updateCustomer(@ModelAttribute("customer") CustomerModel customer) {
        customerService.updateCustomer(customer);
        return "redirect:/customers";
    }

    @PostMapping("/customers/delete")
    public String deleteCustomer(@RequestParam Long id, @RequestParam(defaultValue = "false") boolean physical) {
        if (physical) {
            customerService.deleteCustomerPhysically(id);
        } else {
            customerService.deleteCustomerLogically(id);
        }
        return "redirect:/customers";
    }

    @GetMapping("/customers/{id}")
    public String getIdCustomers(@PathVariable("id") Long id, Model model){
        model.addAttribute("customer", customerService.findCustomerById(id));
        model.addAttribute("orders", orderService.listAllOrder());
        return "customerList";
    }

}
