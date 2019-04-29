package com.example.example2.service;

import com.example.example2.dto.request.ParentCreateRequest;
import com.example.example2.dto.request.ParentUpdateRequest;
import com.example.example2.dto.response.ListParentResponse;
import com.example.example2.models.Parents;
import com.example.example2.models.StudentParent;
import com.example.example2.models.StudentParentPk;
import com.example.example2.models.Student;
import com.example.example2.repository.FamiliesRepository;
import com.example.example2.repository.FamilyMembersRepository;
import com.example.example2.repository.ParentsRepository;
import com.example.example2.repository.StudentParentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ParentsService {

  @Autowired
  ParentsRepository parentsRepository;

  @Autowired
  StudentParentRepository studentParentRepository;

  @Autowired
  FamiliesRepository familiesRepository;

  @Autowired
  FamilyMembersRepository familyMembersRepository;

  /**
   * listar todos los padres.
   */

  public List<ListParentResponse> allParents() {
    List<Parents> parents = parentsRepository.findAll();
    List<ListParentResponse> listParentResponses = null;
    if (parents != null && !parents.isEmpty()) {
      listParentResponses = new ArrayList<ListParentResponse>();
      ListParentResponse listParentResponseBase = null;

      for (Parents parents1 : parents) {
        listParentResponseBase = new ListParentResponse();
        listParentResponseBase.setId(parents1.getParentId());
        listParentResponseBase.setFirstName(parents1.getFirstName());
        listParentResponseBase.setMiddleName(parents1.getMiddleName());
        listParentResponseBase.setLastName(parents1.getLastName());
        listParentResponseBase.setGender(parents1.getGender());
        listParentResponseBase.setOtherParentDetails(parents1.getOtherParentDetails());

        List<StudentParent> list = studentParentRepository
                .findAllByPrimaryKeyParentIdParentId(parents1.getParentId());
        for (StudentParent sp : list) {
          List<ListParentResponse.StudentListResponse> listStudent = new ArrayList<>();
          if (sp.getPrimaryKey().getParentId() != null) {
            ListParentResponse.StudentListResponse student = new ListParentResponse
                    .StudentListResponse();
            student.setId(sp.getPrimaryKey().getStudentId().getStudentId());
            student.setFirstName(sp.getPrimaryKey().getStudentId().getFirstName());
            listStudent.add(student);
          }
          listParentResponseBase.setStudents(listStudent);
        }
        listParentResponses.add(listParentResponseBase);
      }
    }


    return listParentResponses;
  }

  /**
   * crear parent.
   */

  public Long createParent(ParentCreateRequest request) {
    Parents parents = new Parents();


    parents.setGender(request.getGender());
    parents.setFirstName(request.getFirstName());
    parents.setMiddleName(request.getMiddleName());
    parents.setLastName(request.getLastName());
    parents.setOtherParentDetails(request.getOtherStudentDetails());
    parents = parentsRepository.save(parents);

    parents = parentsRepository.save(parents);

    for (ParentCreateRequest.StudentCreateRequest p : request.getStudent()) {
      StudentParentPk pk = new StudentParentPk();
      pk.setParentId(parents);
      Student students = new Student();
      students.setStudentId(p.getId());
      pk.setStudentId(students);

      StudentParent studentParent = new StudentParent();
      studentParent.setPrimaryKey(pk);
      studentParentRepository.save(studentParent);
    }
    return parents.getParentId();
  }

  /**
   * actualizar parent.
   */

  public String updateParent(Long id, ParentUpdateRequest parents) {
    Optional<Parents> parentsOptional = parentsRepository.findById(id);
    if (parentsOptional.isPresent()) {
      Parents parentBase = parentsOptional.get();
      parentBase.setGender(parents.getGender());
      parentBase.setFirstName(parents.getFirstName());
      parentBase.setMiddleName(parents.getMiddleName());
      parentBase.setLastName(parents.getLastName());
      parentBase.setOtherParentDetails(parents.getOtherParentDetails());

      parentsRepository.save(parentBase);

      System.out.println(parents.getStudent());


      for (ParentUpdateRequest.StudentCreateRequest p : parents.getStudent()) {
        StudentParentPk pk = new StudentParentPk();
        pk.setParentId(parentBase);
        Student student = new Student();
        student.setStudentId(p.getId());
        pk.setStudentId(student);

        StudentParent studentParent = new StudentParent();
        studentParent.setPrimaryKey(pk);
        studentParentRepository.save(studentParent);
      }

      return "Update successful";

    } else {
      return "No found ID";
    }
  }

  /**
   * eliminar parent.
   */

  public String deleteParent(Long id) {
    Optional<Parents> parentsOptional = parentsRepository.findById(id);

    if (parentsOptional.isPresent()) {
      familyMembersRepository.deleteFamiliesMembersByParentId(id);
      familiesRepository.deleteFamiliesByHeadParentId(id);
      studentParentRepository.deleteStudentParentByParentId(id);

      parentsRepository.deleteById(id);
      return "Delete successful";
    } else {
      return "No found id";
    }
  }
}
