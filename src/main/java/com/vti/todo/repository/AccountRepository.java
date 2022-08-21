package com.vti.todo.repository;

import com.vti.todo.entity.AccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    AccountEntity findByEmail(String username);

    boolean existsByEmail(String email);

    @Modifying
    void deleteByIdIn(Set<Integer> id);

    @Query(value = "select * " +
            "from accounts " +
            "where (:search is null OR (full_name like :search or email like :search)) " +
            "AND (:role is null OR role = :role) " +
            "AND (:departmentId is null OR department_id = :departmentId) " +
            "ORDER BY created_date DESC ", nativeQuery = true)
    Page<AccountEntity> searchAccount(Integer departmentId, String role, String search, Pageable pageable);
}
