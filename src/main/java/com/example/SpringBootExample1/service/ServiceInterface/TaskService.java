package com.example.SpringBootExample1.service.ServiceInterface;

import com.example.SpringBootExample1.DTO.RequestDTO.TaskManagementRequestDTO;
import com.example.SpringBootExample1.DTO.RequestDTO.TaskUpdateRequestDTO;
import com.example.SpringBootExample1.DTO.RequestDTO.UserTaskDeleteRequestDTO;
import com.example.SpringBootExample1.DTO.ResponseDTO.TaskManagementResponseDTO;
import com.example.SpringBootExample1.Exception.InvalidPasswordException;
import com.example.SpringBootExample1.Exception.InvalidTaskException;
import com.example.SpringBootExample1.Exception.WrongUserActionException;

public interface TaskService {
    public TaskManagementResponseDTO addTask(TaskManagementRequestDTO taskManagementRequestDTO) throws WrongUserActionException, InvalidPasswordException;

    public TaskManagementResponseDTO updateTask(TaskUpdateRequestDTO taskUpdateRequestDTO) throws InvalidTaskException, WrongUserActionException, InvalidPasswordException;
    public String deleteTask(UserTaskDeleteRequestDTO userTaskDeleteRequestDTO) throws WrongUserActionException, InvalidPasswordException, InvalidTaskException;
}