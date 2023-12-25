package com.isg.ws.user.validation;

import com.isg.ws.user.User;
import com.isg.ws.user.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User inDB=userRepository.findByEmail(value);
        return inDB==null;
        /*if(inDB!=null){
            return false;
        }else {
            return true;
        }*/
    }
}
