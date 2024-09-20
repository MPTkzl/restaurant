package com.restaurant.restaurant_web.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orderModel")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    private String status;

    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerModel customer;

    @ManyToOne
    @JoinColumn(name = "waiter_id")
    private WaiterModel waiter;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItemModel> orderItems;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private PaymentModel payment;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableModel table;

    public OrderModel() {
    }

    public OrderModel(Long id, LocalDateTime orderDate, String status, boolean deleted, CustomerModel customer, WaiterModel waiter, List<OrderItemModel> orderItems, PaymentModel payment, TableModel table) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.deleted = deleted;
        this.customer = customer;
        this.waiter = waiter;
        this.orderItems = orderItems;
        this.payment = payment;
        this.table = table;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate( LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public  String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public WaiterModel getWaiter() {
        return waiter;
    }

    public void setWaiter(WaiterModel waiter) {
        this.waiter = waiter;
    }

    public List<OrderItemModel> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemModel> orderItems) {
        this.orderItems = orderItems;
    }

    public PaymentModel getPayment() {
        return payment;
    }

    public void setPayment(PaymentModel payment) {
        this.payment = payment;
    }

    public TableModel getTable() {
        return table;
    }

    public void setTable(TableModel table) {
        this.table = table;
    }
}
