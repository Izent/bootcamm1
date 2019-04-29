package com.example.example2.repository;

import com.example.example2.models.StudentParent;
import com.example.example2.models.StudentParentPk;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface StudentParentRepository extends JpaRepository<StudentParent, StudentParentPk> {

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM student_parents sp WHERE sp.parent_id=?1", nativeQuery = true)
  void deleteStudentParentByParentId(Long id);

  @Modifying
  @Transactional
  @Query(value = "DELETE FROM student_parents sp WHERE sp.student_id=?1", nativeQuery = true)
  void deleteStudentParentByStudentId(Long id);

  /* @Modifying
   @Transactional
   @Query(value = "DELETE FROM student_parents sp WHERE sp.student_id=?1 and  sp.parent_id=?2", nativeQuery = true)
   void deleteStudentParentByStudentIdandStudentParent(Long studentId, Long parentId);
 */
  @Modifying
  @Transactional
  void deleteAllByPrimaryKeyStudentIdStudentIdAndPrimaryKeyParentIdParentId(Long studentId, Long parentId);

  List<StudentParent> findAllByPrimaryKeyStudentIdStudentId(Long id);

  List<StudentParent> findAllByPrimaryKeyParentIdParentId(Long id);

}
