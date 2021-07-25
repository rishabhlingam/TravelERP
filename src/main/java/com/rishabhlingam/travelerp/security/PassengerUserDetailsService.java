package com.rishabhlingam.travelerp.security;

import com.rishabhlingam.travelerp.models.Passenger;
import com.rishabhlingam.travelerp.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PassengerUserDetailsService implements UserDetailsService {
    @Autowired
    private PassengerRepository repository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Passenger> passenger = repository.findByEmail(email);
        passenger.orElseThrow(() -> new UsernameNotFoundException("email: " + email + "  not found!"));
        return new PassengerDetails(passenger.get());
    }
}
