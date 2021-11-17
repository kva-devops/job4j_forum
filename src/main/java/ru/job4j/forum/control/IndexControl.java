package ru.job4j.forum.control;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexControl {

    private final PostService postService;

    public IndexControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", postService.getAll());
        return "index";
    }

    @GetMapping("/create")
    public String create() {
        return "post/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post, HttpServletRequest request) {
        User buff = (User) request.getSession().getAttribute("user");
        post.setAuthor(buff);
        postService.addPostToStore(post);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "post/update";
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        User buff = postService.createUser(user);
        String errorMessage = "Пользователь с таким именем уже существует! Выберите другое имя.";
        if (buff == null) {
            model.addAttribute("errorMessage", errorMessage);
            return "reg";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginCheck(@RequestParam("email") String email,
                             @RequestParam("password") String password,
                             HttpServletRequest request, Model model) {
        User rsl = postService.findUserByEmail(email);
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

    @GetMapping("/post")
    public String loadPost(@RequestParam("id") String id, Model model) {
        model.addAttribute("post", postService.findPostById(id));
        return "post";
    }
}
