package io.aadesh.yogdaan.util;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Utility {

    public String getUserId(){

            return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
