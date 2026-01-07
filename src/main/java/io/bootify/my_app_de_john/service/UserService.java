package io.bootify.myappdejohn.service;

import io.bootify.myappdejohn.model.User;
import io.bootify.myappdejohn.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User save(User user) {
        return repository.save(user);
    }

    public List<User> findAll() {
        return repository.findAll();
    }
}
