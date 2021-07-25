package com.rishabhlingam.travelerp.controllers;

import com.rishabhlingam.travelerp.models.Passenger;
import com.rishabhlingam.travelerp.services.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping("/login")
    public String getLoginPage(Model model){
	    model.addAttribute("title", "Login Page");
	    return "login";
    }

    @RequestMapping("/signup")
    public String getSignupPage(){
        return "signup";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/signup")
    public Passenger registerUser(@RequestParam String name,
                             @RequestParam String email){
        Passenger passenger = new Passenger(name, email);
        passenger = passengerService.addPassenger(passenger);
        return passenger;
    }

    @ResponseBody
    @RequestMapping("/allusers")
    public List<Passenger> getAllPassengers(){
        return passengerService.getAllPassengers();
    }
}
