package com.example.example2.controller;

import com.example.example2.Translator;
import com.example.example2.dto.response.ListFamilyMembersResponse;
import com.example.example2.error.ValidateException;
import com.example.example2.models.FamilyMembers;
import com.example.example2.service.FamilyMembersService;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/api/family-Member")
public class FamilyMemberController {

  @Autowired
  FamilyMembersService familyMembersService;

  @GetMapping("/")
  public List<FamilyMembers> listFamilyMembers() {
    return familyMembersService.allFamilyMembers();
  }

  @GetMapping("/{familyId}")
  public List<FamilyMembers> listFamilyById2(@PathVariable("familyId") Long id) {
    return familyMembersService.findFamilyById2(id);
  }

  @GetMapping("/prueba/{familyId}")
  public List<ListFamilyMembersResponse> listFamilyById(@PathVariable("familyId") Long id) {
    return familyMembersService.findFamilyById(id);
  }

  /**
   * crear miembros de familia.
   */
  @PostMapping
  public Long createFamilyMembers(@RequestBody @Validated FamilyMembers familyMembers, BindingResult br) {
    if (br.hasErrors()) {
      log.info("error of validation");
      List<String> errorList = new ArrayList<>();
      for (FieldError e : br.getFieldErrors()) {
        errorList.add(Translator.toLocale(e.getDefaultMessage()));
      }
      if (!errorList.isEmpty()) {
        throw new ValidateException(errorList);
      }
    }

    return familyMembersService.createFamilyMembers(familyMembers);
  }

  /**
   * controlador actualizar miembros de familia.
   */
  @PutMapping("/{FamilyMembersID}")
  public String updateFamilyMembers(@PathVariable("FamilyMembersID") Long id,
                                    @RequestBody @Validated FamilyMembers familyMembers, BindingResult br) {
    if (br.hasErrors()) {
      log.info("error of validation");
      List<String> errorList = new ArrayList<>();
      for (FieldError e : br.getFieldErrors()) {
        errorList.add(Translator.toLocale(e.getDefaultMessage()));
      }
      if (!errorList.isEmpty()) {
        throw new ValidateException(errorList);
      }
    }
    return familyMembersService.updateFamilyMembers(id, familyMembers);

  }

  @DeleteMapping("/{FamilyMembersID}")
  public String deleteFamilyMembers(@PathVariable("FamilyMembersID") Long id) {

    return familyMembersService.deleteFamilyMembers(id);
  }

}
