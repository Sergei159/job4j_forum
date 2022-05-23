package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.model.Post;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Post findById(int id);

    void deleteById(int id);

}