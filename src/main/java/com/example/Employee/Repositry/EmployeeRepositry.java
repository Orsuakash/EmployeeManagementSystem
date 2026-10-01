package com.example.Employee.Repositry;

import com.example.Employee.Entiy.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository

public interface EmployeeRepositry extends JpaRepository<Employee, Long> {

    Optional<Employee> findByIdAndDeletedIsFalse(Long id);

    List<Employee> findByDeletedIsFalse();

}




