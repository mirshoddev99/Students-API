package com.example.StudentsAPI.Repository;

import com.example.StudentsAPI.Model.Student;
import com.example.StudentsAPI.Model.Subject;
import org.hibernate.query.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;


@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder().firstName("Mirshod").lastName("Oripov").build();
        Student student1 = Student.builder().firstName("Sitora").lastName("Oripova").build();
        Student student2 = Student.builder().firstName("Mushtariy").lastName("Oripova").build();
        Student student3 = Student.builder().firstName("Yasmina").lastName("Dilshodova").build();
        studentRepository.saveAll(List.of(student, student1, student2, student3));
    }

    @Test
    public void getStudentByFirstNameOrLastName(){
        Student student = studentRepository.findByFirstNameOrLastName("Tolib", "Oripov");
        System.out.println(student);
    }

    @Test
    public void updateStudentFirstNameById(){
       studentRepository.updateStudentByStudentId("Mirshodbek", 1L);
        Student student = studentRepository.findByFirstNameOrLastName("Tolib", "Oripov");
        System.out.println(student);
    }

    @Test
    public void getAllPaginatedStudentsTest() {
    }


}