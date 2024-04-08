package com.diginamic.hellodigi.controleurs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HelloControleur {

  @GetMapping("/hello")
  public String direHello(){
    return "Hello";
  }
}
