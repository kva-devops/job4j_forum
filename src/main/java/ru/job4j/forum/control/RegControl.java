package ru.job4j.forum.control;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AuthorizationService;

/**
 * Controller for registration of users
 */
@Controller
@RequiredArgsConstructor
public class RegControl {

    /**
     * Encoder for encoding password
     */
    private final PasswordEncoder encoder;

    /**
     * Business logic for authorization of users
     */
    private final AuthorizationService authorizationService;

    /**
     * Get method for view page of registering users
     * @return "reg" view
     */
    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }

    /**
     * Post method for registration new user
     * @param user - User object
     * @param model - Model object
     * @return reg view or redirection to login view
     */
    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorizationService.findByAuthority("ROLE_USER"));
        try {
            authorizationService.createUser(user);
        } catch (DataIntegrityViolationException e) {
            String errorMessage = "Пользователь с таким именем уже существует! Выберите другое имя.";
            model.addAttribute("errorMessage", errorMessage);
            return "reg";
        }
        return "redirect:/login";
    }
}
