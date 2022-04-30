package ru.gb.gbspringframework.service;

import ru.gb.gbspringframework.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByUsername(String name);

    User save(User user);
}
