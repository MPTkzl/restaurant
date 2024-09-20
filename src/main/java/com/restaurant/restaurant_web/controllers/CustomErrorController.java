package com.restaurant.restaurant_web.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("jakarta.servlet.error.status_code");
        String errorMessage = "Неизвестная ошибка";

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            switch (statusCode) {
                case 403:
                    errorMessage = "Доступ запрещен";
                    break;
                case 404:
                    errorMessage = "Страница не найдена";
                    break;
                case 500:
                    errorMessage = "Внутренняя ошибка сервера";
                    break;
                default:
                    errorMessage = "Ошибка с кодом: " + statusCode;
                    break;
            }
        }

        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }
}
