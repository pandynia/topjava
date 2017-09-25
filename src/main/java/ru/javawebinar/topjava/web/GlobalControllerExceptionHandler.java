package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.util.ValidationUtil;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerExceptionHandler {
    private static final Logger LOG = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView errorHandler(HttpServletRequest req, DataIntegrityViolationException e) throws Exception {
        String message=ValidationUtil.getRootCause(e).toString();
        if(message.contains("users_unique_email_idx"))
            message="User with this email already exists";
        return catchError(req,e,message);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        return catchError(req,e,ValidationUtil.getRootCause(e).toString());
    }

    private static ModelAndView catchError(HttpServletRequest req, Exception e, String message) throws Exception {
        LOG.error("Exception at request " + req.getRequestURL(), e);

        ModelAndView mav = new ModelAndView("exception/exception");
        Throwable rootCause = ValidationUtil.getRootCause(e);
        mav.addObject("exception", rootCause);
        mav.addObject("message", message);

        // Interceptor is not invoked, put userTo
        AuthorizedUser authorizedUser = AuthorizedUser.safeGet();
        if (authorizedUser != null) {
            mav.addObject("userTo", authorizedUser.getUserTo());
        }
        return mav;
    }
}
