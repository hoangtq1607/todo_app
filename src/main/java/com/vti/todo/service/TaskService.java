package com.vti.todo.service;

import com.vti.todo.dto.response.TaskResponse;
import com.vti.todo.entity.TaskEntity;
import com.vti.todo.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskResponse> getTasksByWorkspace(Integer workspaceId) {

        List<TaskResponse> responses = new ArrayList<>();
        List<TaskEntity> tasksByWorkspace = taskRepository.findByWorkSpaceId(workspaceId);
        for (TaskEntity taskEntity : tasksByWorkspace) {
            TaskResponse response = new TaskResponse();
            BeanUtils.copyProperties(taskEntity, response);
            response.setWorkSpaceId(workspaceId);
            response.setStatusId(taskEntity.getTaskStatus().getId());
            response.setStatusName(taskEntity.getTaskStatus().getName());
            responses.add(response);
        }

        return responses;
    }
}
