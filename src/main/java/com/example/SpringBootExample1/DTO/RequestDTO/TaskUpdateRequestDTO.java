package com.example.SpringBootExample1.DTO.RequestDTO;


import com.example.SpringBootExample1.Enum.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskUpdateRequestDTO {
    private String email;
    private int taskId;
    private String title;
    private String description;
    private Date dueDate;
    private TaskStatus status;
}
