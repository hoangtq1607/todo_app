package com.vti.todo.controller;

import com.vti.todo.dto.request.TaskRequest;
import com.vti.todo.dto.response.TaskResponse;
import com.vti.todo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @PutMapping("/{taskId}")
    public TaskResponse updateTask(@PathVariable Integer taskId, @RequestBody @Valid TaskRequest request) {
        return taskService.updateTask(taskId, request);
    }


    // TODO create task
    @PostMapping
    public TaskResponse createTask(@RequestBody @Valid TaskRequest request) {
        return taskService.createTask(request);
    }
}
