package com.example.SpringBootExample1.DTO.RequestDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserTaskRequestDTO {
    private String email;
    private String password;
}