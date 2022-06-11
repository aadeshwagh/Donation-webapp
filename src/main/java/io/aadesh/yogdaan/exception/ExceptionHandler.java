package io.aadesh.yogdaan.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String handle(Exception e, Model model){
        model.addAttribute("message",e.getMessage());
        return "error";
    }
}
