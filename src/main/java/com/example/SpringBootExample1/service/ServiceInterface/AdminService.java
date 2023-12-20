package com.example.SpringBootExample1.service.ServiceInterface;


import com.example.SpringBootExample1.DTO.RequestDTO.UserRequestDTO;
import com.example.SpringBootExample1.DTO.ResponseDTO.TaskManagementResponseDTO;
import com.example.SpringBootExample1.Enum.Role;
import com.example.SpringBootExample1.Enum.TaskStatus;

import java.util.List;

public interface AdminService {
    public List<TaskManagementResponseDTO> findAllTask();
    public List<TaskManagementResponseDTO> findAllTaskByStatus(TaskStatus taskStatus);

    public String addAdmin(UserRequestDTO userRequestDTO, Role role);
}