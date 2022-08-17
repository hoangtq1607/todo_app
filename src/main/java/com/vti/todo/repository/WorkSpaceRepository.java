package com.vti.todo.repository;

import com.vti.todo.dto.response.WorkSpaceResponse;
import com.vti.todo.entity.WorkSpaceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkSpaceRepository extends JpaRepository<WorkSpaceEntity, Integer> {

    @Query("SELECT NEW com.vti.todo.dto.response.WorkSpaceResponse(w.id, w.name, count(t)) " +
            "FROM WorkSpaceEntity w left join TaskEntity t ON w = t.workSpace " +
            "where w.account.email = :email " +
            "GROUP BY w")
    List<WorkSpaceResponse> getWorkSpaceResponseByUserEmail(String email);

}
