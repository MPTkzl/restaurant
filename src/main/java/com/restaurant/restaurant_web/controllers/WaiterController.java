package com.restaurant.restaurant_web.controllers;

import com.restaurant.restaurant_web.model.WaiterModel;
import com.restaurant.restaurant_web.service.WaiterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class WaiterController {

    @Autowired
    private WaiterService waiterService;

    @GetMapping("/waiters")
    public String getAllWaiters(@RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "3") int size,
                                Model model) {
        List<WaiterModel> waiters = waiterService.findAllWaiter(page, size);
        int totalWaiters = waiterService.countWaiters();
        int totalPages = (int) Math.ceil((double) totalWaiters / size);
        model.addAttribute("waiters", waiters);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("waiter", new WaiterModel());
        return "waiterList";
    }

    @PostMapping("/waiters/add")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String addWaiter(@Valid @ModelAttribute("waiter") WaiterModel waiter, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("waiters", waiterService.findAllWaiter(0, 3));
            model.addAttribute("currentPage", 0);
            model.addAttribute("totalPages", (int) Math.ceil((double) waiterService.countWaiters() / 3));
            return "waiterList";
        }
        waiterService.addWaiter(waiter);
        return "redirect:/waiters";
    }

    @PostMapping("/waiters/update")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String updateWaiter(@ModelAttribute("waiter") WaiterModel waiter) {
        waiterService.updateWaiter(waiter);
        return "redirect:/waiters";
    }

    @PostMapping("/waiters/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteWaiter(@RequestParam Long id, @RequestParam(defaultValue = "false") boolean physical) {
        if (physical) {
            waiterService.deleteWaiterPhysically(id);
        } else {
            waiterService.deleteWaiterLogically(id);
        }
        return "redirect:/waiters";
    }

    @GetMapping("/waiters/{id}")
    public String getIdWaiters(@PathVariable("id") Long id, Model model){
        model.addAttribute("waiter", waiterService.findWaiterById(id));
        return "waiterList";
    }

}
