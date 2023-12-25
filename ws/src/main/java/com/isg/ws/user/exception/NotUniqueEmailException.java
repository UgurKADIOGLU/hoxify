package com.isg.ws.user.exception;

import com.isg.ws.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Collections;
import java.util.Map;

public class NotUniqueEmailException extends RuntimeException{
    public NotUniqueEmailException() {
        super(Messages.getMessageForLocle("hoaxify.error.validation", LocaleContextHolder.getLocale()));
    }
public Map<String,String> getValidationErrors(){
        return Collections.singletonMap("email",Messages.getMessageForLocle("hoaxify.constraint.email.notunique",LocaleContextHolder.getLocale()));
}

}
