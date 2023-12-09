package com.example.StudentsAPI.Service;

import com.example.StudentsAPI.Custom.StudentNotFoundException;
import com.example.StudentsAPI.Custom.SubjectNotFoundException;
import com.example.StudentsAPI.Model.Student;
import com.example.StudentsAPI.Model.Subject;
import com.example.StudentsAPI.Repository.StudentRepository;
import com.example.StudentsAPI.Repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.example.StudentsAPI.Custom.Constants.ALREADY_EXIST;
import static com.example.StudentsAPI.Custom.Constants.NOT_FOUND_MSG;

@Service
@AllArgsConstructor
public class SubjectService {
    private SubjectRepository subjectRepository;
    private StudentRepository studentRepository;

    public Subject saveSubject(Subject subject, Long studentId) throws Exception {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(optionalStudent.isEmpty())
            throw new StudentNotFoundException(String.format(NOT_FOUND_MSG, "Student", studentId));
        Student student = optionalStudent.get();
        Optional<Subject> optionalSubject =  student
                        .getSubjectList()
                        .stream()
                        .filter(x -> x.getName()
                        .equals(subject.getName()))
                        .findFirst();
        if (optionalSubject.isPresent()) throw new Exception(String.format(ALREADY_EXIST, subject.getName()));
        subject.setStudent(student);
        return subjectRepository.save(subject);
    }

    public Subject getSubjectById(Long subjectId) throws SubjectNotFoundException{
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        if(optionalSubject.isEmpty())
            throw new SubjectNotFoundException(String.format(NOT_FOUND_MSG, "Subject", subjectId));
        return optionalSubject.get();
    }

    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    public void deleteSubject(Long subjectId) {
        subjectRepository.deleteById(subjectId);
    }

    public Subject updateSubject(Long id, Subject subject) throws SubjectNotFoundException {
        Subject existingSubject = getSubjectById(id);
        existingSubject.setName(subject.getName());
        return subjectRepository.save(existingSubject);
    }
}
