package com.example.employeebackend.controller;

import com.example.employeebackend.exceptions.ResourceNotFoundException;
import com.example.employeebackend.model.Employee;
import com.example.employeebackend.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping("/employees")
    public List<Employee> findAllEmployees(){
        return employeeRepo.findAll();
    }


    @PostMapping("/employees")
   public Employee addEmployee(@RequestBody Employee employee){
        return employeeRepo.save(employee);
   }

   @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable long id){
       Employee employee = employeeRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee With ID : " + id + " does not exist"));

       return ResponseEntity.ok(employee);


   }

    @PutMapping("/employees/{id}")
   public ResponseEntity<Employee> updateEmployee(@PathVariable long id, @RequestBody Employee employeeDetails){
       Employee employee = employeeRepo.findById(id)
               .orElseThrow(()-> new ResourceNotFoundException("Employee With ID : " + id + " does not exist"));

       employee.setFirstName(employeeDetails.getFirstName());
       employee.setLastName(employeeDetails.getLastName());
       employee.setEmail(employeeDetails.getEmail());

       Employee updatedEmployee = employeeRepo.save(employee);

       return ResponseEntity.ok(updatedEmployee);

   }

  @DeleteMapping("employees/{id}")
   public ResponseEntity< Map<String,Boolean >>deleteEmployee(@PathVariable long id){
      Employee employee = employeeRepo.findById(id)
              .orElseThrow(()-> new ResourceNotFoundException("Employee With ID : " + id + " does not exist"));

    employeeRepo.delete(employee);

    Map<String,Boolean> response = new HashMap<>();
    response.put("deleted", true);
    return ResponseEntity.ok(response);
   }






}
