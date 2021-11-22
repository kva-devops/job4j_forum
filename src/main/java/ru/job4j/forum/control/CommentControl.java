package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AuthorizationService;
import ru.job4j.forum.service.CommentService;

import java.util.Calendar;

@Controller
public class CommentControl {
    private final CommentService commentService;

    public CommentControl(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comment/save")
    public String save(
            @ModelAttribute Comment comment,
            RedirectAttributes attributes) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User buff = AuthorizationService.findUserByUsername(username);
        comment.setUser(buff);
        comment.setCreated(Calendar.getInstance());
        commentService.save(comment);
        attributes.addAttribute("id", comment.getPost().getId());
        return "redirect:/post";
    }
}
