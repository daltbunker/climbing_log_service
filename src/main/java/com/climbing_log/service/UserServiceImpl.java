package com.climbing_log.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.climbing_log.exception.ResourceNotFoundException;
import com.climbing_log.model.User;
import com.climbing_log.repository.UserRepository;
import com.climbing_log.service.ifc.UserService;
 
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    public User getUserByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("username must not be null");
        }
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            throw new ResourceNotFoundException("user not found");
        }

        User user = userOpt.get();
        return user;
    }

}
