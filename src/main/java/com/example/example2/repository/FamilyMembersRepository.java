package com.example.example2.repository;

import com.example.example2.models.FamilyMembers;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FamilyMembersRepository extends JpaRepository<FamilyMembers, Long> {

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM family_members fm WHERE fm.family_id=?1", nativeQuery = true)
  void deleteFamiliesMembersByFamilyId(Long id);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM family_members fm WHERE fm.parent_id=?1", nativeQuery = true)
  void deleteFamiliesMembersByParentId(Long id);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM family_members fm WHERE fm.parent_id=?1", nativeQuery = true)
  void deleteFamiliesMembersByStudentId(Long id);


  List<FamilyMembers> findAllByFamilyIdFamilyId(Long id);


}
