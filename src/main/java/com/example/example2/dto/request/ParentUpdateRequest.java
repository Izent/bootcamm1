package com.example.example2.dto.request;

import java.util.List;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ParentUpdateRequest {
  @NotEmpty(message = "gender-empty ")
  @Size(max = 1, message = "gender-size")
  private String gender;
  @NotEmpty(message = "parent-firstName-notEmpty")
  @Size(max = 20, message = "name-size")
  private String firstName;
  @NotEmpty(message = "parent-middleName-notEmpty")
  private String middleName;
  @NotEmpty(message = "parent-lastName-notEmpty")
  private String lastName;
  @Size(max = 50, message = "details-size")
  private String otherParentDetails;
  private List<StudentCreateRequest> student;

  @Data
  public static class StudentCreateRequest {
    private Long id;
  }
}
