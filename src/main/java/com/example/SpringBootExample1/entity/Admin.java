package com.example.SpringBootExample1.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Builder
@Data
@Entity
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;


}

