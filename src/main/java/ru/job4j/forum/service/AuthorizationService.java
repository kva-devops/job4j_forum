package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserRepository;

@Service
public class AuthorizationService {

    private static UserRepository users;

    public AuthorizationService(UserRepository users) {
        AuthorizationService.users = users;
    }

    public static User createUser(User user) {
        return users.save(user);
    }

    public static User findUserByEmail(String email) {
        return users.findUserByEmail(email);
    }
}
