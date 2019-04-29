package com.example.example2.controller;

import com.example.example2.models.Families;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Base64Utils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class FamilyControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void listFamily() throws Exception {
    this.mvc.perform(get("/api/family/all").header(HttpHeaders.AUTHORIZATION,
            "Basic " + Base64Utils.encodeToString("raul:Loerftujyo".getBytes())))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json; charset=utf-8"));
    this.mvc.perform(get("/api/family/all").header(HttpHeaders.AUTHORIZATION,
            "Basic " + Base64Utils.encodeToString("raul:sdfadasdsd".getBytes())))
            .andExpect(status().isUnauthorized())
            .andReturn();
  }


  @Test
  public void createFamily() throws Exception {
    this.mvc.perform(post("/api/family")
            .contentType(MediaType.APPLICATION_JSON)
            .content(" {\n" +
                    "        \"headOfFamilyParentId\": {\n" +
                    "            \"parentId\": 1\n" +
                    "        },\n" +
                    "\t        \"familyName\": \"Family\"\n" +
                    "    }}")).andExpect(status().isOk())
            .andReturn();
    this.mvc.perform(post("/api/family")
            .contentType(MediaType.APPLICATION_JSON)
            .content(" {}")).andExpect(status().isOk())
            .andReturn();

  }

  @Test
  public void updateFamily() throws Exception {
    this.mvc.perform(put("/api/family/2")
            .contentType(MediaType.APPLICATION_JSON)
            .content(" {\n" +
                    "        \"headOfFamilyParentId\": {\n" +
                    "            \"parentId\": 1\n" +
                    "        },\n" +
                    "\t        \"familyName\": \"Family ss\"\n" +
                    "    }}")).andExpect(status().isOk())
            .andReturn();
    this.mvc.perform(put("/api/family/1  ")
            .contentType(MediaType.APPLICATION_JSON)
            .content(" {}")).andExpect(status().isOk())
            .andReturn();
    this.mvc.perform(put("/api/family/0")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    "        \"headOfFamilyParentId\": {\n" +
                    "            \"parentId\": 1\n" +
                    "        },\n" +
                    "        \"familyName\": \"Family Editada\"\n" +
                    "    }")).andExpect(status().isConflict())
            .andReturn();

  }

  @Test
  public void deleteFamily() throws Exception {
    Families families = new Families();
    families.setFamilyId(3L);
    this.mvc.perform(MockMvcRequestBuilders.delete("/api/family/{familyId}", families.getFamilyId()))
            .andExpect(status().isOk());
    /*this.mvc.perform(MockMvcRequestBuilders.delete("/api/family/50"))
            .andExpect(status().isOk())
            .andExpect(content().string("Delete successful"));*/
    this.mvc.perform(MockMvcRequestBuilders.delete("/api/family/26"))
            .andExpect(status().isOk())
            .andExpect(content().string("No found id"));

  }
}