package com.blog.bolgpostproject.services;

import com.blog.bolgpostproject.entities.User;

public interface UserService {
    public User validateUser(String email, String password);
}
