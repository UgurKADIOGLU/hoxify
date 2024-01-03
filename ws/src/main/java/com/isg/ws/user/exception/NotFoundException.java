package com.isg.ws.user.exception;

import com.isg.ws.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {

        super(Messages.getMessageForLocle("hoaxify.user.not.found", LocaleContextHolder.getLocale(),id));
    }
}
