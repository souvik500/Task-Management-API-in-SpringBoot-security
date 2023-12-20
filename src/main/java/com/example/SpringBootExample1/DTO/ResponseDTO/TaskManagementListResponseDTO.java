package com.example.SpringBootExample1.DTO.ResponseDTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskManagementListResponseDTO {
    private String userName;
    private List<UserTaskListResponseDTO> taskList;
}