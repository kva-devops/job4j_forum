package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserMem {
    private final Map<Integer, User> users = new HashMap<>();

    private final AtomicInteger counterUserId = new AtomicInteger(1);

    public User createUser(User user) {
        user.setId(counterUserId.intValue());
        for (User elem : users.values()) {
            if (elem.getEmail().equals(user.getEmail())) {
                return null;
            }
        }
        users.put(counterUserId.intValue(), user);
        counterUserId.incrementAndGet();
        return user;
    }

    public Collection<User> getUsers() {
        return users.values();
    }

    public User findUserByEmail(String email) {
        User rsl = null;
        for (User elem : users.values()) {
            if (email.equals(elem.getEmail())) {
                rsl = elem;
            }
        }
        return rsl;
    }
}
