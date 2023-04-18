package com.sample.restapi.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.sample.restapi.entity.Local;
import com.sample.restapi.service.LocalService;

import net.minidev.json.JSONObject;

@WebMvcTest
public class LocalControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private LocalService localService;

    private Local local;

    @BeforeEach
    void setUp() {
        local = Local.builder()
            .id(1L)
            .name("Cinema")
            .floor("Fourth Floor")
            .code("Cin-040-4")
            .build();
    }

    @Test
    public void saveLocal() throws Exception {
        Local postLocal = Local.builder()
            .name("Cinema")
            .floor("Fourth Floor")
            .code("Cin-040-4")
            .build();

        var map = new HashMap<String, String>();
        map.put("name", "Cinema");
        map.put("floor", "Fourth Floor");
        map.put("code", "Cin-040-4");
        var content = new JSONObject(map).toJSONString();

        Mockito.when(localService.saveLocal(postLocal)).thenReturn(local);        
        mockMvc.perform(post("/saveLocal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
            ).andExpect(status().isOk());
        System.out.println(content);
    }

    @Test
    public void findLocalById() throws Exception {
        Mockito.when(localService.findLocalById(1L)).thenReturn(local);
        mockMvc.perform(get("/findLocalById/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value(local.getName()));
    }

}
