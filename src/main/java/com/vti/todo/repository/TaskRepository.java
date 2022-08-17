package com.vti.todo.repository;

import com.vti.todo.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
    int countByWorkSpaceId(Integer id);

    List<TaskEntity> findByWorkSpaceId(Integer workspaceId);

    @Modifying
    void deleteByIdIn(List<Integer> id);
}
