package com.sample.restapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.sample.restapi.entity.Local;
import com.sample.restapi.repository.LocalRepository;

@SpringBootTest
public class LocalServiceTest {
    
    @Autowired
    private LocalService localService;

    @MockBean
    private LocalRepository localRepository;

    @BeforeEach
    void setUp() {
        Local local = Local.builder()
            .id(1L)
            .name("PetShop")
            .floor("Second Floor")
            .code("Pet-050-2")
            .build();

        Mockito.when(localRepository.findByNameIgnoreCase("PetShop")).thenReturn(Optional.of(local));
    }

    @Test
    @DisplayName("Prueba de obtención de información de un local enviando un nombre válido")
    public void findByNameIgnoreCaseShouldFound() {
        String localName = "PetShop";
        Local local = localService.findByNameIgnoreCase(localName).get();
        assertEquals(localName, local.getName());
        System.out.println("local = " + local);
    }

}
