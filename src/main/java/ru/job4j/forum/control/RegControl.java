package ru.job4j.forum.control;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AuthorizationService;

@Controller
public class RegControl {

    private final PasswordEncoder encoder;

    public RegControl(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(AuthorizationService.findByAuthority("ROLE_USER"));
        try {
            AuthorizationService.createUser(user);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = "Пользователь с таким именем уже существует! Выберите другое имя.";
            model.addAttribute("errorMessage", errorMessage);
            return "reg";
        }
        return "redirect:/login";
    }
}
