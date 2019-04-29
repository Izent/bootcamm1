package com.example.example2.service;

import com.example.example2.dto.response.ListFamilyMembersResponse;
import com.example.example2.models.FamilyMembers;
import com.example.example2.models.Parents;
import com.example.example2.repository.FamilyMembersRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FamilyMembersService {

  @Autowired
  FamilyMembersRepository familyMembersRepository;

  public List<FamilyMembers> allFamilyMembers() {
    return familyMembersRepository.findAll();
  }

  public Long createFamilyMembers(FamilyMembers familyMember) {
    familyMember = familyMembersRepository.save(familyMember);
    return familyMember.getFamilyMemberId();
  }

  /**
   * actualizar miembro de familia.
   */

  public String updateFamilyMembers(Long id, FamilyMembers familyMember) {
    Optional<FamilyMembers> familyMemberOptional = familyMembersRepository.findById(id);
    if (familyMemberOptional.isPresent()) {
      FamilyMembers familyMembersBase = familyMemberOptional.get();


      familyMembersBase.setFamilyId(familyMember.getFamilyId());
      familyMembersBase.setParentOrStudentMember(familyMember.getParentOrStudentMember());
      familyMembersBase.setParentId(familyMember.getParentId());
      familyMembersBase.setStudentId(familyMember.getStudentId());

      familyMembersRepository.save(familyMembersBase);

      return "Update successful";

    } else {
      return "No fund ID";
    }
  }

  /**
   * eliminar miembro de familia.
   */

  public String deleteFamilyMembers(Long id) {
    Optional<FamilyMembers> familyMemberOptional = familyMembersRepository.findById(id);
    if (familyMemberOptional.isPresent()) {
      familyMembersRepository.deleteById(id);
      return "Delete successful";
    } else {
      return "No found id";
    }
  }

  /**
   * busccar todas las familias por familia id.
   */

  public List<ListFamilyMembersResponse> findFamilyById(Long id) {

    List<FamilyMembers> familyMembers = familyMembersRepository.findAllByFamilyIdFamilyId(id);
    List<ListFamilyMembersResponse> listFamilyMembersResponses = null;
    if (familyMembers != null && !familyMembers.isEmpty()) {
      listFamilyMembersResponses = new ArrayList<ListFamilyMembersResponse>();
      ListFamilyMembersResponse listFamilyMembersResponseBase = null;

      for (FamilyMembers familyMember : familyMembers) {
        listFamilyMembersResponseBase = new ListFamilyMembersResponse();
        listFamilyMembersResponseBase.setFamilyId(familyMember.getFamilyId().getFamilyId());
        listFamilyMembersResponseBase.setFamilyName(familyMember.getFamilyId().getFamilyName());


        List<ListFamilyMembersResponse.ParentListResponse> parentListResponses = new ArrayList<>();
        ListFamilyMembersResponse.ParentListResponse parentList = new ListFamilyMembersResponse.ParentListResponse();
        parentList.setId(familyMember.getParentId().getParentId());
        parentList.setFirstName(familyMember.getParentId().getFirstName());
        parentList.setMember(familyMember.getParentOrStudentMember());
        parentListResponses.add(parentList);
        listFamilyMembersResponseBase.setParent(parentListResponses);
      }
      listFamilyMembersResponses.add(listFamilyMembersResponseBase);
    }

    return listFamilyMembersResponses;


  }

  public List<FamilyMembers> findFamilyById2(Long id) {

    List<FamilyMembers> familyMembers = familyMembersRepository.findAllByFamilyIdFamilyId(id);
    System.out.println(familyMembers);
    return familyMembers;


  }
}
