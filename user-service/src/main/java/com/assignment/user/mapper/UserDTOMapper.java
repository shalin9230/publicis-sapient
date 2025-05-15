package com.assignment.user.mapper;

import com.assignment.user.dto.UserDto;
import com.assignment.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDto> {

    @Override
    public UserDto apply(User user) {

        return new UserDto(
                user.getId(),
                user.getUserName(),
                user.getPassword(),
                user.getEmail(),
                user.getMobileNo(),
                user.getRole()
        );
    }
}
