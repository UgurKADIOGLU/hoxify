package com.isg.ws.user;

import com.isg.ws.user.exception.NotUniqueEmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public void save(User user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setAtivationToken(UUID.randomUUID().toString());
            userRepository.save(user);
        }catch (DataIntegrityViolationException exception){
            throw new NotUniqueEmailException();
        }

    }
}
