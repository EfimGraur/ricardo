package net.proselyte.springsecuritydemo.converter;

import net.proselyte.springsecuritydemo.data.UserData;
import net.proselyte.springsecuritydemo.persistence.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserData convert(final User user) {
        final UserData userData = new UserData();
        userData.setUsername(user.getUsername());
        userData.setId(user.getId());
        userData.setEmail(user.getEmail());
        userData.setFirstName(user.getFirstName());
        userData.setLastName(user.getLastName());
        userData.setRole(user.getRole().toString());
        return userData;
    }
}
