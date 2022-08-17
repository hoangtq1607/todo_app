package com.vti.todo.controller;

import com.vti.todo.dto.request.WorkSpaceRequest;
import com.vti.todo.dto.response.WorkSpaceResponse;
import com.vti.todo.service.WorkSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/workspaces")
public class WorkSpaceController {

    @Autowired
    private WorkSpaceService workSpaceService;

    @PostMapping
    public WorkSpaceResponse createWorkSpace(@RequestBody @Valid WorkSpaceRequest request) {

        return workSpaceService.createWorkSpace(request);
    }

    @GetMapping
    public List<WorkSpaceResponse> getCurrentWorkspace() {
        return workSpaceService.getCurrentWorkspace();
    }

    @DeleteMapping
    public void getCurrentWorkspace(@RequestParam Set<Integer> id) {
        System.out.println(id);
    }

}
