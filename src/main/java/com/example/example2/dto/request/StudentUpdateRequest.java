package com.example.example2.dto.request;

import java.util.Date;
import java.util.List;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
public class StudentUpdateRequest {

  @NotEmpty(message = "gender-empty ")
  @Size(max = 1, message = "gender-size")
  private String gender;
  @NotEmpty(message = "student-firstName-notEmpty")
  @Size(max = 20, message = "name-size")
  private String firstName;
  @NotEmpty(message = "student-middleName-notEmpty")
  private String middleName;
  @NotEmpty(message = "student-lastName-notEmpty")
  private String lastName;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
  private Date dateOfBirth;
  @Size(max = 50, message = "details-size")
  private String otherStudentDetails;
  private List<ParentUpdateRequest> parents;

  @Data
  public static class ParentUpdateRequest {
    @NotNull
    private Long id;
  }
}
