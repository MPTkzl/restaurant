package com.restaurant.restaurant_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "menu")
public class MenuModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Название не должно быть менее 3 символов")
    private String name;

    @Size(min = 3, message = "Описание не должно быть менее 3 символов")
    private String description;

    @Min(value = 1, message = "Цена не может быть меньше 1")
    private double price;
    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryModel category;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    private List<OrderItemModel> orderItems;

    public MenuModel() {
    }

    public MenuModel(Long id, String name, String description, double price, boolean deleted, CategoryModel category, List<OrderItemModel> orderItems) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.deleted = deleted;
        this.category = category;
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Size(min = 3, message = "Название не должно быть менее 3 символов") String getName() {
        return name;
    }

    public void setName(@Size(min = 3, message = "Название не должно быть менее 3 символов") String name) {
        this.name = name;
    }

    public @Size(min = 3, message = "Описание не должно быть менее 3 символов") String getDescription() {
        return description;
    }

    public void setDescription(@Size(min = 3, message = "Описание не должно быть менее 3 символов") String description) {
        this.description = description;
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

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public List<OrderItemModel> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemModel> orderItems) {
        this.orderItems = orderItems;
    }
}
