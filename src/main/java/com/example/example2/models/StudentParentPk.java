package com.example.example2.models;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;


@Data
@Embeddable
public class StudentParentPk implements Serializable {


  @ManyToOne
  @JoinColumn(name = "parent_id")
  private Parents parentId;


  @ManyToOne
  @JoinColumn(name = "student_id")
  private Student studentId;


}
