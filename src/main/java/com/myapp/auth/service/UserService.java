package com.myapp.auth.service;

import com.myapp.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}