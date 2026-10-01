package com.example.Employee.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpectionDto {

    private LocalDateTime timestamp;
    private  int statuscode;
    private String message;
    private String fieldError;
    private String path;
}
