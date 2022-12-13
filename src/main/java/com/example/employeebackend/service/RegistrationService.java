package com.example.employeebackend.service;

import com.example.employeebackend.model.Admin;
import com.example.employeebackend.repo.RegistrationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepo registrationRepo;

    public Admin addAdmin(Admin admin){
        return registrationRepo.save(admin);

    }

    public Admin searchByEmail (String email){
        return registrationRepo.getByEmail(email);
    }

    public Admin fetchEmailAndPassword(String email, String password){
        return registrationRepo.findByEmailAndPassword(email,password);
    }

    public List<Admin> getAllAdmins(){
        return registrationRepo.findAll();
    }
}
