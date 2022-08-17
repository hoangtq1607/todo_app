package com.vti.todo.controller;

import com.vti.todo.entity.TaskEntity;
import com.vti.todo.entity.TaskStatusEntity;
import com.vti.todo.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task-status")
public class TaskStatusController {

    @Autowired
    TaskStatusRepository taskStatusRepository;

    @GetMapping
    public List<TaskStatusEntity> getAllTaskStatus() {
        return taskStatusRepository.findAll();
    }
}
