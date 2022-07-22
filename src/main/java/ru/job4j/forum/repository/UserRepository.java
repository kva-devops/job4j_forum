package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.User;

import java.util.Optional;

/**
 * DAO interface for User models
 */
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Finding User object by username
     * @param username - username of user
     * @return User object
     */
    Optional<User> findByUsername(String username);
}
