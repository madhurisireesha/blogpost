package com.blog.bolgpostproject.services;

import com.blog.bolgpostproject.entities.User;
import com.blog.bolgpostproject.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;

    public User validateUser(String email, String password) {
        User user=userRepository.findByEmailAndPassword(email,password);
        return user;
    }
}
