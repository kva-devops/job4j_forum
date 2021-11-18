package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postStore;

    public PostService(PostRepository postStore) {
        this.postStore = postStore;
    }

    public Collection<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        postStore.findAll().forEach(rsl::add);
        return rsl;
    }

    public void addPostToStore(Post post) {
        if (post.getId() != 0) {
            Post buff = findById(post.getId());
            buff.setName(post.getName());
            postStore.save(buff);
        } else {
            post.setCreated(Calendar.getInstance());
            postStore.save(post);
        }
    }

    public Post findById(int id) {
        return postStore.findById(id).get();
    }

}
