package com.example.StudentsAPI.Controller;

import com.example.StudentsAPI.Custom.StudentNotFoundException;
import com.example.StudentsAPI.Custom.SubjectNotFoundException;
import com.example.StudentsAPI.Model.Student;
import com.example.StudentsAPI.Model.Subject;
import com.example.StudentsAPI.Service.SubjectService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/v3/subject/")
public class SubjectController {
    private SubjectService subjectService;

    @GetMapping(path = "all")
    public ResponseEntity<List<Subject>> getAllStudents(){
        return new ResponseEntity<>(subjectService.getAllSubjects(), HttpStatusCode.valueOf(200));
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Subject> getSubjectById(@Valid @PathVariable Long id)
            throws SubjectNotFoundException
    {
        Subject subject = subjectService.getSubjectById(id);
        return new ResponseEntity<>(subject, HttpStatusCode.valueOf(200));
    }

    @PostMapping(path = "create/student/{studentId}")
    public ResponseEntity<Subject> createSubject(@Valid @RequestBody Subject subject, @Valid @PathVariable Long studentId)
            throws Exception
    {
        return new ResponseEntity<>(subjectService.saveSubject(subject, studentId), HttpStatusCode.valueOf(201));
    }

    @DeleteMapping(path = "delete/{subjectId}")
    public ResponseEntity<String> deleteSubject(@Valid @PathVariable Long subjectId)
    {
        subjectService.deleteSubject(subjectId);
        return new ResponseEntity<>("Successfully deleted!", HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "update/{id}")
    public ResponseEntity<Subject> updateSubject(@Valid @PathVariable Long id, @Valid @RequestBody Subject subject)
            throws SubjectNotFoundException
    {
        return new ResponseEntity<>(subjectService.updateSubject(id, subject), HttpStatus.valueOf(200));

    }
}
