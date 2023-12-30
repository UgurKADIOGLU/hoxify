package com.isg.ws.user;

import com.isg.ws.dto.UserCreate;
import com.isg.ws.error.ApiError;
import com.isg.ws.shared.GenericMessage;
import com.isg.ws.shared.Messages;
import com.isg.ws.user.exception.AtivationNotifictionException;
import com.isg.ws.user.exception.InvalidTokenException;
import com.isg.ws.user.exception.NotUniqueEmailException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {
    @Autowired
    UserService userService;

    /*@Autowired
    MessageSource messageSource;*/

    @PostMapping("/api/v1/users")
    GenericMessage createUser(@Valid @RequestBody UserCreate user){

        userService.save(user.toUser());
        String message= Messages.getMessageForLocle("hoaxify.create.user.success.message",LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @PatchMapping("/api/v1/users/{token}/active")
    GenericMessage activateUser(@PathVariable String token){
        userService.activateUser(token);
        String message = Messages.getMessageForLocle("hoaxify.activate.user.success.message", LocaleContextHolder.getLocale());
        return new GenericMessage(message);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleMethodArgNotValidEx(MethodArgumentNotValidException exception){
        ApiError apiError=new ApiError();
        apiError.setPath("/api/v1/users");
        String message= Messages.getMessageForLocle("hoaxify.error.validation",LocaleContextHolder.getLocale());
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

        apiError.setMessage(exception.getMessage());
        apiError.setStatus(400);

        /*for (var fieldError:exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }*/
        /*var validationErrors=exception.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage,(existing,replacing)->existing));*/
        apiError.setValidationErrors(exception.getValidationErrors());
        return apiError;
    }
    @ExceptionHandler(AtivationNotifictionException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleNotActivationEmail(AtivationNotifictionException exception){
        ApiError apiError=new ApiError();
        apiError.setPath("/api/v1/users");

        apiError.setMessage(exception.getMessage());
        apiError.setStatus(502);

        /*for (var fieldError:exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }*/
        /*var validationErrors=exception.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage,(existing,replacing)->existing));*/

        return apiError;


    }
    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiError handleInvalidTokenException(InvalidTokenException exception){
        ApiError apiError=new ApiError();
        apiError.setPath("/api/v1/users");

        apiError.setMessage(exception.getMessage());
        apiError.setStatus(400);

        /*for (var fieldError:exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }*/
        /*var validationErrors=exception.getBindingResult().getFieldErrors().stream().collect(Collectors.toMap(FieldError::getField,FieldError::getDefaultMessage,(existing,replacing)->existing));*/

        return apiError;


}
}
