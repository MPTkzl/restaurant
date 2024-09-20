package com.restaurant.restaurant_web.controllers;

import com.restaurant.restaurant_web.model.PaymentModel;
import com.restaurant.restaurant_web.service.PaymentService;
import com.restaurant.restaurant_web.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/payments")
    public String getAllPayments(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "3") int size,
                                 Model model) {
        List<PaymentModel> payments = paymentService.findAllPayment(page, size);
        int totalPayments = paymentService.countPayments();
        int totalPages = (int) Math.ceil((double) totalPayments / size);
        model.addAttribute("payments", payments);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("payment", new PaymentModel());
        model.addAttribute("orders", orderService.listAllOrder());
        return "paymentList";
    }

    @PostMapping("/payments/add")
    public String addPayment(@Valid @ModelAttribute("payment") PaymentModel payment, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("payments", paymentService.findAllPayment(0,3));
            model.addAttribute("currentPage", 0);
            model.addAttribute("totalPages", (int) Math.ceil((double) paymentService.countPayments() / 3));
            model.addAttribute("orders", orderService.listAllOrder());
            return "paymentList";
        }
        paymentService.addPayment(payment);
        return "redirect:/payments";
    }

    @PostMapping("/payments/update")
    public String updatePayment(@ModelAttribute("payment") PaymentModel payment) {
        paymentService.updatePayment(payment);
        return "redirect:/payments";
    }

    @PostMapping("/payments/delete")
    public String deletePayment(@RequestParam Long id, @RequestParam(defaultValue = "false") boolean physical) {
        if (physical) {
            paymentService.deletePaymentPhysically(id);
        } else {
            paymentService.deletePaymentLogically(id);
        }
        return "redirect:/payments";
    }

    @GetMapping("/payments/{id}")
    public String getIdPayments(@PathVariable("id") Long id, Model model){
        model.addAttribute("payment", paymentService.findPaymentById(id));
        model.addAttribute("orders", orderService.listAllOrder());
        return "paymentList";
    }

}
