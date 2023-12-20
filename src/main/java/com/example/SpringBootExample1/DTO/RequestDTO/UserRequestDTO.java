package com.example.SpringBootExample1.DTO.RequestDTO;


import com.example.SpringBootExample1.Enum.Role;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter @ToString
public class UserRequestDTO {
    private String userName;
    private String email;
    private String password;
    private Role role;
}