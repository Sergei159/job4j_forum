package ru.job4j.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.model.Post;
import ru.job4j.repository.PostRepository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PostService {

    private final PostRepository store;


    public PostService(PostRepository posts) {
        this.store = posts;
    }

    public List<Post> findAll() {
        List<Post> rsl = new ArrayList<>();
        store.findAll().forEach(rsl::add);
        return rsl;
    }

    public Post findById(int id) {
        return store.findById(id);
    }

    public void save(Post post) {
        if (post.getCreated() == null) {
            post.setCreated(Calendar.getInstance());
        }
        store.save(post);
    }

    @Transactional
    public void deleteById(int id) {
        store.deleteById(id);
    }


}
