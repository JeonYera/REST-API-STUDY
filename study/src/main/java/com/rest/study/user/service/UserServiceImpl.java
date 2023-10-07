package com.rest.study.user.service;

import com.rest.study.user.entity.User;
import com.rest.study.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUserId(String freeUserId) {
        return userRepository.findByUserId(freeUserId);
    }
}
