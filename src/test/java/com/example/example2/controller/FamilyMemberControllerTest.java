package com.example.example2.controller;

import com.example.example2.models.FamilyMembers;
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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FamilyMemberControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void listFamilyMembers() throws Exception {
    this.mvc.perform(get("/api/family-Member/"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json; charset=utf-8"));
  }

  @Test
  public void listFamilyById2() throws Exception {
    this.mvc.perform(get("/api/family-Member/prueba/1"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json; charset=utf-8"));
  }

  @Test
  public void createFamilyMembers() throws Exception {
    this.mvc.perform(post("/api/family-Member")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    " \t\"familyId\": {\n" +
                    " \t\t\"familyId\": 1\n" +
                    "    },\n" +
                    "    \"parentOrStudentMember\": \"student\",\n" +
                    "    \"parentId\": {\n" +
                    "            \"parentId\": 1\n" +
                    "    },\n" +
                    "    \"studentId\": {\n" +
                    "            \"studentId\":1\n" +
                    "    }\n" +
                    "}")).andExpect(status().isOk())
            .andReturn();
    this.mvc.perform(post("/api/family-Member")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}")).andExpect(status().isBadRequest())
            .andReturn();
  }

  @Test
  public void updateFamilyMembers() throws Exception {
    this.mvc.perform(put("/api/family-Member/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    " \"familyId\": {\n" +
                    " \t\"familyId\": 1\n" +
                    "    },\n" +
                    "  \"parentOrStudentMember\": \"fdfd\",\n" +
                    "    \"parentId\": {\n" +
                    "            \"parentId\": 1\n" +
                    "    },\n" +
                    "  \"studentId\": {\n" +
                    "            \"studentId\":1\n" +
                    "    }\n" +
                    "}")).andExpect(status().isOk())
            .andReturn();
    this.mvc.perform(put("/api/family-Member/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}")).andExpect(status().isBadRequest())
            .andReturn();
  }

  @Test
  public void deleteFamilyMembers() throws Exception {

    FamilyMembers familyMembers = new FamilyMembers();
    familyMembers.setFamilyMemberId(3L);
    this.mvc.perform(MockMvcRequestBuilders.delete("/api/family-Member/{familyMemberId}", familyMembers.getFamilyMemberId()))
            .andExpect(status().isOk());

    /*this.mvc.perform(MockMvcRequestBuilders.delete("/api/familyMember/49"))
            .andExpect(status().isOk())
            .andExpect(content().string("Delete successful"));*/
    this.mvc.perform(MockMvcRequestBuilders.delete("/api/family-Member/32"))
            .andExpect(status().isOk())
            .andExpect(content().string("No found id"));
  }
}