package com.eteptek.mbncheker.rest.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionHandler {
    
    @ExceptionHandler(value = NullPointerException.class)
    public String nullPointerHandler(Model theModel) {
        theModel.addAttribute("err", "NullPointerException");
        return "error";
    }
     
    @ExceptionHandler(value = Exception.class)
    public String AnyOtherHandler() {
        return "error";
    }
     
}