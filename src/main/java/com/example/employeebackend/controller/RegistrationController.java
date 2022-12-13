package com.example.employeebackend.controller;

import com.example.employeebackend.model.Admin;
import com.example.employeebackend.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")

public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public Admin registerAdmin(@RequestBody Admin admin) throws Exception {
        String tempEmail = admin.getEmail();

        if (tempEmail != null && !"".equals(tempEmail)){
            Admin adminObj1 = registrationService.searchByEmail(tempEmail);

            if (adminObj1 != null){
                throw new Exception("Admin with the email" + tempEmail + "already exists");
            }
        }

        Admin adminObj = null;
        adminObj = registrationService.addAdmin(admin);
        return adminObj;

    }
    @PostMapping("/login")
    public Admin loginAdmin(@RequestBody Admin admin) throws Exception {
        String tempEmail = admin.getEmail();
        String tempPassword = admin.getPassword();
        Admin adminObj = null;
        if (tempEmail != null && tempPassword != null){
             adminObj = registrationService.fetchEmailAndPassword(tempEmail, tempPassword);
        }
        if (adminObj == null){
            throw new Exception("Incorrect Credentials");
        }
        return adminObj;
    }

    @GetMapping("/adminList")
    public List<Admin> getAllAdmins(){
        return registrationService.getAllAdmins();
    }
}
