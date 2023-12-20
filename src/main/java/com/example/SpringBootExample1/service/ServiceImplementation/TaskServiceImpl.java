package com.example.SpringBootExample1.service.ServiceImplementation;


import com.example.SpringBootExample1.DTO.RequestDTO.TaskManagementRequestDTO;
import com.example.SpringBootExample1.DTO.RequestDTO.TaskUpdateRequestDTO;
import com.example.SpringBootExample1.DTO.RequestDTO.UserTaskDeleteRequestDTO;
import com.example.SpringBootExample1.DTO.ResponseDTO.TaskManagementResponseDTO;
import com.example.SpringBootExample1.Enum.Role;
import com.example.SpringBootExample1.Exception.InvalidTaskException;
import com.example.SpringBootExample1.Exception.WrongUserActionException;
import com.example.SpringBootExample1.entity.Task;
import com.example.SpringBootExample1.entity.User;
import com.example.SpringBootExample1.repository.TaskRepository;
import com.example.SpringBootExample1.repository.UserRepository;
import com.example.SpringBootExample1.service.ServiceInterface.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public TaskManagementResponseDTO addTask(TaskManagementRequestDTO taskManagementRequestDTO) throws WrongUserActionException {
        User user;
        try{
            user = userRepository.findByEmail(taskManagementRequestDTO.getEmail());
        }catch (Exception e){
            throw new WrongUserActionException("Invalid User!");
        }
        if(!user.getRole().equals(Role.ROLE_Admin)){
            if(!user.isLogin()){
                throw new WrongUserActionException("Please login first !!");
            }
        }
        Task task = Task.builder().title(taskManagementRequestDTO.getTitle())
                .description(taskManagementRequestDTO.getDescription())
                .dueDate(Date.valueOf(taskManagementRequestDTO.getDueDate()))
                .status(taskManagementRequestDTO.getStatus())
                .user(user)
                .build();

        user.getTaskList().add(task);
        userRepository.save(user);

        TaskManagementResponseDTO taskManagementResponseDTO = TaskManagementResponseDTO.builder()
                .userName(user.getUserName())
                .title(task.getTitle())
                .description(task.getDescription())
                .dueDate(task.getDueDate())
                .status(task.getStatus())
                .message("Congratulation!! Your task add.")
                .build();

        return taskManagementResponseDTO;
    }


    @Override
    public TaskManagementResponseDTO updateTask(TaskUpdateRequestDTO taskUpdateRequestDTO) throws InvalidTaskException, WrongUserActionException {
        User user;
        try{
            user = userRepository.findByEmail(taskUpdateRequestDTO.getEmail());
        }catch (Exception e){
            throw new WrongUserActionException("Invalid User!");
        }
        if(!user.getRole().equals(Role.ROLE_Admin)){
            if(!user.isLogin()){
                throw new WrongUserActionException("Please login first !!");
            }
        }

        Task task = null;

        List<Task> taskList = user.getTaskList();

        for(int i = 0; i<taskList.size(); i++){
            if(taskList.get(i).getId() == taskUpdateRequestDTO.getTaskId()){
                task = taskList.get(i);
            }
        }
        if(task == null){
            throw new InvalidTaskException("This task not exist.");
        }
        task.setTitle(taskUpdateRequestDTO.getTitle());
        task.setDescription(taskUpdateRequestDTO.getDescription());
        task.setDueDate(taskUpdateRequestDTO.getDueDate());
        task.setStatus(taskUpdateRequestDTO.getStatus());

        Task updatedTask = taskRepository.save(task);

        TaskManagementResponseDTO taskManagementResponseDTO = TaskManagementResponseDTO.builder()
                .userName(updatedTask.getUser().getUserName())
                .title(updatedTask.getTitle())
                .description(updatedTask.getDescription())
                .dueDate(updatedTask.getDueDate())
                .status(updatedTask.getStatus())
                .message("Your task updated.")
                .build();

        return taskManagementResponseDTO;
    }

    @Override
    public String deleteTask(UserTaskDeleteRequestDTO userTaskDeleteRequestDTO) throws WrongUserActionException, InvalidTaskException {
        User user;
        try{
            user = userRepository.findByEmail(userTaskDeleteRequestDTO.getEmail());
        }catch (Exception e){
            throw new WrongUserActionException("Invalid User!");
        }
        if(!user.isLogin()){
            throw new WrongUserActionException("Please login first !!");
        }

        Task currentTask = null;

        for(Task task : user.getTaskList()){
            if(task.getId() == userTaskDeleteRequestDTO.getTaskId()){
                currentTask = task;
            }
        }

        if(currentTask == null){
            throw new InvalidTaskException("This task not exist.");
        }
        user.getTaskList().remove(currentTask);

        taskRepository.delete(currentTask);

        userRepository.save(user);
        return "Your task have successfully deleted.";
    }
}