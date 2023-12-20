package com.example.SpringBootExample1.service.ServiceImplementation;


import com.example.SpringBootExample1.DTO.RequestDTO.UserRequestDTO;
import com.example.SpringBootExample1.DTO.RequestDTO.UserTaskRequestDTO;
import com.example.SpringBootExample1.DTO.ResponseDTO.TaskManagementListResponseDTO;
import com.example.SpringBootExample1.DTO.ResponseDTO.UserTaskListResponseDTO;
import com.example.SpringBootExample1.Enum.Role;
import com.example.SpringBootExample1.Exception.InvalidPasswordException;
import com.example.SpringBootExample1.Exception.WrongUserActionException;
import com.example.SpringBootExample1.entity.Task;
import com.example.SpringBootExample1.entity.User;
import com.example.SpringBootExample1.repository.UserRepository;
import com.example.SpringBootExample1.service.ServiceInterface.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
//    @Autowired
//    PasswordEncoder passwordEncoder;

    private static final Logger logger = Logger.getLogger(String.valueOf(UserServiceImpl.class));

    @Override
    public String addUser(UserRequestDTO userRequestDTO)
    {
        System.out.println(userRequestDTO); // Print the entire UserRequestDTO object
        System.out.println(userRequestDTO.getUserName()); // Print the userName field


        User user = User.builder()
                .userName(userRequestDTO.getUserName())
                .email(userRequestDTO.getEmail())
                .password(new BCryptPasswordEncoder().encode(userRequestDTO.getPassword()))
                .role(Role.ROLE_User)
                .login(false)
                .build();

        userRepository.save(user);

        /*Debug*/
        System.out.println(user.getUserName());
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        System.out.println(user.getId());
        System.out.println(user.getRole());

        return "Congratulations! "+ user.getUserName()+" you have successfully registered as a user!";
    }

    @Override
    public String loginUser(UserTaskRequestDTO userTaskRequestDTO) throws WrongUserActionException, InvalidPasswordException {
        User user;
        try{
            user = userRepository.findByEmail(userTaskRequestDTO.getEmail());
        }catch (Exception e){
            throw new WrongUserActionException("Invalid User!");
        }
        if(!user.getPassword().equals(userTaskRequestDTO.getPassword())){
            throw new InvalidPasswordException("Please enter your correct password !!");
        }
        user.setLogin(true);

        userRepository.save(user);

        return "Congratulations! "+ user.getUserName()+" you have successfully login.";
    }

    @Override
    public String logoutUser(String emailId) throws WrongUserActionException {
        User user;
        try{
            user = userRepository.findByEmail(emailId);
        }catch (Exception e){
            throw new WrongUserActionException("Invalid User!");
        }
        if(!user.isLogin()){
            throw new WrongUserActionException("Invalid action, please login first");
        }

        user.setLogin(false);

        userRepository.save(user);

        return "Logout, see you again";
    }
    @Override
    public TaskManagementListResponseDTO getAllTasksforUser(String emailId) throws WrongUserActionException {
        User user;
        try{
            user = userRepository.findByEmail(emailId);
        }catch (Exception e){
            throw new WrongUserActionException("Invalid User!");
        }
        if(!user.isLogin()){
            throw new WrongUserActionException("Please login first !!");
        }
        List<UserTaskListResponseDTO> userTaskListResponseDTOList = new ArrayList<>();
        for(Task task : user.getTaskList()){
            UserTaskListResponseDTO userTaskListResponseDTO = UserTaskListResponseDTO.builder()
                    .title(task.getTitle())
                    .description(task.getDescription())
                    .dueDate(task.getDueDate())
                    .status(task.getStatus())
                    .build();
            userTaskListResponseDTOList.add(userTaskListResponseDTO);
        }
        TaskManagementListResponseDTO taskManagementListResponseDTO = TaskManagementListResponseDTO.builder()
                .userName(user.getUserName())
                .taskList(userTaskListResponseDTOList)
                .build();
        return taskManagementListResponseDTO;
    }
}