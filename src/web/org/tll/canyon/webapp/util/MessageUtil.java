package org.tll.canyon.webapp.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.tll.canyon.Constants;

public class MessageUtil {
	@SuppressWarnings("unchecked")
    public static void saveError(HttpServletRequest request, String error) {
        List errors = (List) request.getSession().getAttribute(Constants.ERROR_MESSAGE_KEY);
        if (errors == null) {
            errors = new ArrayList();
        }
        errors.add(error);
        request.getSession().setAttribute(Constants.ERROR_MESSAGE_KEY, errors);
    }
	
	@SuppressWarnings("unchecked")
    public static void saveMessage(HttpServletRequest request, String msg) {
        List messages = (List) request.getSession().getAttribute(Constants.MESSAGES_KEY);

        if (messages == null) {
            messages = new ArrayList();
        }

        messages.add(msg);
        request.getSession().setAttribute(Constants.MESSAGES_KEY, messages);
    }

}
