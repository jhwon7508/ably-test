package com.example.lecspringsecurity.utils;

import com.example.lecspringsecurity.controller.AuthController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityLogger {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    
    public static void log(String message) {
        
        logger.info(message);
        Thread thread = Thread.currentThread();
        logger.info("thread: " + thread.getName());
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        logger.info("principal" + principal);
    }
}
