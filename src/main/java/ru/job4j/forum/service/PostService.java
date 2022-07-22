package ru.job4j.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.*;

/**
 * Service for working with objects of post
 */
@Service
@RequiredArgsConstructor
public class PostService {

    /**
     * DAO object for repository of posts
     */
    private final PostRepository postRepository;

    /**
     * Method for getting all posts
     * @return List of posts
     */
    public Collection<Post> getAll() {
        String anchor = UUID.randomUUID().toString();
        List<Post> postList = postRepository.findAll();
        if (postList == null) {
            throw new NullPointerException("An internal error has occurred. Please try again later or contact technical support with the 'anchor'. anchor: " + anchor);
        } else {
            return postList;
        }
    }

    /**
     * Method for adding new post
     * @param post Post object
     */
    public void addPost(Post post) {
        String anchor = UUID.randomUUID().toString();
        if (post == null) {
            throw new NullPointerException("An internal error has occurred. Please try again later or contact technical support with the 'anchor'. anchor: " + anchor);
        }
        if (post.getId() != 0) {
            Post createdPost = findPostById(post.getId());
            createdPost.setName(post.getName());
            postRepository.save(createdPost);
        } else {
            post.setCreated(Calendar.getInstance());
            postRepository.save(post);
        }
    }

    /**
     * Method for getting post by post ID
     * @param id - post ID
     * @return Post object
     */
    public Post findPostById(int id) {
        String anchor = UUID.randomUUID().toString();
        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isEmpty()) {
            throw new IllegalArgumentException("Post not found. Actual parameters: post ID - " + id + ". Please contact technical support with the 'anchor'. anchor: " + anchor);
        } else {
            return postOptional.get();
        }
    }
}
