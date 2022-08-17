package com.vti.todo.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskResponse {

    private Integer id;

    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate dueDate;

    private Integer workSpaceId;

    private Integer statusId;

    private String statusName;
    
}
