package com.isg.ws.user;

import com.isg.ws.error.ApiError;
import com.isg.ws.shared.GenericMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody User user){

        userService.save(user);
        String message= messageSource.getMessage("hoaxify.create.user.success.message",null, LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleMethodArgNotValidEx(MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError();
        apiError.setPath("/api/v1/users");
        String message= messageSource.getMessage("hoaxify.error.validation",null, LocaleContextHolder.getLocale());
        apiError.setMessage(message);
        apiError.setStatus(400);
        Map<String,String> validationErrors=new HashMap<>();

        for (var fieldError:exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
/*
        var validationErrors=exception.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage));
*/
        apiError.setValidationErrors(validationErrors);
        return apiError;
        }

    @ExceptionHandler(NotUniqueEmailException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleNotUniqueEmail(NotUniqueEmailException exception){
        ApiError apiError=new ApiError();
        apiError.setPath("/api/v1/users");
        String message= messageSource.getMessage("hoaxify.constraint.email.notunique",null, LocaleContextHolder.getLocale());
        apiError.setMessage(message);
        apiError.setStatus(400);
        Map<String,String> validationErrors=new HashMap<>();
        validationErrors.put("email","email in use");
        /*for (var fieldError:exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }*/
        /*var validationErrors=exception.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage,(existing,replacing)->existing));*/
        apiError.setValidationErrors(validationErrors);
        return apiError;
    }


}
