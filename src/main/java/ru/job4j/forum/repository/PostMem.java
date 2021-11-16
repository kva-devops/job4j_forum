package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostMem {

    private final Map<Integer, Post> posts = new HashMap<>();

    private final AtomicInteger counterPostId = new AtomicInteger(1);

    public Collection<Post> getPosts() {
        return posts.values();
    }

    public void createPost(Post post) {
        post.setId(counterPostId.intValue());
        post.setCreated(Calendar.getInstance());
        posts.put(counterPostId.intValue(), post);
        counterPostId.incrementAndGet();
    }

    public void updatePost(Post post) {
        for (Map.Entry<Integer, Post> elem : posts.entrySet()) {
            if (elem.getValue().getId() == post.getId()) {
                post.setDescription(elem.getValue().getDescription());
                post.setCreated(elem.getValue().getCreated());
                posts.put(elem.getKey(), post);
            }
        }
    }

    public boolean checkContainPostIntoMap(int key) {
        return posts.containsKey(key);
    }

    public Post findPostById(String id) {
        return posts.get(Integer.parseInt(id));
    }
}
