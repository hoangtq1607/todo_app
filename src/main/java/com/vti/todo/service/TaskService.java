package com.vti.todo.service;

import com.vti.todo.dto.request.TaskRequest;
import com.vti.todo.dto.response.TaskResponse;
import com.vti.todo.entity.TaskEntity;
import com.vti.todo.entity.TaskStatusEntity;
import com.vti.todo.entity.WorkSpaceEntity;
import com.vti.todo.repository.TaskRepository;
import com.vti.todo.repository.TaskStatusRepository;
import com.vti.todo.repository.WorkSpaceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskStatusRepository statusRepository;

    @Autowired
    private WorkSpaceRepository workSpaceRepository;

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

    public TaskResponse updateTask(Integer taskId, TaskRequest request) {
        Optional<TaskEntity> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isPresent()) {
            TaskEntity task = taskOpt.get();
            task.setDescription(request.getDescription());
            task.setDueDate(request.getDueDate());
            task.setStartDate(request.getStartDate());
            task.setTitle(request.getTitle());
            TaskStatusEntity status = statusRepository.getReferenceById(request.getTaskStatusId());
            task.setTaskStatus(status);
            taskRepository.save(task);

            TaskResponse response = new TaskResponse();
            BeanUtils.copyProperties(task, response);
            return response;
        }
        return null;
    }

    public TaskResponse createTask(TaskRequest request) {

        TaskEntity task = new TaskEntity();
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        task.setStartDate(request.getStartDate());
        task.setTitle(request.getTitle());

        TaskStatusEntity status = statusRepository.getReferenceById(request.getTaskStatusId());
        task.setTaskStatus(status);

        WorkSpaceEntity workSpace = workSpaceRepository.getReferenceById(request.getWorkSpaceId());
        task.setWorkSpace(workSpace);
        taskRepository.save(task);

        TaskResponse response = new TaskResponse();
        BeanUtils.copyProperties(task, response);
        return response;
    }
}
