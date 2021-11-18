package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserMem;

@Service
public class AuthorizationService {

    private static final UserMem USERS = new UserMem();

    public AuthorizationService() {
    }

    public static User createUser(User user) {
        return USERS.createUser(user);
    }

    public static User findUserByEmail(String email) {
        return USERS.findUserByEmail(email);
    }
}
