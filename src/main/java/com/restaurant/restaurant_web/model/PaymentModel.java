package com.restaurant.restaurant_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "payment")
public class PaymentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Сумма не может быть меньше 1")
    private double amount;

    private String paymentMethod;

    private boolean deleted;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    public PaymentModel() {
    }

    public PaymentModel(Long id, double amount, String paymentMethod, boolean deleted, OrderModel order) {
        this.id = id;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.deleted = deleted;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Min(value = 1, message = "Сумма не может быть меньше 1") double getAmount() {
        return amount;
    }

    public void setAmount(@Min(value = 1, message = "Сумма не может быть меньше 1") double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod( String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public OrderModel getOrder() {
        return order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }
}
