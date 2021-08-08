package com.ricardo.pmtool.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ricardo.pmtool.status.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private int progress;

    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    private Date deadline;

    @ManyToOne()
    private Project project;

    @ManyToOne
    @JsonBackReference
    private User user;
}
