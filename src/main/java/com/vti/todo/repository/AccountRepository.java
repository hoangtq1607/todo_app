package com.vti.todo.repository;

import com.vti.todo.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Set;

public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {

    AccountEntity findByEmail(String username);

    boolean existsByEmail(String email);

    @Modifying
    void deleteByIdIn(Set<Integer> id);

}
