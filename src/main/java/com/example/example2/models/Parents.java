package com.example.example2.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;


@Data
@Entity
@Table(name = "parents")

public class Parents {

  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long parentId;
  @Column(length = 30, name = "gender")
  private String gender;
  @Column(length = 30, name = "first_name")
  private String firstName;
  @Column(length = 30, name = "middle_name")
  private String middleName;
  @Column(length = 30, name = "last_name")
  private String lastName;
  @Column(length = 50, name = "other_parent_details")
  private String otherParentDetails;
  @OneToMany(mappedBy = "primaryKey.studentId", fetch = FetchType.EAGER)
  private List<StudentParent> students;

}
