package com.ricardo.pmtool.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectData {
    private Long id;
    private String code;
    private String name;
    private String assignee;
}
