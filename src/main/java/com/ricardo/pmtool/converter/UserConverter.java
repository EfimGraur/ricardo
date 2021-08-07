package com.ricardo.pmtool.converter;

import com.ricardo.pmtool.data.UserData;
import com.ricardo.pmtool.persistence.model.User;
import com.ricardo.pmtool.roles.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserConverter(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserData convert(final User user) {
        UserData userData = new UserData();
        userData.setUsername(user.getUsername());
        userData.setId(user.getId());
        userData.setEmail(user.getEmail());
        userData.setFirstName(user.getFirstName());
        userData.setLastName(user.getLastName());
        userData.setRole(user.getRole().toString());
        return userData;
    }

    public User convert(final UserData userData) {
        final User user = new User();
        user.setEmail(userData.getEmail());
        user.setFirstName(userData.getFirstName());
        user.setLastName(userData.getLastName());
        user.setUsername(userData.getUsername());
        user.setRole(Role.valueOf(userData.getRole()));
        //Better to user Optional
        if (userData.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userData.getPassword()));
        }
        return user;
    }
}
