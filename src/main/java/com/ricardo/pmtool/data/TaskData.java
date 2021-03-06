package com.ricardo.pmtool.data;

import com.ricardo.pmtool.status.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskData {
    private Long id;
    private String description;
    private String pm;
    private int progress;
    private String status;
    private Date deadline;
    private String projectCode;
    private String assignee;
}
