package com.example.example2.dto.request;

import java.util.Date;

import java.util.List;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class StudentCreateRequest {

  @NotEmpty(message = "gender-empty ")
  @Size(max = 1, message = "gender-size")
  private String gender;
  @NotEmpty(message = "studentId-notEmpty")
  @Size(max = 20, message = "name-size")
  private String firstName;
  @NotEmpty(message = "student-middleName-notEmpty")
  private String middleName;
  @NotEmpty(message = "student-lastName-notEmpty")
  private String lastName;

  private Date dateOfBirth;
  @Size(max = 50, message = "details-size")
  private String otherStudentDetails;
  private List<ParentCreateRequest> parents;

  @Data
  public static class ParentCreateRequest {
    @NotNull
    private Long id;
  }
}
