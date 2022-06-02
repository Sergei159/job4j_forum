package ru.job4j.service;

import org.springframework.stereotype.Service;
import ru.job4j.model.User;
import ru.job4j.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository store;

    public UserService(UserRepository store) {
        this.store = store;
    }

    public List<User> findAll() {
        List<User> rsl = new ArrayList<>();
        store.findAll().forEach(rsl::add);
        return rsl;
    }

    public User save(User user) {
        User result = null;
        try {
            result = store.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
