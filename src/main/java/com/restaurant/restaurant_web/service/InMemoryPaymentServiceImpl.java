package com.restaurant.restaurant_web.service;

import com.restaurant.restaurant_web.model.PaymentModel;
import com.restaurant.restaurant_web.repository.PaymentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryPaymentServiceImpl implements PaymentService{
    private final PaymentRepository paymentRepository;

    public InMemoryPaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public int countPayments(){
        return (int)paymentRepository.count();
    }

    @Override
    public List<PaymentModel> findAllPayment(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        return paymentRepository.findAll(pageable).getContent();
    }

    @Override
    public List<PaymentModel> listAllPayment() {
        return paymentRepository.findAll();
    }
    @Override
    public PaymentModel findPaymentById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    @Override
    public PaymentModel addPayment(PaymentModel payment){
        return paymentRepository.save(payment);
    }

    @Override
    public PaymentModel updatePayment(PaymentModel payment){
        if (paymentRepository.existsById(payment.getId())){
            return paymentRepository.save(payment);
        }
        return null;
    }

    @Override
    public void deletePaymentLogically(Long id) {
        PaymentModel payment = paymentRepository.findById(id).orElse(null);
        if (payment != null) {
            payment.setDeleted(true);
            paymentRepository.save(payment);
        }
    }

    @Override
    public void deletePaymentPhysically(Long id) {
        paymentRepository.deleteById(id);
    }

}
