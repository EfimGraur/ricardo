package net.proselyte.springsecuritydemo.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private int progress;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    private Date deadline;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JsonBackReference
    private Project project;

    @ManyToOne
    @JsonBackReference
    private User user;
}
