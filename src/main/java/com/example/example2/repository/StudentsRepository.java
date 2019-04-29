package com.example.example2.repository;

import com.example.example2.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentsRepository extends JpaRepository<Student, Long> {

  List<Student> findAllStudentByListStudentId(List<Long> ids);

}
