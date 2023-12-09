package com.example.StudentsAPI.Controller;

import com.example.StudentsAPI.Model.Admin;
import com.example.StudentsAPI.Model.Student;
import com.example.StudentsAPI.Service.AdminService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AdminController {
    private AdminService adminService;

    @PostMapping(path = "/api/v3/register")
    public ResponseEntity<String> registerAdmin(@Valid @RequestBody Admin admin){
        adminService.registerAdmin(admin);
        return new ResponseEntity<>("Successfully created", HttpStatusCode.valueOf(201));
    }
}
