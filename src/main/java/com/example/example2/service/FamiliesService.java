package com.example.example2.service;

import com.example.example2.error.CustomException;
import com.example.example2.models.Families;
import com.example.example2.repository.FamiliesRepository;
import com.example.example2.repository.FamilyMembersRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FamiliesService {
  @Autowired
  FamiliesRepository familiesRepository;

  @Autowired
  FamilyMembersRepository familyMembersRepository;

  public List<Families> allFamilies() {
    return familiesRepository.findAll();
  }

  public Long createFamily(Families family) {
    family = familiesRepository.save(family);
    return family.getFamilyId();
  }

  /**
   * actualizar familia.
   */

  public String updateFamily(Long id, Families family) {
    Optional<Families> familyOptional = familiesRepository.findById(id);
    if (familyOptional.isPresent()) {
      Families familyBase = familyOptional.get();

      familyBase.setHeadOfFamilyParentId(family.getHeadOfFamilyParentId());
      familyBase.setFamilyName(family.getFamilyName());

      familiesRepository.save(familyBase);

      return "Update successful";

    } else {
      throw new CustomException("400", "no existe id " + id);
    }
  }

  /**
   * borrar familia.
   */

  public String deleteFamily(Long id) {
    Optional<Families> familyOptional = familiesRepository.findById(id);
    if (familyOptional.isPresent()) {
      familyMembersRepository.deleteFamiliesMembersByFamilyId(id);

      familiesRepository.deleteById(id);
      return "Delete successful";
    } else {
      return "No found id";
    }
  }
}
