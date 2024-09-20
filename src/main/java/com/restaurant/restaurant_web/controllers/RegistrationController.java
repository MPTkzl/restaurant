package com.restaurant.restaurant_web.controllers;

import com.restaurant.restaurant_web.model.ModelUser;
import com.restaurant.restaurant_web.model.RoleEnum;
import com.restaurant.restaurant_web.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String registrationView(Model model) {
        List<RoleEnum> roles = Arrays.stream(RoleEnum.values())
                .filter(role -> role != RoleEnum.ADMIN)
                .collect(Collectors.toList());
        model.addAttribute("user", new ModelUser());
        model.addAttribute("roles", roles);
        return "regis";
    }

    @PostMapping("/registration")
    public String registrationUser(@Valid @ModelAttribute("user") ModelUser user,
                                   BindingResult result,
                                   @RequestParam String role,
                                   Model model) {
        if (result.hasErrors()) {
            List<RoleEnum> roles = Arrays.stream(RoleEnum.values())
                    .filter(r -> r != RoleEnum.ADMIN)
                    .collect(Collectors.toList());
            model.addAttribute("roles", roles);
            return "regis";
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("message", "Пользователь уже существует");
            List<RoleEnum> roles = Arrays.stream(RoleEnum.values())
                    .filter(r -> r != RoleEnum.ADMIN)
                    .collect(Collectors.toList());
            model.addAttribute("roles", roles);
            return "regis";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(RoleEnum.valueOf(role)));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }
}
