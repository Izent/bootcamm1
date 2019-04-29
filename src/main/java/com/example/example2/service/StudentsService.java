package com.example.example2.service;

import com.example.example2.dto.request.StudentCreateRequest;
import com.example.example2.dto.request.StudentUpdateRequest;
import com.example.example2.dto.response.ListStudentResponse;
import com.example.example2.models.Parents;
import com.example.example2.models.StudentParent;
import com.example.example2.models.StudentParentPk;
import com.example.example2.models.Student;
import com.example.example2.repository.FamilyMembersRepository;
import com.example.example2.repository.StudentParentRepository;
import com.example.example2.repository.StudentsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentsService {

  @Autowired
  private StudentsRepository studentsRepository;

  @Autowired
  private StudentParentRepository studentParentRepository;
  @Autowired
  private FamilyMembersRepository familyMembersRepository;

  /**
   * LISTAR TODOS LOS ESTUDIANTES
   */
  public List<ListStudentResponse> allStudents() {

    List<Student> students = studentsRepository.findAll();
    List<ListStudentResponse> listStudentResponses = null;
    if (students != null && !students.isEmpty()) {

      listStudentResponses = new ArrayList<ListStudentResponse>();
      ListStudentResponse listStudentResponseBase = null;

      for (Student student : students) {
        listStudentResponseBase = new ListStudentResponse();
        listStudentResponseBase.setId(student.getStudentId());
        listStudentResponseBase.setFirstName(student.getFirstName());
        listStudentResponseBase.setMiddleName(student.getMiddleName());
        listStudentResponseBase.setLastName(student.getLastName());
        listStudentResponseBase.setGender(student.getGender());
        listStudentResponseBase.setDateOfBirth(student.getDateOfBirth());
        listStudentResponseBase.setOtherStudentDetails(student.getOtherStudentDetails());
        List<StudentParent> list = studentParentRepository
                .findAllByPrimaryKeyStudentIdStudentId(student.getStudentId());

        for (StudentParent sp : list) {
          List<ListStudentResponse.ParentListResponse> listaParent = new ArrayList<>();
          if (sp.getPrimaryKey().getParentId() != null) {
            ListStudentResponse.ParentListResponse parent = new ListStudentResponse
                    .ParentListResponse();
            parent.setId(sp.getPrimaryKey().getParentId().getParentId());
            parent.setFirstName(sp.getPrimaryKey().getParentId().getFirstName());
            listaParent.add(parent);
          }
          listStudentResponseBase.setParents(listaParent);
        }

        listStudentResponses.add(listStudentResponseBase);
      }
    }

    return listStudentResponses;
  }

  public List<ListStudentResponse> allStudentsTime() {
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();//ERROR ERROR
    }
    List<Student> students = studentsRepository.findAll();
    List<ListStudentResponse> listStudentResponses = null;
    if (students != null && !students.isEmpty()) {

      listStudentResponses = new ArrayList<ListStudentResponse>();
      ListStudentResponse listStudentResponseBase = null;

      for (Student student : students) {
        listStudentResponseBase = new ListStudentResponse();
        listStudentResponseBase.setId(student.getStudentId());
        listStudentResponseBase.setFirstName(student.getFirstName());
        listStudentResponseBase.setMiddleName(student.getMiddleName());
        listStudentResponseBase.setLastName(student.getLastName());
        listStudentResponseBase.setGender(student.getGender());
        listStudentResponseBase.setDateOfBirth(student.getDateOfBirth());
        listStudentResponseBase.setOtherStudentDetails(student.getOtherStudentDetails());
        List<StudentParent> list = studentParentRepository
                .findAllByPrimaryKeyStudentIdStudentId(student.getStudentId());

        for (StudentParent sp : list) {
          List<ListStudentResponse.ParentListResponse> listaParent = new ArrayList<>();
          if (sp.getPrimaryKey().getParentId() != null) {
            ListStudentResponse.ParentListResponse parent = new ListStudentResponse
                    .ParentListResponse();
            parent.setId(sp.getPrimaryKey().getParentId().getParentId());
            parent.setFirstName(sp.getPrimaryKey().getParentId().getFirstName());
            listaParent.add(parent);
          }
          listStudentResponseBase.setParents(listaParent);
        }

        listStudentResponses.add(listStudentResponseBase);
      }
    }

    return listStudentResponses;
  }

  /**
   * CREAR ESTUDIANTE.
   */
  public Long createStudent(StudentCreateRequest request) {
    Student student = new Student();
    student.setGender(request.getGender());
    student.setFirstName(request.getFirstName());
    student.setMiddleName(request.getMiddleName());
    student.setLastName(request.getLastName());
    student.setDateOfBirth(request.getDateOfBirth());
    student.setOtherStudentDetails(request.getOtherStudentDetails());

    student = studentsRepository.save(student);
    //System.out.println(request.getParents());

    if (request.getParents() != null) {
      for (StudentCreateRequest.ParentCreateRequest p : request.getParents()) {
        StudentParentPk pk = new StudentParentPk();
        pk.setStudentId(student);
        Parents parent = new Parents();
        parent.setParentId(p.getId());
        pk.setParentId(parent);

        StudentParent studentParent = new StudentParent();
        studentParent.setPrimaryKey(pk);
        studentParentRepository.save(studentParent);
      }
    }

    return student.getStudentId();
  }

  /**
   * ACTUALZIAR USUARIO Y ELIMINAR RELACION.
   */
  public String updateStudent(Long id, String[] parentsId, StudentUpdateRequest students) {
    Optional<Student> studentsOptional = studentsRepository.findById(id);
    if (studentsOptional.isPresent()) {
      Student studentBase = studentsOptional.get();

      studentBase.setGender(students.getGender());
      studentBase.setFirstName(students.getFirstName());
      studentBase.setMiddleName(students.getMiddleName());
      studentBase.setLastName(students.getLastName());
      studentBase.setDateOfBirth(students.getDateOfBirth());
      studentBase.setOtherStudentDetails(students.getOtherStudentDetails());

      studentsRepository.save(studentBase);

      int i = 0;
      for (StudentUpdateRequest.ParentUpdateRequest p : students.getParents()) {

        studentParentRepository.deleteAllByPrimaryKeyStudentIdStudentIdAndPrimaryKeyParentIdParentId(id, Long.valueOf(parentsId[i]));

        StudentParentPk pk = new StudentParentPk();
        pk.setStudentId(studentBase);
        Parents parent = new Parents();
        parent.setParentId(p.getId());
        pk.setParentId(parent);

        StudentParent studentParent = new StudentParent();
        studentParent.setPrimaryKey(pk);
        studentParentRepository.save(studentParent);
        i++;
      }
      return "Update successful";

    } else {
      return "No fund ID";
    }
  }

  /**
   * borrar estudiante.
   */
  public String deleteStudent(Long id) {
    Optional<Student> studentsOptional = studentsRepository.findById(id);
    if (studentsOptional.isPresent()) {

      studentParentRepository.deleteStudentParentByStudentId(id);
      familyMembersRepository.deleteFamiliesMembersByStudentId(id);

      studentsRepository.deleteById(id);
      return "Delete successful";
    } else {
      return "No found id";
    }
  }

  public List<Student> ListStudentByListStudent(List<Long> id) {
    try {
      TimeUnit.SECONDS.sleep(3);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return studentsRepository.findAllStudentByListStudentId(id);
  }

  public List<Student> ListStudentByListStudent2(List<Long> id) {

    return studentsRepository.findAllStudentByListStudentId(id);
  }

}
