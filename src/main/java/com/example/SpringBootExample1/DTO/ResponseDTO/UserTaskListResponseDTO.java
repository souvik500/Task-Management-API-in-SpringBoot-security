package com.example.SpringBootExample1.DTO.ResponseDTO;


import com.example.SpringBootExample1.Enum.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserTaskListResponseDTO {
    private String title;
    private String description;
    private Date dueDate;
    @Enumerated(EnumType.STRING)
    private TaskStatus status;
}