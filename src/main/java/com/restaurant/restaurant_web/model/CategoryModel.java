package com.restaurant.restaurant_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;


@Entity
@Table(name = "category")
public class CategoryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "Название не должно быть менее 3 символов")
    private String name;
    private boolean deleted;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<MenuModel> menu;

    public CategoryModel() {
    }

    public CategoryModel(Long id, String name, boolean deleted, List<MenuModel> menu) {
        this.id = id;
        this.name = name;
        this.deleted = deleted;
        this.menu = menu;
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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public List<MenuModel> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuModel> menu) {
        this.menu = menu;
    }
}
