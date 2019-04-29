package com.example.example2.repository;

import com.example.example2.models.Families;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface FamiliesRepository extends JpaRepository<Families, Long> {

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM families f WHERE f.head_of_family_parent=?1", nativeQuery = true)
  void deleteFamiliesByHeadParentId(Long id);


}
