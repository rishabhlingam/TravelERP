package com.rishabhlingam.travelerp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	@RequestMapping("/login")
    public String getLoginPage(Model model){
	    model.addAttribute("title", "Login Page");
	    return "login";
    }

    @RequestMapping("/signup")
    public String getSignupPage(){
        return "signup";
    }
}
