package com.example.example2.controller;

import com.example.example2.Translator;
import com.example.example2.dto.request.ParentCreateRequest;
import com.example.example2.dto.request.ParentUpdateRequest;
import com.example.example2.dto.response.ListParentResponse;
import com.example.example2.error.ValidateException;
import com.example.example2.service.ParentsService;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/api/parent")
public class ParentController {
  @Autowired
  ParentsService parentsService;

  @GetMapping("/all")
  public List<ListParentResponse> listParent() {
    return parentsService.allParents();
  }

  @PostMapping
  public Long createParent(@RequestBody @Validated ParentCreateRequest parent, BindingResult br) {
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
    return parentsService.createParent(parent);
  }

  /**
   * actualizar padre o apoderado.
   */
  @PutMapping("/{ParentID}")
  public String updateParent(@PathVariable("ParentID") Long id,
                             @RequestBody @Validated ParentUpdateRequest parent, BindingResult br) {
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
    return parentsService.updateParent(id, parent);

  }

  @DeleteMapping("/{ParentID}")
  public String deleteParent(@PathVariable("ParentID") Long id) {

    return parentsService.deleteParent(id);
  }

}
