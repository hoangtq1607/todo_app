package com.vti.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkSpaceResponse {
    private Integer id;
    private String workSpaceName;
    private Long numberOfTask;
}
