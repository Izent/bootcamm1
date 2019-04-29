package com.example.example2.controller;

import com.example.example2.models.Parents;
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
public class ParentControllerTest {

  @Autowired
  private MockMvc mvc;

  @Test
  public void listParent() throws Exception {
    this.mvc.perform(get("/api/parent/all"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json; charset=utf-8"));
  }

  @Test
  public void createParent() throws Exception {
    this.mvc.perform(post("/api/parent")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    "        \"gender\": \"M\",\n" +
                    "        \"firstName\": \"PADRE2\",\n" +
                    "        \"middleName\": \"asdasd\",\n" +
                    "        \"lastName\": \"asdasd\",\n" +
                    "        \"otherParentDetails\": \"vacio\",\n" +
                    "        \"student\": [\n" +
                    "            {\n" +
                    "                \"id\": 2\n" +
                    "            }\n" +
                    "        ]\n" +
                    "    }")).andExpect(status().isOk())
            .andReturn();

    this.mvc.perform(post("/api/parent")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}")).andExpect(status().isBadRequest())
            .andReturn();

  }

  @Test
  public void updateParent() throws Exception {

    this.mvc.perform(put("/api/parent/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\n" +
                    "        \"gender\": \"m\",\n" +
                    "        \"firstName\": \"PADRE2\",\n" +
                    "        \"middleName\": \"asdasd\",\n" +
                    "        \"lastName\": \"asdasd\",\n" +
                    "        \"otherParentDetails\": \"EDITandoo\",\n" +
                    "        \"student\": [\n" +
                    "            {\n" +
                    "                \"id\": 1\n" +
                    "                \n" +
                    "            }\n" +
                    "        ]\n" +
                    "}")).andExpect(status().isOk())
            .andReturn();
    this.mvc.perform(put("/api/parent/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{}")).andExpect(status().isBadRequest())
            .andReturn();
  }

  @Test
  public void deleteParent() throws Exception {
    Parents parents = new Parents();
    parents.setParentId(3L);

    this.mvc.perform(MockMvcRequestBuilders.delete("/api/parent/{parentId}", parents.getParentId()))
            .andExpect(status().isOk());
    /*this.mvc.perform(MockMvcRequestBuilders.delete("/api/parent/49"))
            .andExpect(status().isOk())
            .andExpect(content().string("Delete successful"));*/
    this.mvc.perform(MockMvcRequestBuilders.delete("/api/family/31"))
            .andExpect(status().isOk())
            .andExpect(content().string("No found id"));
  }
}