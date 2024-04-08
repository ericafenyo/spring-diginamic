package com.diginamic.hellodigi.controleurs;

import com.diginamic.hellodigi.services.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HelloControleur {

  @Autowired
  private HelloService helloService;

  @GetMapping("/hello")
  public String direHello() {
    return helloService.salutations();
  }
}
