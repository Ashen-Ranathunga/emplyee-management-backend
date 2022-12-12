package com.example.employeebackend.controller;

import com.example.employeebackend.model.Admin;
import com.example.employeebackend.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")

public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping("/register")
    public Admin createAdmin(@RequestBody Admin admin) throws Exception {
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
}
