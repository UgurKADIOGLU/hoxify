package com.isg.ws.user.exception;

import com.isg.ws.shared.Messages;
import org.springframework.context.i18n.LocaleContextHolder;

public class AtivationNotifictionException extends RuntimeException {
    public AtivationNotifictionException() {
        super(Messages.getMessageForLocle("hoxify.create.user.email.failure", LocaleContextHolder.getLocale()));
    }
}
