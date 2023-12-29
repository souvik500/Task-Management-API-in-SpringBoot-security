package com.example.SpringBootExample1.controller;


import com.example.SpringBootExample1.DTO.RequestDTO.UserRequestDTO;
import com.example.SpringBootExample1.DTO.ResponseDTO.TaskManagementResponseDTO;
import com.example.SpringBootExample1.Enum.Role;
import com.example.SpringBootExample1.Enum.TaskStatus;
import com.example.SpringBootExample1.service.ServiceInterface.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/add")
    public String addAdmin(@RequestBody UserRequestDTO userRequestDTO){
         return adminService.addAdmin(userRequestDTO, Role.ROLE_Admin);
    }

    @GetMapping("/find/all/task")
    @PreAuthorize("hasRole('Admin')")
    public List<TaskManagementResponseDTO> findAllTask(){
        return adminService.findAllTask();
    }


    @GetMapping("/get/category/{taskStatus}")
    @PreAuthorize("hasRole('Admin')")
    public List<TaskManagementResponseDTO> getAllProductByCategory(@PathVariable("taskStatus") TaskStatus taskStatus){
        return adminService.findAllTaskByStatus(taskStatus);
    }
}