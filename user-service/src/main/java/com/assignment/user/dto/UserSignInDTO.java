package com.assignment.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class UserSignInDTO {
    private String username;
    private String password;
}
