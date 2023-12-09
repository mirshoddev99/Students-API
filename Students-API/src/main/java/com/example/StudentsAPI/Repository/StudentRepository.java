package com.example.StudentsAPI.Repository;

import com.example.StudentsAPI.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Here, we must use class name to make a query instead of table name in the Database
    @Query("select s from Student s where s.firstName = ?1 or s.lastName = ?2")
    public Student findByFirstNameOrLastName(String firstName, String lastName);

    // Update the Student-first name based on the id of the student
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "update student set first_name = ?1 where id = ?2")
    public void updateStudentByStudentId(String firstName, Long id);

}
