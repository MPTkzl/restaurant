package com.restaurant.restaurant_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "orderitem")
public class OrderItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Количество не может быть меньше 1")
    private int quantity;

    @Min(value = 1, message = "Цена не может быть меньше 1")
    private double price;
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderModel order;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private MenuModel menu;

    public OrderItemModel() {
    }

    public OrderItemModel(Long id, int quantity, double price, boolean deleted, OrderModel order, MenuModel menu) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.deleted = deleted;
        this.order = order;
        this.menu = menu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Min(value = 1, message = "Количество не может быть меньше 1") int getQuantity() {
        return quantity;
    }

    public void setQuantity(@Min(value = 1, message = "Количество не может быть меньше 1") int quantity) {
        this.quantity = quantity;
    }

    public @Min(value = 1, message = "Цена не может быть меньше 1") double getPrice() {
        return price;
    }

    public void setPrice(@Min(value = 1, message = "Цена не может быть меньше 1") double price) {
        this.price = price;
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

    public MenuModel getMenu() {
        return menu;
    }

    public void setMenu(MenuModel menu) {
        this.menu = menu;
    }
}
