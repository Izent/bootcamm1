package com.example.example2.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name = "family_members")
public class FamilyMembers {
  @Id()
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long familyMemberId;


  @ManyToOne()
  @JoinColumn(name = "family_id")
  private Families familyId;

  @NotEmpty(message = "parentOrStudent-notEmpty")
  @JsonIgnoreProperties({"otherParentDetails", "gender"})
  @Column(length = 30, name = "parent_or_student_member")
  private String parentOrStudentMember;

  @JsonIgnoreProperties({"students", "otherParentDetails", "gender"})
  @ManyToOne()
  @JoinColumn(name = "parent_id")

  private Parents parentId;

  @JsonIgnoreProperties({"parents", "gender", "dateOfBirth", "otherStudentDetails"})
  @ManyToOne()
  @JoinColumn(name = "student_id")
  private Student studentId;


}
