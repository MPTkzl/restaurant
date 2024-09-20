package com.restaurant.restaurant_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "customer")
public class CustomerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Имя не должно быть менее 3 символов")
    private String name;

    @Size(min = 7, message = "Номер телефона не должен быть менее 7 символов")
    private String phone;

    @Size(min = 6, message = "Адрес не должен быть менее 6 символов")
    private String address;
    private boolean deleted;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<OrderModel> orders;

    public CustomerModel() {
    }

    public CustomerModel(Long id, String name, String phone, String address, boolean deleted, List<OrderModel> orders) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
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

    public @Size(min = 7, message = "Номер телефона не должен быть менее 7 символов") String getPhone() {
        return phone;
    }

    public void setPhone(@Size(min = 7, message = "Номер телефона не должен быть менее 7 символов") String phone) {
        this.phone = phone;
    }

    public @Size(min = 6, message = "Адрес не должен быть менее 6 символов") String getAddress() {
        return address;
    }

    public void setAddress(@Size(min = 6, message = "Адрес не должен быть менее 6 символов") String address) {
        this.address = address;
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
