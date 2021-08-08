package com.ricardo.pmtool.persistence.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String name;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    @ManyToOne
    @JsonBackReference
    private User user;


    @PreRemove
    public void unassignTasks() {
        tasks.forEach(task -> task.setProject(null));
    }
}