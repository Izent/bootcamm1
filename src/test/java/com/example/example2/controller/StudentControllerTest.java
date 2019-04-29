package com.example.example2.controller;

import com.example.example2.models.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void listStudent() throws Exception {
    this.mvc.perform(get("/api/student/"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json; charset=utf-8"));
  }

  @Test
  public void createStudent() throws Exception {
    this.mvc.perform(post("/api/student")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    "  \"gender\":\"M\",\n" +
                    "  \"firstName\":\"add\",\n" +
                    "  \"middleName\":\"estudiante\",\n" +
                    "  \"lastName\":\"Perez2\",\n" +
                    "  \"dateOfBirth\":\"1992-06-04\",\n" +
                    "  \"otherStudentDetails\":\"quiero aprender\",\n" +
                    "  \"parents\" :[{\n" +
                    "  \t\"id\": 1  \n" +
                    "  }]\n" +
                    "}")).andExpect(status().isOk())
            .andReturn();


    this.mvc.perform(post("/api/student")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}")).andExpect(status().isBadRequest())
            .andReturn();
  }

  @Test
  public void updateStudent() throws Exception {
    this.mvc.perform(put("/api/student/1/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    "  \"gender\":\"M\",\n" +
                    "  \"firstName\":\"Raul2\",\n" +
                    "  \"middleName\":\"Ortiz2\",\n" +
                    "  \"lastName\":\"Perez2\",\n" +
                    "  \"dateOfBirth\":\"1992-06-04\",\n" +
                    "  \"otherStudentDetails\":\"prueba \",\n" +
                    "  \"parents\" :[{\n" +
                    "  \t\"id\": 2 }]\n" +
                    "}")).andExpect(status().isOk())
            .andReturn();
    this.mvc.perform(put("/api/student/1/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}")).andExpect(status().isBadRequest())
            .andReturn();
  }

  @Test
  public void deleteStudent() throws Exception {
    Student student = new Student();
    student.setStudentId(3L);
    this.mvc.perform(MockMvcRequestBuilders.delete("/api/student/{id}", student.getStudentId()))
            .andExpect(status().isOk());

    /*this.mvc.perform(MockMvcRequestBuilders.delete("/api/student/55"))
            .andExpect(status().isOk())
            .andExpect(content().string("Delete successful"));*/
    this.mvc.perform(MockMvcRequestBuilders.delete("/api/family/37"))
            .andExpect(status().isOk())
            .andExpect(content().string("No found id"));
  }

}