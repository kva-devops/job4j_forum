package ru.job4j.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.repository.CommentRepository;

import java.util.List;
import java.util.UUID;

/**
 * Service for working with comments
 */
@Service
@RequiredArgsConstructor
public class CommentService {
    /**
     * DAO object for repository of comments
     */
    private final CommentRepository commentRepository;

    /**
     * Method for adding new comment
     * @param comment Comment object
     */
    public void save(Comment comment) {
        String anchor = UUID.randomUUID().toString();
        if (comment == null) {
            throw new NullPointerException("An internal error has occurred. Please try again later or contact technical support with the 'anchor'. anchor: " + anchor);
        }
        commentRepository.save(comment);
    }

    /**
     * Method for getting all comments of post by post ID
     * @param postId - post ID
     * @return List of comments
     */
    public List<Comment> findAllCommentsByPostId(int postId) {
        String anchor = UUID.randomUUID().toString();
        List<Comment> commentsList = commentRepository.findAllCommentsByPostId(postId);
        if (commentsList == null) {
            throw new NullPointerException("An internal error has occurred. Please try again later or contact technical support with the 'anchor'. anchor: " + anchor);
        }
        return commentsList;
    }

}
