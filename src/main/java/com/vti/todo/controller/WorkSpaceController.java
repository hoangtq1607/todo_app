package com.vti.todo.controller;

import com.vti.todo.dto.request.WorkSpaceRequest;
import com.vti.todo.dto.response.WorkSpaceResponse;
import com.vti.todo.service.WorkSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/workspaces")
public class WorkSpaceController {

    @Autowired
    private WorkSpaceService workSpaceService;

    @PostMapping
    public WorkSpaceResponse createWorkSpace(@RequestBody @Valid WorkSpaceRequest request) {

        return workSpaceService.createWorkSpace(request);
    }

    //TODO Lay danh sách workspace của user hiện tại, không để user này lấy được danh sách workspace của user khác
    @GetMapping
    public List<WorkSpaceResponse> getCurrentWorkspace() {
        return workSpaceService.getCurrentWorkspace();
    }
}
