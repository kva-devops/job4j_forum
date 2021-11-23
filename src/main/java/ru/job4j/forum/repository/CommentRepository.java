package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.forum.model.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Integer> {

    @Query("SELECT DISTINCT c FROM Comment c "
            + "JOIN FETCH c.user u "
            + "WHERE c.post.id = :id")
    List<Comment> findAllCommentsByPostId(@Param("id") int postId);
}
