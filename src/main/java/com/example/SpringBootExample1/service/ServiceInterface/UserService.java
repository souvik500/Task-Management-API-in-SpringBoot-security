package com.example.SpringBootExample1.service.ServiceInterface;


import com.example.SpringBootExample1.DTO.RequestDTO.UserRequestDTO;
import com.example.SpringBootExample1.DTO.RequestDTO.UserTaskRequestDTO;
import com.example.SpringBootExample1.DTO.ResponseDTO.TaskManagementListResponseDTO;
import com.example.SpringBootExample1.Exception.InvalidPasswordException;
import com.example.SpringBootExample1.Exception.WrongUserActionException;

public interface UserService {
    public String addUser(UserRequestDTO userRequestDTO);
    public String loginUser(UserTaskRequestDTO userTaskRequestDTO) throws WrongUserActionException, InvalidPasswordException;
    public String logoutUser(String emailId) throws WrongUserActionException;

    public TaskManagementListResponseDTO getAllTasksforUser(String emailId) throws WrongUserActionException, InvalidPasswordException;
}