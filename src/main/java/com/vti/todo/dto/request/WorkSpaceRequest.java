package com.vti.todo.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class WorkSpaceRequest {
    @NotNull
    @NotBlank
    private String workSpaceName;
}
