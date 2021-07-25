package com.rishabhlingam.travelerp.security;

import com.rishabhlingam.travelerp.models.Passenger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class PassengerDetails implements UserDetails {
    private Passenger passenger;
    public PassengerDetails(Passenger passenger){
        this.passenger = passenger;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getPassword() {
        return "qwerty";
    }

    @Override
    public String getUsername() {
        return passenger.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
