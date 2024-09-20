package com.restaurant.restaurant_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "tableModel")
public class TableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Номер стола не может быть 0")
    private int tableNumber;

    @Min(value = 1, message = "Вместимость не может быть 0")
    private int capacity;

    private boolean deleted;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
    private List<OrderModel> orders;

    public TableModel() {
    }

    public TableModel(Long id, int tableNumber, int capacity, boolean deleted, List<OrderModel> orders) {
        this.id = id;
        this.tableNumber = tableNumber;
        this.capacity = capacity;
        this.deleted = deleted;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Min(value = 1, message = "Номер стола не может быть 0") int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(@Min(value = 1, message = "Номер стола не может быть 0") int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public @Min(value = 1, message = "Вместимость не может быть 0") int getCapacity() {
        return capacity;
    }

    public void setCapacity(@Min(value = 1, message = "Вместимость не может быть 0") int capacity) {
        this.capacity = capacity;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<OrderModel> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderModel> orders) {
        this.orders = orders;
    }
}
