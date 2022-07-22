package ru.job4j.forum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRepository;
import ru.job4j.forum.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Service for authorization of users
 */
@Service
@RequiredArgsConstructor
public class AuthorizationService {

    /**
     * DAO object for repository of users
     */
    private final UserRepository usersRepository;

    /**
     * DAO object for repository of roles
     */
    private final AuthorityRepository authorityRepository;

    /**
     * Method for creating new user
     * @param user - User object
     * @return User object
     */
    public User createUser(User user) {
        String anchor = UUID.randomUUID().toString();
        if (user == null) {
            throw new NullPointerException("An internal error has occurred. Please try again later or contact technical support with the 'anchor'. anchor: " + anchor);
        }
        User createdUser = usersRepository.save(user);
        if (createdUser == user) {
            throw new NullPointerException("An internal error has occurred. Please try again later or contact technical support with the 'anchor'. anchor: " + anchor);
        } else {
            return createdUser;
        }
    }

    /**
     * Method for getting Authority object by name of role
     * @param authority - name of authority
     * @return Authority object
     */
    public Authority findByAuthority(String authority) {
        String anchor = UUID.randomUUID().toString();
        Optional<Authority> authorityOptional = authorityRepository.findByAuthority(authority);
        if (authorityOptional.isEmpty()) {
            throw new NullPointerException("An internal error has occurred. Please try again later or contact technical support with the 'anchor'. anchor: " + anchor);
        } else {
            return authorityOptional.get();
        }
    }

    /**
     * Method for getting User object by username
     * @param username - username of User object
     * @return User object
     */
    public User findUserByUsername(String username) {
        String anchor = UUID.randomUUID().toString();
        Optional<User> userOptional = usersRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new NullPointerException("An internal error has occurred. Please try again later or contact technical support with the 'anchor'. anchor: " + anchor);
        } else {
            return userOptional.get();
        }
    }
}
