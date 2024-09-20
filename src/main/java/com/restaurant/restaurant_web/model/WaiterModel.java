package com.restaurant.restaurant_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "waiter")
public class WaiterModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Имя не должно быть менее 3 символов")
    private String name;

    @Size(min = 3, message = "Фамилия не должна быть менее 3 символов")
    private String lastname;

    private boolean deleted;

    @OneToMany(mappedBy = "waiter", cascade = CascadeType.ALL)
    private List<OrderModel> orders;

    public WaiterModel() {
    }

    public WaiterModel(Long id, String name, String lastname, boolean deleted, List<OrderModel> orders) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.deleted = deleted;
        this.orders = orders;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(min = 3, message = "Имя не должно быть менее 3 символов") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, message = "Имя не должно быть менее 3 символов") String name) {
        this.name = name;
    }

    public @Size(min = 3, message = "Фамилия не должна быть менее 3 символов") String getLastname() {
        return lastname;
    }

    public void setLastname(@Size(min = 3, message = "Фамилия не должна быть менее 3 символов") String lastname) {
        this.lastname = lastname;
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
