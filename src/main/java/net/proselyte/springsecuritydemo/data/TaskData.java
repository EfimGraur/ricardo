package net.proselyte.springsecuritydemo.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.proselyte.springsecuritydemo.persistence.model.TaskStatus;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskData {
    private Long id;
    private String description;
    private String pm;
    private int progress;
    private TaskStatus status;
    private Date deadline;
    private String project;
    private String assignee;
}
