package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRepository;
import ru.job4j.forum.repository.UserRepository;

@Service
public class AuthorizationService {

    private static UserRepository users;
    
    private static AuthorityRepository authorityRepository;

    public AuthorizationService(
            UserRepository users,
            AuthorityRepository authorityRepository) {
        AuthorizationService.users = users;
        AuthorizationService.authorityRepository = authorityRepository;
    }

    public static User createUser(User user) {
        return users.save(user);
    }

    public static Authority findByAuthority(String authority) {
        return authorityRepository.findByAuthority(authority);
    }

    public static User findUserByUsername(String username) {
        return users.findByUsername(username);
    }
}
