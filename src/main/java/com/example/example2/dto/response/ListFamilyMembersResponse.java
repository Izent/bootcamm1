package com.example.example2.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ListFamilyMembersResponse {
  private Long familyId;
  private String familyName;
  private List<ParentListResponse> parent;
  private List<StudentListResponse> students;

  @Data
  public static class ParentListResponse {
    private Long id;
    private String firstName;
    private String member;

  }

  @Data
  public static class StudentListResponse {
    private Long id;
    private String firstName;
    private String member;

  }

}
