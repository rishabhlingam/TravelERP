package com.rishabhlingam.travelerp.controllers;

import com.rishabhlingam.travelerp.models.Passenger;
import com.rishabhlingam.travelerp.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private PassengerService passengerService;

    @RequestMapping("/")
    public String getHomePage(Model model){
        return "home";
    }

    @RequestMapping("/signup")
    public String getSignupPage(){
        return "signup";
    }

    @RequestMapping("/user")
    public String getUserPage(Model model){
        SecurityContext context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();
        Passenger passenger = passengerService.findByEmail(email);
        model.addAttribute("passenger", passenger);
	    return "user";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public String registerUser(@RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String userType,
                               @RequestParam double balance){
        Passenger passenger = new Passenger(name, email, balance, userType);
        passenger = passengerService.addPassenger(passenger);
        System.out.println(passenger);
        return "redirect:/travelPackages/";
    }

    @ResponseBody
    @RequestMapping("/allusers")
    public List<Passenger> getAllPassengers(){
        return passengerService.getAllPassengers();
    }
}
