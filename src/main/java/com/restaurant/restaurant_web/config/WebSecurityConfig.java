package com.restaurant.restaurant_web.config;

import com.restaurant.restaurant_web.model.ModelUser;
import com.restaurant.restaurant_web.model.RoleEnum;
import com.restaurant.restaurant_web.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private SessionRegistry sessionRegistry;

    public WebSecurityConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createDefaultUser() {
        if (!userRepository.existsByUsername("admin")) {
            ModelUser user = new ModelUser();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin123"));
            user.setActive(true);
            user.setRoles(Collections.singleton(RoleEnum.ADMIN));
            userRepository.save(user);
        }

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
                    ModelUser user = userRepository.findByUsername(username);
                    if (user == null) {
                        throw new UsernameNotFoundException("Такой пользователь не существует!");
                    }
                    return new org.springframework.security.core.userdetails.User(
                            user.getUsername(),
                            user.getPassword(),
                            user.isActive(),
                            true,
                            true,
                            true,
                            user.getRoles()
                    );
                }
        ).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/registration", "/actuator/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .sessionManagement(session -> session
                        .maximumSessions(10)
                        .maxSessionsPreventsLogin(true)
                        .sessionRegistry(sessionRegistry))
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());

        return http.build();
    }

    @Bean
    public static HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }




}
