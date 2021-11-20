package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
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

@Controller
public class IndexControl {

    private final PostService postService;

    public IndexControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute(
                "user",
                SecurityContextHolder.getContext().getAuthentication().getPrincipal());
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

    @GetMapping("/post")
    public String loadPost(@RequestParam("id") String id, Model model) {
        model.addAttribute("post", postService.findById(Integer.parseInt(id)));
        return "post";
    }
}
