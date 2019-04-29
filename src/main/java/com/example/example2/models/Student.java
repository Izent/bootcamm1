package com.example.example2.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
@Entity
@Table(name = "students")
@NamedQuery(name = "Student.findAllStudentByListStudentId",
        query = "SELECT stu FROM Student stu WHERE stu.studentId IN (?1)")
public class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long studentId;
  @Column(length = 30, name = "gender")
  private String gender;
  @Column(length = 30, name = "first_name", nullable = false)
  private String firstName;
  @Column(length = 30, name = "middle_name")
  private String middleName;
  @Column(length = 30, name = "last_name")
  private String lastName;
  @Column(name = "date_of_birth")
  private Date dateOfBirth;
  @Column(length = 50, name = "other_student_details")
  private String otherStudentDetails;
  @JsonIgnore
  @OneToMany(mappedBy = "primaryKey.parentId", fetch = FetchType.EAGER)
  private List<StudentParent> parents;

}
