package io.bootify.myappdejohn.controller;

import io.bootify.myappdejohn.model.User;
import io.bootify.myappdejohn.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.save(user);
    }

    @GetMapping
    public List<User> list() {
        return service.findAll();
    }
}
