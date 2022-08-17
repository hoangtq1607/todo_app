package com.vti.todo.repository;

import com.vti.todo.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    int countByWorkSpaceId(Integer id);
}
