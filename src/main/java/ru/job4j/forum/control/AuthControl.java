package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AuthorizationService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AuthControl {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginCheck(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             HttpServletRequest request, Model model) {
        User rsl = AuthorizationService.findUserByEmail(email);
        String errorMessage;
        if (rsl == null) {
            errorMessage = "Пользователь с таким именем не существует! Зарегистрируйтесь";
            model.addAttribute("errorMessage", errorMessage);
            return "login";
        }
        if (password.equals(rsl.getPassword())) {
            HttpSession sc = request.getSession();
            sc.setAttribute("user", rsl);
            return "redirect:/";
        } else if (!password.equals(rsl.getPassword())) {
            errorMessage = "Неверный пароль!";
            model.addAttribute("errorMessage", errorMessage);
            return "login";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
