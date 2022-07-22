package ru.job4j.forum.control;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller for login user
 */
@Controller
public class LoginControl {

    /**
     * GET method for login page
     * @param error - String error (if exist)
     * @param logout - String logout (if exist)
     * @param model - Model object
     * @return login view
     */
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect";
        }
        if (logout != null) {
            errorMessage = "You have been successfully logged out";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    /**
     * Get method for logout user
     * @param req - HttpServletRequest object
     * @param resp - HttpServletResponse object
     * @return redirect
     */
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest req, HttpServletResponse resp) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(req, resp, authentication);
        }
        return "redirect:/login?logout=true";
    }
}
