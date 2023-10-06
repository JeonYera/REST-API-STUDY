package com.rest.study.user.service;

import com.rest.study.user.entity.User;

public interface UserService {
    User findByUserId(String freeUserId);
}
