package com.example.StudentsAPI.Service;

import com.example.StudentsAPI.Custom.StudentNotFoundException;
import com.example.StudentsAPI.Custom.SubjectNotFoundException;
import com.example.StudentsAPI.Model.Student;
import com.example.StudentsAPI.Model.Subject;
import com.example.StudentsAPI.Repository.StudentRepository;
import com.example.StudentsAPI.Repository.SubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.StudentsAPI.Custom.Constants.NOT_FOUND_MSG;

@Service
@AllArgsConstructor
public class StudentService {

    private StudentRepository studentRepository;

    public Student save(Student student){
        return studentRepository.save(student);
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) throws StudentNotFoundException{
        Optional<Student> optionalStudent =  studentRepository.findById(id);
        if(optionalStudent.isEmpty())
            throw new StudentNotFoundException(String.format(NOT_FOUND_MSG, "Student", id));
        return optionalStudent.get();
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }


    public Student updateStudent(Long id, Student student) throws StudentNotFoundException {
        Student studentById = getStudentById(id);
        studentById.setFirstName(student.getFirstName());
        studentById.setLastName(student.getLastName());
        return studentRepository.save(studentById);
    }

    public Student getStudentByFirstOrLastName(String firstName, String lastName) {
        return studentRepository.findByFirstNameOrLastName(firstName, lastName);
    }
}
