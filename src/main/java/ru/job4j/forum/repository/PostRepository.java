package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Integer> {

    @EntityGraph(attributePaths = {"user"})
    List<Post> findAll();

    @EntityGraph(attributePaths = {"user", "comments"})
    Post findById(int id);
}
