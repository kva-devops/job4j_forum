package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AuthorizationService;

@Controller
public class RegControl {

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        User buff = AuthorizationService.createUser(user);
        String errorMessage = "Пользователь с таким именем уже существует! Выберите другое имя.";
        if (buff == null) {
            model.addAttribute("errorMessage", errorMessage);
            return "reg";
        }
        return "redirect:/login";
    }
}
