package ru.job4j.forum.control;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AuthorizationService;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;

import java.util.Calendar;
import java.util.List;

@Controller
public class IndexControl {

    private static final Logger LOG = LogManager.getLogger(IndexControl.class.getName());

    private final PostService postService;

    private final CommentService commentService;

    public IndexControl(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User buff = AuthorizationService.findUserByUsername(username);
        model.addAttribute("user", buff);
        model.addAttribute("posts", postService.getAll());
        return "index";
    }

    @GetMapping("/create")
    public String create() {
        return "post/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User buff = AuthorizationService.findUserByUsername(username);
        post.setUser(buff);
        postService.addPostToStore(post);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "post/update";
    }

    @PostMapping("/comment/save")
    public String saveComment(@ModelAttribute Comment comment, RedirectAttributes attributes) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User buff = AuthorizationService.findUserByUsername(username);
        comment.setUser(buff);
        comment.setCreated(Calendar.getInstance());
        commentService.save(comment);
        attributes.addAttribute("id", comment.getPost().getId());
        return "redirect:/post";
    }

    @GetMapping("/post")
    public String loadPost(@RequestParam("id") String id, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User buff = AuthorizationService.findUserByUsername(username);
        List<Comment> comments = commentService.findAllCommentsByPostId(Integer.parseInt(id));
        model.addAttribute("comments", comments);
        model.addAttribute("user", buff);
        model.addAttribute("post", postService.findById(Integer.parseInt(id)));
        return "post";
    }

}
