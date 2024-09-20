package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.PaymentModel;

import java.util.List;

public interface PaymentService {
    public List<PaymentModel> findAllPayment(int page, int size);
    public List<PaymentModel> listAllPayment();
    public int countPayments();
    public PaymentModel findPaymentById(Long id);
    public PaymentModel addPayment(PaymentModel payment);
    public PaymentModel updatePayment(PaymentModel payment);
    public void deletePaymentLogically(Long id);
    public void deletePaymentPhysically(Long id);
}
