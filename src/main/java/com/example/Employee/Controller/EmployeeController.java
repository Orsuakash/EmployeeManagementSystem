package com.example.Employee.Controller;

import com.example.Employee.DTO.CreateRequestDto;
import com.example.Employee.DTO.CreateResponceDto;
import com.example.Employee.DTO.UpdateRequstDto;
import com.example.Employee.DTO.UpdateResponceDto;
import com.example.Employee.Entiy.Employee;
import com.example.Employee.Service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {


    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;

    }

    //create
@PostMapping
    public ResponseEntity<CreateResponceDto> create (@RequestBody @Valid CreateRequestDto createRequestDto){
        CreateResponceDto employecrt=employeeService.create(createRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(employecrt);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CreateResponceDto> getByid(@PathVariable Long id){
        CreateResponceDto empgetbyid=employeeService.getByid(id);

        return ResponseEntity.ok().body(empgetbyid);
    }

    //getall
@GetMapping
    public ResponseEntity<List<CreateResponceDto>> getAll(){

        List  employeelist=employeeService.getAll();

        return ResponseEntity.ok().body(employeelist);

    }

    //update
@PutMapping("/{id}")
    public ResponseEntity<UpdateResponceDto> update(@PathVariable Long id,
                                                    @RequestBody UpdateRequstDto updateRequstDto){
        UpdateResponceDto updateemp=employeeService.update(id,updateRequstDto);

        return ResponseEntity.ok().body(updateemp);

    }

    //delete
@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Employee isdelete=employeeService.delete(id);

        return ResponseEntity.ok().body("delete Sucessfully");
    }

    //soft delete
    @PatchMapping("/{id}")
    public ResponseEntity<String> softdelete(@PathVariable Long id){
        Employee isdelete=employeeService.softdelete(id);

        return ResponseEntity.ok().body("delete Sucessfully");
    }


}
