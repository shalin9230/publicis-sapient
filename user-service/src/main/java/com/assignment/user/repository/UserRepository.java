package com.assignment.user.repository;

import com.assignment.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String emailId);

    Optional<User> findByMobileNo(String mobileNo);

    Optional<User> findByUserName(String username);
}
