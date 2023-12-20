package com.example.SpringBootExample1.DTO.RequestDTO;



import com.example.SpringBootExample1.Enum.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskManagementRequestDTO
{
    private String email;
    private String title;
    private String description;
    private String dueDate;
    private TaskStatus status;
}
