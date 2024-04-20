package com.diginamic.hellodigi.controllers;

import com.diginamic.hellodigi.repositories.CityRepository;
import com.diginamic.hellodigi.repositories.DepartmentRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;

@Controller
public class CityViewController {

  private final CityRepository cityRepository;
  private final DepartmentRepository departmentRepository;

  public CityViewController(CityRepository cityRepository, DepartmentRepository departmentRepository) {
    this.cityRepository = cityRepository;
    this.departmentRepository = departmentRepository;
  }

  @GetMapping("cities/view")
  public ModelAndView getCitiesView(Authentication authentication) {
    var cities = cityRepository.findAll();
    var model = new HashMap<String, Object>();
    model.put("cities", cities);
    model.put("authorities", authentication.getAuthorities());

    return new ModelAndView("cities", model);
  }
}
