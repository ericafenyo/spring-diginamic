package com.diginamic.hellodigi.controleurs;

import com.diginamic.hellodigi.model.Ville;
import com.diginamic.hellodigi.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping()
public class VilleControleur {

  @Autowired
  private VilleService villeService;

  @GetMapping("/villes")
  public List<Ville> getVilles() {
    return villeService.getVilles();
  }
}
