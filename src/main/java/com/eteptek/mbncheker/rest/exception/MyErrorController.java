package com.eteptek.mbncheker.rest.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class MyErrorController implements ErrorController {

    private final static String PATH = "/error";

    @RequestMapping(PATH)
    @ResponseStatus
    public String getErrorPath() {
        return "error/exception";
    }

    public boolean validate(String str) {
    	if(str.equals("")||str.length() < 2) {
    		new AuthException("Please require input");
    		return false;
    	}else {
    		str= str.toUpperCase();
    		return true;
    	}
    	
    }

}