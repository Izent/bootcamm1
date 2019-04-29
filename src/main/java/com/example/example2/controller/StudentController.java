package com.example.example2.controller;

import com.example.example2.Translator;
import com.example.example2.dto.request.StudentCreateRequest;
import com.example.example2.dto.request.StudentUpdateRequest;
import com.example.example2.dto.response.ListStudentResponse;
import com.example.example2.error.ValidateException;
import com.example.example2.models.Student;
import com.example.example2.repository.StudentsRepository;
import com.example.example2.service.StudentsService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/student")
public class StudentController {

  @Autowired
  StudentsService studentsService;

  @Autowired
  StudentsRepository studentsRepository;

  @GetMapping("/")
  public List<ListStudentResponse> listStudent() {
    return studentsService.allStudents();
  }

  @GetMapping("/list-time")
  public List<ListStudentResponse> listStudentTime() {
    return studentsService.allStudentsTime();
  }

  @GetMapping("/list")
  public List<Student> listStudentByList(@RequestParam(name = "id") List<Long> ids) {
    log.info(String.valueOf(ids));
    List<Long> aa = new ArrayList<Long>(Arrays.asList(1L, 2L, 3L));
    List<Student> studentList = studentsService.ListStudentByListStudent(ids);
    return studentList;

  }

  @PostMapping
  public Long createStudent(@RequestBody @Validated StudentCreateRequest student, BindingResult br) {

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

    return studentsService.createStudent(student);
  }

  /**
   * crear miembros de familia.
   */
//BindingResult extiende de Errors para que validator pueda funcionar
  @PutMapping("/{StudentID}/{ParentsID}")
  public String updateStudent(@PathVariable("StudentID") Long id, @PathVariable("ParentsID") String[] parentsId,
                              @RequestBody @Validated StudentUpdateRequest student, BindingResult br) {

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
    return studentsService.updateStudent(id, parentsId, student);

  }

  @DeleteMapping("/{StudentID}")
  public String deleteStudent(@PathVariable("StudentID") Long id) {

    return studentsService.deleteStudent(id);
  }


}
