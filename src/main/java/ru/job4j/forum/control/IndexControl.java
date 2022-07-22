package ru.job4j.forum.control;

import lombok.AllArgsConstructor;
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

/**
 * Main controller for query of users processing
 */
@Controller
@AllArgsConstructor
public class IndexControl {

    private final PostService postService;

    private final CommentService commentService;

    private final AuthorizationService authorizationService;

    /**
     * Get method for processing index page
     * @param model Model object
     * @return String - name of view
     */
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User buff = authorizationService.findUserByUsername(username);
        model.addAttribute("user", buff);
        model.addAttribute("posts", postService.getAll());
        return "index";
    }

    /**
     * Get method for redirecting to create new post page
     * @return String - create view
     */
    @GetMapping("/create")
    public String create() {
        return "post/create";
    }

    /**
     * Post method for creating new post
     * @param post - Post model
     * @return redirecting
     */
    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User buff = authorizationService.findUserByUsername(username);
        post.setUser(buff);
        postService.addPost(post);
        return "redirect:/";
    }

    /**
     * Get method for updating post by post ID
     * @param id - post ID
     * @param model - Model object
     * @return - post update view
     */
    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", postService.findPostById(id));
        return "post/update";
    }

    /**
     * Post method for creating comment to post
     * @param comment - Comment object
     * @param attributes - attributes for redirecting
     * @return redirecting to post view
     */
    @PostMapping("/comment/save")
    public String saveComment(@ModelAttribute Comment comment, RedirectAttributes attributes) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User buff = authorizationService.findUserByUsername(username);
        comment.setUser(buff);
        comment.setCreated(Calendar.getInstance());
        commentService.save(comment);
        attributes.addAttribute("id", comment.getPost().getId());
        return "redirect:/post";
    }

    /**
     * Get method for loading post by post ID
     * @param id - post ID
     * @param model - Model object
     * @return post view
     */
    @GetMapping("/post")
    public String loadPost(@RequestParam("id") String id, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User buff = authorizationService.findUserByUsername(username);
        List<Comment> comments = commentService.findAllCommentsByPostId(Integer.parseInt(id));
        model.addAttribute("comments", comments);
        model.addAttribute("user", buff);
        model.addAttribute("post", postService.findPostById(Integer.parseInt(id)));
        return "post";
    }

}
