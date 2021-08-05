package net.proselyte.springsecuritydemo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String username;
    private String role;

}
