package com.isg.ws.shared;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
    public static String getMessageForLocle(String messageKey, Locale locale){
        return ResourceBundle.getBundle("messages",locale).getString(messageKey);
    }
}
