package com.example.Employee.Service;

import com.example.Employee.DTO.CreateRequestDto;
import com.example.Employee.DTO.CreateResponceDto;
import com.example.Employee.DTO.UpdateRequstDto;
import com.example.Employee.DTO.UpdateResponceDto;
import com.example.Employee.Entiy.Employee;
import com.example.Employee.GlobleException.RunTimeExpectionNotFound;
import com.example.Employee.Repositry.EmployeeRepositry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepositry employeeRepositry;

    public EmployeeService(EmployeeRepositry employeeRepositry) {
        this.employeeRepositry = employeeRepositry;

    }

    //    create
    public CreateResponceDto create(CreateRequestDto createRequestDto) {

        Employee employeecreat = mapToEntity(createRequestDto);

        employeeRepositry.save(employeecreat);

        return mapToDto(employeecreat);


    }

    //getbyid

    public CreateResponceDto getByid(Long id) {
        Employee empgetbyid = employeeRepositry.
                findByIdAndDeletedIsFalse(id).orElseThrow(() -> new RunTimeExpectionNotFound("Employee with id" + id + "not found"));
        return mapToDto(empgetbyid);
    }


    //getAll

    public List<CreateResponceDto> getAll() {
        List<Employee> employeeList = employeeRepositry.findAll();

        return employeeList.stream().map(this::mapToDto).toList();

    }
    //update 

    public UpdateResponceDto update(Long id, UpdateRequstDto updateRequstDto) {
        Employee updateemp = employeeRepositry.findByIdAndDeletedIsFalse(id).orElseThrow(() -> new RunTimeExpectionNotFound
                ("Employee with id" + id + "not found"));

        updateemp.setName(updateRequstDto.getName());
        updateemp.setEmail(updateRequstDto.getEmail());
        updateemp.setSalary(updateRequstDto.getSalary());
        updateemp.setSubject(updateRequstDto.getSubject());
        updateemp.setAge(updateemp.getAge());
        updateemp.setDeleted(false);

        employeeRepositry.save(updateemp);

        return mapToUpdateDto(updateemp);
    }

    //delete

    public Employee delete(Long id) {

        Employee isDeleted = employeeRepositry.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new RunTimeExpectionNotFound(
                        "Employee with id " + id + " not found"));

        employeeRepositry.delete(isDeleted);

        return isDeleted;
    }

    public Employee softdelete(Long id) {

        Employee isDeleted = employeeRepositry.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new RunTimeExpectionNotFound(
                        "Employee with id " + id + " not found"));

        isDeleted.setDeleted(true);

        employeeRepositry.save(isDeleted);

        return isDeleted;


    }




    private Employee mapToEntity(CreateRequestDto createRequestDto) {
        Employee employeecreate = new Employee();

        employeecreate.setName(createRequestDto.getName());
        employeecreate.setEmail(createRequestDto.getEmail());
        employeecreate.setSalary(createRequestDto.getSalary());
        employeecreate.setSubject(createRequestDto.getSubject());
        employeecreate.setAge(createRequestDto.getAge());
        employeecreate.setDeleted(false);


        return employeecreate;
    }


    private CreateResponceDto mapToDto(Employee employee) {

        CreateResponceDto createResponceDto = new CreateResponceDto();

        createResponceDto.setName(employee.getName());
        createResponceDto.setEmail(employee.getEmail());
        createResponceDto.setSalary(employee.getSalary());
        createResponceDto.setSubject(employee.getSubject());
        createResponceDto.setAge(employee.getAge());


        return createResponceDto;
    }


    private UpdateResponceDto mapToUpdateDto(Employee employee) {

        UpdateResponceDto updateResponceDto = new UpdateResponceDto();

        updateResponceDto.setName(employee.getName());
        updateResponceDto.setEmail(employee.getEmail());
        updateResponceDto.setSalary(employee.getSalary());
        updateResponceDto.setSubject(employee.getSubject());
        updateResponceDto.setAge(employee.getAge());

        return updateResponceDto;
    }


}
