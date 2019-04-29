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

import lombok.Data;

@Data
@Entity
@Table(name = "families")
public class Families {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long familyId;

  @ManyToOne()
  @JoinColumn(name = "head_of_family_parent")
  @JsonIgnoreProperties("students")
  private Parents headOfFamilyParentId;


  @Column(length = 30, name = "family_name")
  private String familyName;
}
