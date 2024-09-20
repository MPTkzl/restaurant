package com.restaurant.restaurant_web.controllers;

import com.restaurant.restaurant_web.model.OrderModel;
import com.restaurant.restaurant_web.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private TableService tableService;

    @Autowired
    private WaiterService waiterService;

    @GetMapping("/orders")
    public String getAllOrders(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "3") int size,
                               Model model) {
        List<OrderModel> orders = orderService.findAllOrder(page, size);
        int totalOrders = orderService.countOrders();
        int totalPages = (int) Math.ceil((double) totalOrders / size);
        model.addAttribute("orders", orders);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("order", new OrderModel());
        model.addAttribute("customers", customerService.listAllCustomer());
        model.addAttribute("tables", tableService.listAllTable());
        model.addAttribute("waiters", waiterService.listAllWaiter());
        return "orderList";
    }

    @PostMapping("/orders/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public String addOrder(@Valid @ModelAttribute("order") OrderModel order, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("orders", orderService.findAllOrder(0,3));
            model.addAttribute("currentPage", 0);
            model.addAttribute("totalPages", (int) Math.ceil((double) orderService.countOrders() / 3));
            model.addAttribute("customers", customerService.listAllCustomer());
            model.addAttribute("tables", tableService.listAllTable());
            model.addAttribute("waiters", waiterService.listAllWaiter());
            return "orderList";
        }
        orderService.addOrder(order);
        return "redirect:/orders";
    }

    @PostMapping("/orders/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public String updateOrder(@ModelAttribute("order") OrderModel order) {
        orderService.updateOrder(order);
        return "redirect:/orders";
    }

    @PostMapping("/orders/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public String deleteOrder(@RequestParam Long id, @RequestParam(defaultValue = "false") boolean physical) {
        if (physical) {
            orderService.deleteOrderPhysically(id);
        } else {
            orderService.deleteOrderLogically(id);
        }
        return "redirect:/orders";
    }

    @GetMapping("/orders/{id}")
    public String getIdOrders(@PathVariable("id") Long id, Model model){
        model.addAttribute("order", orderService.findOrderById(id));
        model.addAttribute("customers", customerService.listAllCustomer());
        model.addAttribute("tables", tableService.listAllTable());
        model.addAttribute("waiters", waiterService.listAllWaiter());
        return "orderList";
    }

}
