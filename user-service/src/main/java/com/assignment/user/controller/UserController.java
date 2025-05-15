package com.assignment.user.controller;

import com.assignment.user.dto.UserDto;
import com.assignment.user.dto.UserSignInDTO;
import com.assignment.user.entity.User;
import com.assignment.user.request.UserRegistrationRequest;
import com.assignment.user.service.UserService;
import com.assignment.user.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest userRegistrationRequest) {
        return ResponseEntity.ok(userService.registerUser(userRegistrationRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserSignInDTO userSignInDTO) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userSignInDTO.getUsername(), userSignInDTO.getPassword()));

            User user = userService.findByUsername(userSignInDTO.getUsername());

            String token = jwtUtil.generateToken(user.getUserName(), user.getRole());

            return ResponseEntity.ok(token);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid Credentials");
        }
    }
}
