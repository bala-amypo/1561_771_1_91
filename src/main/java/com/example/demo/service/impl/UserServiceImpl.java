package com.example.demo.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    
    public User register(User user){
        return userRepository.save(user);
    }
}
