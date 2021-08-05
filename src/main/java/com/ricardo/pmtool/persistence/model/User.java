package com.ricardo.pmtool.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import com.ricardo.pmtool.roles.Role;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String username;
    @Enumerated(value = EnumType.STRING)
    private Role role;

//    @OneToOne
//    private Project project;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Project> projects;

    @OneToMany(mappedBy = "user")
    private List<Task> tasks;
}
