package com.isg.ws.user.exception;

import com.isg.ws.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException() {
        super(Messages.getMessageForLocle("hoaxify.activate.user.invalid.token", LocaleContextHolder.getLocale()));
    }
}
