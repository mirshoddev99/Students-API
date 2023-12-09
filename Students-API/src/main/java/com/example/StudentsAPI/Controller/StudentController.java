package com.example.StudentsAPI.Controller;

import com.example.StudentsAPI.Custom.StudentNotFoundException;
import com.example.StudentsAPI.Custom.SubjectNotFoundException;
import com.example.StudentsAPI.Model.Student;
import com.example.StudentsAPI.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v3/student/")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @PostMapping(value = "create")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student){
        return new ResponseEntity<>(studentService.save(student), HttpStatusCode.valueOf(201));
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> studentList = studentService.getStudents();
        return new ResponseEntity<>(studentList, HttpStatusCode.valueOf(200));
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Student> getStudentById(@Valid @PathVariable Long id)
            throws StudentNotFoundException {
        Student student = studentService.getStudentById(id);
        return new ResponseEntity<>(student, HttpStatusCode.valueOf(200));
    }

    @GetMapping(value = "find")
    public ResponseEntity<Student> getStudentByFirstOrLastName(
            @RequestParam String firstName,
             @RequestParam String lastName)
            throws StudentNotFoundException
    {
        System.out.println(firstName);
        System.out.println(lastName);
        Student student = studentService.getStudentByFirstOrLastName(firstName, lastName);
        return new ResponseEntity<>(student, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<String> deleteStudents(@Valid @PathVariable Long id){
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("Successfully deleted!", HttpStatusCode.valueOf(200));
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<Student> updateStudent(@Valid @PathVariable Long id, @Valid @RequestBody Student student)
            throws StudentNotFoundException {
        return new ResponseEntity<>(studentService.updateStudent(id, student), HttpStatus.valueOf(200));
    }
}
