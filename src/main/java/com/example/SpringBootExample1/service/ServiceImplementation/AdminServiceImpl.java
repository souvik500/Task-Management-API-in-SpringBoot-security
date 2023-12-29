package com.example.SpringBootExample1.service.ServiceImplementation;


import com.example.SpringBootExample1.DTO.RequestDTO.UserRequestDTO;
import com.example.SpringBootExample1.DTO.ResponseDTO.TaskManagementResponseDTO;
import com.example.SpringBootExample1.Enum.Role;
import com.example.SpringBootExample1.Enum.TaskStatus;
import com.example.SpringBootExample1.entity.Admin;
import com.example.SpringBootExample1.entity.Task;
import com.example.SpringBootExample1.entity.User;
import com.example.SpringBootExample1.repository.TaskRepository;
import com.example.SpringBootExample1.repository.UserRepository;
import com.example.SpringBootExample1.service.ServiceInterface.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private TaskRepository taskManagementRepository;

    @Autowired
    private UserRepository userRepository;

    public AdminServiceImpl(TaskRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String addAdmin(UserRequestDTO userRequestDTO, Role role) {
        System.out.println(userRequestDTO +","+ role);

        User user = User.builder()
                .userName(userRequestDTO.getUserName())
                .email(userRequestDTO.getEmail())
                .password(new BCryptPasswordEncoder().encode(userRequestDTO.getPassword()))
                .role(role)
                .login(false)
                .build();

//        Admin admin = Admin.builder()
//                .username(userRequestDTO.getUserName())
//                .password(userRequestDTO.getPassword())
//                .build();

        userRepository.save(user);
        return "Congratulations! "+ user.getUserName()+" you have successfully registered!";
    }
    @Override
    public List<TaskManagementResponseDTO> findAllTask() {
        List<Task> taskList = taskManagementRepository.findAll();
        List<TaskManagementResponseDTO> taskManagementResponseDTOS = new ArrayList<>();

        for(Task task : taskList){
            TaskManagementResponseDTO managementResponseDTO = TaskManagementResponseDTO.builder()
                    .userName(task.getUser().getUserName())
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .dueDate(task.getDueDate())
                    .status(task.getStatus())
                    .build();
            taskManagementResponseDTOS.add(managementResponseDTO);
        }
        return taskManagementResponseDTOS;
    }

    @Override
    public List<TaskManagementResponseDTO> findAllTaskByStatus(TaskStatus taskStatus) {
        List<Task> taskList = taskManagementRepository.findAllTaskByStatus(taskStatus);

        List<TaskManagementResponseDTO> taskManagementResponseDTOS = new ArrayList<>();

        for(Task task : taskList){
            TaskManagementResponseDTO managementResponseDTO = TaskManagementResponseDTO.builder()
                    .userName(task.getUser().getUserName())
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .dueDate(task.getDueDate())
                    .status(task.getStatus())
                    .build();
            taskManagementResponseDTOS.add(managementResponseDTO);
        }
        return taskManagementResponseDTOS;
    }

}