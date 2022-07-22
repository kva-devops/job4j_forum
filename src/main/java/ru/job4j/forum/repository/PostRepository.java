package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.forum.model.Post;

import java.util.List;
import java.util.Optional;

/**
 * DAO interface for Post models
 */
public interface PostRepository extends CrudRepository<Post, Integer> {

    /**
     * Finding all posts
     * @return List of posts
     */
    @EntityGraph(attributePaths = {"user"})
    List<Post> findAll();

    /**
     * Finding Post object by post ID
     * @param id - post ID
     * @return Post object
     */
    @EntityGraph(attributePaths = {"user", "comments"})
    Optional<Post> findById(@Param("id") int id);
}
