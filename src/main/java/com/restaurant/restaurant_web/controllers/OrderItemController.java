package com.restaurant.restaurant_web.controllers;

import com.restaurant.restaurant_web.model.MenuModel;
import com.restaurant.restaurant_web.model.OrderItemModel;
import com.restaurant.restaurant_web.service.MenuService;
import com.restaurant.restaurant_web.service.OrderItemService;
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
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/orderItems")
    public String getAllOrderItems(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "3") int size,
                                   Model model) {
        List<OrderItemModel> orderItems = orderItemService.findAllOrderItem(page, size);
        int totalOrderItems = orderItemService.countOrderItems();
        int totalPages = (int) Math.ceil((double) totalOrderItems / size);
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("orderItem", new OrderItemModel());
        model.addAttribute("orders", orderService.listAllOrder());
        model.addAttribute("menus", menuService.listAllMenu());
        return "orderItemList";
    }

    @PostMapping("/orderItems/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public String addOrderItem(@Valid @ModelAttribute("orderItem") OrderItemModel orderItem, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("orderItems", orderItemService.findAllOrderItem(0, 3));
            model.addAttribute("currentPage", 0);
            model.addAttribute("totalPages", (int) Math.ceil((double) orderItemService.countOrderItems() / 3));
            model.addAttribute("orders", orderService.listAllOrder());
            model.addAttribute("menus", menuService.listAllMenu());
            return "orderItemList";
        }
        orderItemService.addOrderItem(orderItem);
        return "redirect:/orderItems";
    }

    @PostMapping("/orderItems/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public String updateOrderItem(@ModelAttribute("orderItem") OrderItemModel orderItem) {
        orderItemService.updateOrderItem(orderItem);
        return "redirect:/orderItems";
    }

    @PostMapping("/orderItems/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'WAITER')")
    public String deleteOrderItem(@RequestParam Long id, @RequestParam(defaultValue = "false") boolean physical) {
        if (physical) {
            orderItemService.deleteOrderItemPhysically(id);
        } else {
            orderItemService.deleteOrderItemLogically(id);
        }
        return "redirect:/orderItems";
    }

    @GetMapping("/orderItems/{id}")
    public String getIdOrderItems(@PathVariable("id") Long id, Model model){
        model.addAttribute("orderItem", orderItemService.findOrderItemById(id));
        model.addAttribute("orders", orderService.listAllOrder());
        model.addAttribute("menus", menuService.listAllMenu());
        return "orderItemList";
    }


}
