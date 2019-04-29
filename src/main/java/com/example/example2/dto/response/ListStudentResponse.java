package com.example.example2.dto.response;

import java.util.Date;
import java.util.List;

import lombok.Data;


@Data
public class ListStudentResponse {

  private Long id;
  private String gender;
  private String firstName;
  private String middleName;
  private String lastName;
  private Date dateOfBirth;
  private String otherStudentDetails;
  private List<ParentListResponse> parents;

  @Data
  public static class ParentListResponse {
    private Long id;
    private String firstName;

  }
}
