package com.example.Employee.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateResponceDto {
    @NotBlank(message = "name not null ")
    private String name;

    @NotBlank(message = "Email not null ")
    @Email
    private String email;

    @NotNull(message = "Salary is required")

    @Positive(message = "Salary must be greater than 0")
    private double salary;

    private String subject;

    @Min(value = 18, message = "Age must be at least 18")
    @Max(value = 60, message = "Age must not exceed 60")
    private int age;
}
