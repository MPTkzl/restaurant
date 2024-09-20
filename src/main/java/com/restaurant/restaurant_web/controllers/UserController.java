package com.restaurant.restaurant_web.controllers;

import com.restaurant.restaurant_web.model.ModelUser;
import com.restaurant.restaurant_web.model.RoleEnum;
import com.restaurant.restaurant_web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String userView(Model model) {
        model.addAttribute("user_list", userRepository.findAll());
        return "index";
    }

    @GetMapping("/{id}")
    public String detailView(@PathVariable Long id, Model model) {
        ModelUser user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Пользователя не существует:" + id));
        model.addAttribute("user_object", user);
        return "info";
    }

    @GetMapping("/{id}/update")
    public String updateView(@PathVariable Long id, Model model) {
        ModelUser user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не существует: " + id));
        model.addAttribute("user_object", user);
        model.addAttribute("roles", RoleEnum.values());
        return "update";
    }


    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable Long id,
                             @RequestParam String username,
                             @RequestParam(name = "roles[]", required = false) String[] roles) {
        ModelUser user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Пользователя не существует:" + id));
        user.setUsername(username);
        user.getRoles().clear();
        if (roles != null) {
            for (String role : roles) {
                user.getRoles().add(RoleEnum.valueOf(role));
            }
        }
        userRepository.save(user);
        return "redirect:/admin/" + id;
    }
}
