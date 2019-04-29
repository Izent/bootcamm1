package com.example.example2.dto.response;

import java.util.List;

import lombok.Data;


@Data
public class ListParentResponse {

  private Long id;
  private String gender;
  private String firstName;
  private String middleName;
  private String lastName;
  private String otherParentDetails;
  private List<StudentListResponse> students;

  @Data
  public static class StudentListResponse {
    private Long id;
    private String firstName;

  }
}
