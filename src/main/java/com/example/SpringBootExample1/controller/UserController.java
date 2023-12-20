package com.example.SpringBootExample1.controller;


import com.example.SpringBootExample1.DTO.RequestDTO.UserRequestDTO;
import com.example.SpringBootExample1.DTO.RequestDTO.UserTaskRequestDTO;
import com.example.SpringBootExample1.DTO.ResponseDTO.TaskManagementListResponseDTO;
import com.example.SpringBootExample1.Exception.InvalidPasswordException;
import com.example.SpringBootExample1.Exception.WrongUserActionException;
import com.example.SpringBootExample1.service.ServiceInterface.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping("/add")
    public String addUser(@RequestBody UserRequestDTO userRequestDTO){

        return userService.addUser(userRequestDTO);
    }

    @PutMapping("/login")
    @PreAuthorize("hasRole('User')")
    public String loginUser(@RequestBody UserTaskRequestDTO userTaskRequestDTO) throws WrongUserActionException, InvalidPasswordException {
        String response = "";
        try {
            response = userService.loginUser(userTaskRequestDTO);
        }catch (Exception e){
            return e.getMessage();
        }
        return response;
    }
    @PutMapping("/logout")
    @PreAuthorize("hasRole('User')")
    public String logoutUser(@RequestParam(name = "emailId") String emailId) throws WrongUserActionException {
        String response = "";
        try {
            response = userService.logoutUser(emailId);
        }catch (Exception e){
            return e.getMessage();
        }
        return response;
    }

    @GetMapping("/all/tasks")
    @PreAuthorize("hasRole('User')")
    public ResponseEntity getAllTasksforUser(@RequestParam(name = "emailId") String emailId) throws WrongUserActionException{
        TaskManagementListResponseDTO taskManagementListResponseDTO;

        try{
            taskManagementListResponseDTO = userService.getAllTasksforUser(emailId);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(taskManagementListResponseDTO, HttpStatus.ACCEPTED);
    }

}