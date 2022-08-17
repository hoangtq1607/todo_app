package com.vti.todo.controller;

import com.vti.todo.dto.response.TaskResponse;
import com.vti.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/workspaces/{workspaceId}")
    public List<TaskResponse> getTasksByWorkspace(@PathVariable Integer workspaceId) {
        return taskService.getTasksByWorkspace(workspaceId);
    }

    // TODO update task

    // TODO create task

}
