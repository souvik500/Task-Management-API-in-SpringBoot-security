package com.example.SpringBootExample1.controller;


import com.example.SpringBootExample1.DTO.RequestDTO.TaskManagementRequestDTO;
import com.example.SpringBootExample1.DTO.RequestDTO.TaskUpdateRequestDTO;
import com.example.SpringBootExample1.DTO.RequestDTO.UserTaskDeleteRequestDTO;
import com.example.SpringBootExample1.DTO.ResponseDTO.TaskManagementResponseDTO;

import com.example.SpringBootExample1.Exception.InvalidTaskException;
import com.example.SpringBootExample1.Exception.WrongUserActionException;
import com.example.SpringBootExample1.service.ServiceInterface.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;


    @PostMapping("/add")
    public ResponseEntity addTask(@RequestBody TaskManagementRequestDTO taskManagementRequestDTO){
        TaskManagementResponseDTO taskManagementResponseDTO;
        try{
            taskManagementResponseDTO = taskService.addTask(taskManagementRequestDTO);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(taskManagementResponseDTO, HttpStatus.ACCEPTED);
    }


    @PutMapping("/update/user_task")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public ResponseEntity updateTask(@RequestBody TaskUpdateRequestDTO taskUpdateRequestDTO) throws InvalidTaskException, WrongUserActionException {
        TaskManagementResponseDTO taskManagementResponseDTO;
        try{
            taskManagementResponseDTO = taskService.updateTask(taskUpdateRequestDTO);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(taskManagementResponseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/user_task")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public String deleteTask(@RequestBody UserTaskDeleteRequestDTO userTaskDeleteRequestDTO) throws WrongUserActionException, InvalidTaskException{
        String response = "";
        try {
            response = taskService.deleteTask(userTaskDeleteRequestDTO);
        }catch (Exception e){
            return e.getMessage();
        }
        return response;
    }
}




//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//@RestController
//@RequestMapping("/tasks")
//public class TaskController {
//
//
//    private TaskService taskService;
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<List<Task>> getAllTasks() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//
//        // Check for roles here
//        if (roles.contains("USER")) {
//            // Logic for getting tasks for a specific user
//            String username = authentication.getName();
//            List<Task> tasks = taskService.getAllTasksForUser(username);
//            return ResponseEntity.ok(tasks);
//        } else if (roles.contains("ADMIN")) {
//            // Logic for getting all tasks for admin
//            List<Task> tasks = taskService.getAllTasks();
//            return ResponseEntity.ok(tasks);
//        } else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//    }
//
//    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<Task> getTask(@PathVariable Long id) {
//        Task task = taskService.getTaskById(id);
//
//        if (task == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (hasPermission(authentication, task)) {
//            return ResponseEntity.ok(task);
//        } else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//    }
//
//    @PostMapping
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<Task> createTask(@RequestBody Task task) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//
//        if (roles.contains("USER")) {
//            // Set the user for the task based on the logged-in user
//            String username = authentication.getName();
//            User user = userService.findByUsername(username);
//            task.setUser(user);
//        } else if (!roles.contains("ADMIN")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//
//        Task createdTask = taskService.createTask(task);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
//    }
//
//    @PutMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
//        Task existingTask = taskService.getTaskById(id);
//
//        if (existingTask == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (hasPermission(authentication, existingTask)) {
//            Task updated = taskService.updateTask(id, updatedTask);
//            return ResponseEntity.ok(updated);
//        } else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
//        Task task = taskService.getTaskById(id);
//
//        if (task == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (hasPermission(authentication, task)) {
//            taskService.deleteTask(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//    }
//
//    private boolean hasPermission(Authentication authentication, Task task) {
//        String username = authentication.getName();
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
//
//        if (roles.contains("ADMIN") || task.getUser().getUsername().equals(username)) {
//            return true;
//        }
//
//        return false;
//    }
//}





//@RestController
//@RequestMapping("/tasks")
//public class TaskController {
//
//    @Autowired
//    private TaskService taskService;
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<List<Task>> getAllTasks(Principal principal) {
//        List<Task> tasks;
//        if (principal != null && principal.getName() != null) {
//            tasks = taskService.getAllTasksForUser(principal.getName());
//        } else {
//            // Handle the case where principal is null or has no name (shouldn't normally happen)
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//
//        return ResponseEntity.ok(tasks);
//    }
//
//    @GetMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<Task> getTask(@PathVariable Long id, Principal principal) {
//        // Check if the user is authorized to access this task
//        Task task = taskService.getTaskById(id);
//        if (task == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        if (!task.getUser().getUsername().equals(principal.getName()) && !principal.hasRole("ADMIN")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//
//        return ResponseEntity.ok(task);
//    }
//
//    @PostMapping
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<Task> createTask(@RequestBody Task task, Principal principal) {
//        // Set the user for the task based on the logged-in user
//        task.setUser(userService.findByUsername(principal.getName()));
//        Task createdTask = taskService.createTask(task);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
//    }
//
//    @PutMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<Task> updateTask(@PathVariable Long id,
//                                           @RequestBody Task updatedTask,
//                                           Principal principal)
//    {
//        // Check if the user is authorized to update this task
//        Task existingTask = taskService.getTaskById(id);
//        if (existingTask == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        if (!existingTask.getUser().getUsername().equals(principal.getName()) && !principal.hasRole("ADMIN")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//
//        // Update the task
//        Task updated = taskService.updateTask(id, updatedTask);
//        return ResponseEntity.ok(updated);
//    }
//
//    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
//    public ResponseEntity<Void> deleteTask(@PathVariable Long id, Principal principal) {
//        // Check if the user is authorized to delete this task
//        Task task = taskService.getTaskById(id);
//        if (task == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        if (!task.getUser().getUsername().equals(principal.getName()) && !principal.hasRole("ADMIN")) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//
//        // Delete the task
//        taskService.deleteTask(id);
//        return ResponseEntity.noContent().build();
//    }
//}





//@RestController
//public class TaskController {
//    public TaskService taskService;
//
//    public TaskController(TaskService taskService) {
//        this.taskService = taskService;
//    }
//
//    @PostMapping("/tasks")
//    public ResponseEntity<Task> createTask(@RequestBody CreateTaskInput createTaskInput) {
//        Task taskCreated = taskService.create(createTaskInput.toTask());
//
//        return new ResponseEntity<>(taskCreated, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/tasks")
//    public ResponseEntity<List<Task>> allTasks() {
//        List<Task> tasks = taskService.findAll();
//
//        return new ResponseEntity<>(tasks, HttpStatus.OK);
//    }
//
//    @GetMapping("/tasks/{id}")
//    public ResponseEntity<Task> oneTask(@PathVariable int id) {
//        Optional<Task> optionalTask = taskService.findById(id);
//
//        if (optionalTask.isPresent()) {
//            return new ResponseEntity<>(optionalTask.get(), HttpStatus.OK);
//        }
//
//        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
//
//    @PatchMapping("/tasks/{id}")
//    public ResponseEntity<Task> updateTask(@PathVariable int id, @RequestBody UpdateTaskInput updateTaskInput) {
//        Optional<Task> optionalTask = taskService.findById(id);
//
//        if (optionalTask.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//
//        Task taskToUpdate = optionalTask.get();
//
//        taskToUpdate.setStatus(updateTaskInput.status());
//        taskToUpdate.setDueDate(updateTaskInput.dueDate());
//
//        Task taskUpdated = taskService.update(taskToUpdate);
//
//        return new ResponseEntity<>(taskUpdated, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/tasks/{id}")
//    public ResponseEntity<Void> deleteTask(@PathVariable int id) {
//        taskService.delete(id);
//
//        return ResponseEntity.noContent().build();
//    }
//}
