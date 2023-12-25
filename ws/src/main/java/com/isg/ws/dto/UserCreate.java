package com.isg.ws.dto;

import com.isg.ws.user.User;
import com.isg.ws.user.validation.UniqueEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserCreate (
        @NotBlank(message = "{hoaxify.constraint.username.not-blank}")
        @Size(min=4,max = 255)
        String username,
        @NotBlank
        @Email
        @UniqueEmail
        String email,
        @NotBlank
        @Pattern(regexp="^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$",message = "{hoaxify.constraint.password.pattern}")
        String password
){
    public User toUser(){
        User user=new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        return user;
    }

}
