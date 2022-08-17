package com.vti.todo.service;

import com.vti.todo.dto.request.WorkSpaceRequest;
import com.vti.todo.dto.response.WorkSpaceResponse;
import com.vti.todo.entity.AccountEntity;
import com.vti.todo.entity.WorkSpaceEntity;
import com.vti.todo.repository.AccountRepository;
import com.vti.todo.repository.TaskRepository;
import com.vti.todo.repository.WorkSpaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkSpaceService {

    @Autowired
    private WorkSpaceRepository workSpaceRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TaskRepository taskRepository;

    public WorkSpaceResponse createWorkSpace(WorkSpaceRequest request) {
        WorkSpaceEntity workSpace = new WorkSpaceEntity();
        workSpace.setName(request.getWorkSpaceName());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountEntity account = accountRepository.findByEmail(authentication.getName());
        //lay acount hien tai

        workSpace.setAccount(account);
        workSpaceRepository.save(workSpace);
        return new WorkSpaceResponse(workSpace.getId(), workSpace.getName(), 0L);
    }

    public List<WorkSpaceResponse> getCurrentWorkspace() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        List<WorkSpaceResponse> response = workSpaceRepository.getWorkSpaceResponseByUserEmail(email);

        return response;
    }
}
