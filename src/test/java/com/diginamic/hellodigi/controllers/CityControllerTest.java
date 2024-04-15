package com.diginamic.hellodigi.controllers;

import com.diginamic.hellodigi.entities.CityEntity;
import com.diginamic.hellodigi.entities.DepartmentEntity;
import com.diginamic.hellodigi.repositories.CityRepository;
import com.diginamic.hellodigi.repositories.DepartmentRepository;
import com.diginamic.hellodigi.services.CityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class CityControllerTest {
  public static final String CITY_NAME = "Nantes";
  public static final long CITY_POPULATION = 309346;
  public static final String DEPARTMENT_NAME = "Loire-Atlantique";
  public static final String DEPARTMENT_CODE = "44";

  @Autowired
  private CityService service;

  @Autowired
  MockMvc mockMvc;

  @MockBean
  private CityRepository cityRepository;

  @MockBean
  private DepartmentRepository departmentRepository;

  CityEntity city = new CityEntity()
      .setId(1)
      .setName(CITY_NAME)
      .setPopulation(CITY_POPULATION)
      .setDepartment(
          new DepartmentEntity()
              .setId(1)
              .setName(DEPARTMENT_NAME)
              .setCode(DEPARTMENT_CODE)
      );

  @Test
  void controlTest() {
    Assertions.assertTrue(true);
  }

  @Test
  void getCitiesTest() throws Exception {
    // Given that my repository returns a list of cities
    when(cityRepository.findAll()).thenReturn(Collections.singletonList(city));


    // Then the response from the controller should be the same as my
    // mapped business model.
    mockMvc.perform(MockMvcRequestBuilders.get("/cities"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().string(containsString("Nantes")))
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].name").value(CITY_NAME))
        .andExpect(jsonPath("$[0].population").value(CITY_POPULATION))
        .andExpect(jsonPath("$[0].department.id").value(1))
        .andExpect(jsonPath("$[0].department.name").value(DEPARTMENT_NAME))
        .andExpect(jsonPath("$[0].department.code").value(DEPARTMENT_CODE));
  }
}
