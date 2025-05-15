package com.assignment.user.service;

import com.assignment.user.request.NotificationRequest;
import com.assignment.user.entity.User;
import com.assignment.user.mapper.UserDTOMapper;
import com.assignment.user.repository.UserRepository;
import com.assignment.user.request.UserRegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDTOMapper userDTOMapper;

    @Transactional
    public User registerUser(UserRegistrationRequest userRegistrationRequest) {
        if (userRepo.findByEmail(userRegistrationRequest.email()).isPresent() || userRepo.findByMobileNo(userRegistrationRequest.mobile()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }


        User user = new User();
        user.setMobileNo(userRegistrationRequest.mobile());
        user.setPassword(passwordEncoder.encode(userRegistrationRequest.password()));
        user.setRole("B2C");
        user.setEmail(userRegistrationRequest.email());
        user.setUserName(userRegistrationRequest.email()!=null ? userRegistrationRequest.email() : userRegistrationRequest.mobile());

        User savedUser = userRepo.save(user);
        NotificationRequest notificationRequest = new NotificationRequest(
                user.getId(),
                user.getEmail(),
                String.format("Hi %s, welcome to BMS...", user.getUserName())
        );
        return savedUser;
    }

    public User findByUsername(String username) {
        return userRepo.findByUserName(username).orElse(null);
    }
}
