package com.example.example2.controller;

import com.example.example2.error.CustomException;
import com.example.example2.models.Families;
import com.example.example2.service.FamiliesService;

import java.util.List;
import java.util.logging.Logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/family")
public class FamilyController {

  private static final Logger LOGGER = Logger.getLogger(FamilyController.class.getName());

  @Autowired
  FamiliesService familiesService;

  @GetMapping("/all")
  public List<Families> listFamily() {
    /*log.info("Dentro de FamilyController metodo: listFamily");
    LOGGER.info("Otro Log");

    if(true){
      throw new CustomException("450", "Error de prueba");
    }*/
    return familiesService.allFamilies();
  }

  /**
   * asdasd .com
   */
  @PostMapping
  public Long createFamily(@RequestBody Families families) {


    return familiesService.createFamily(families);
  }

  /**
   * asdasd.
   */
  @PutMapping("/{familyID}")
  public String updateFamily(@PathVariable("familyID") Long id, @RequestBody Families families) {

    return familiesService.updateFamily(id, families);

  }

  @DeleteMapping("/{FamilyID}")
  public String deleteFamily(@PathVariable("FamilyID") Long id) {

    return familiesService.deleteFamily(id);
  }
}
