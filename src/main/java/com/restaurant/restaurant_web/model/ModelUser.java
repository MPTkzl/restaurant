package com.restaurant.restaurant_web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Entity
public class ModelUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Size(min = 3, message = "Логин не должен быть менее 3 символов")
    private String username;
    @Size(min = 6, message = "Пароль не должен быть менее 6 символов")
    private String password;
    private boolean active;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> roles;

    public ModelUser() {

    }

    public ModelUser(String username, String password, boolean active, Set<RoleEnum> roles) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public @Size(min = 3, message = "Логин не должен быть менее 3 символов") String getUsername() {
        return username;
    }

    public void setUsername(@Size(min = 3, message = "Логин не должен быть менее 3 символов") String username) {
        this.username = username;
    }

    public @Size(min = 6, message = "Пароль не должен быть менее 6 символов") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 6, message = "Пароль не должен быть менее 6 символов") String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEnum> roles) {
        this.roles = roles;
    }
}
