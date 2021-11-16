package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.PostMem;
import ru.job4j.forum.repository.UserMem;

import java.util.Collection;

@Service
public class PostService {

    private final PostMem postStore;

    private final UserMem users;

    public PostService() {
        this.postStore = new PostMem();
        this.users = new UserMem();
    }

    public Collection<Post> getAll() {
        return postStore.getPosts();
    }

    public void addPostToStore(Post post) {
        if (postStore.checkContainPostIntoMap(post.getId())) {
            postStore.updatePost(post);
        } else {
            postStore.createPost(post);
        }
    }

    public Post findById(int id) {
        Post rsl = null;
        for (Post elem : postStore.getPosts()) {
            if (elem.getId() == id) {
                rsl = elem;
            }
        }
        return rsl;
    }

    public User createUser(User user) {
        return users.createUser(user);
    }

    public User findUserByEmail(String email) {
        return users.findUserByEmail(email);
    }

    public Post findPostById(String id) {
        return postStore.findPostById(id);
    }

}
