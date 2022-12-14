package com.vti.todo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class TaskRequest {

    @NotNull
    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate dueDate;

    private Integer workSpaceId;

    @NotNull
    private Integer taskStatusId;

}
