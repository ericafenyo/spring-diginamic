package com.diginamic.hellodigi.controleurs;

import com.diginamic.hellodigi.model.Ville;
import com.diginamic.hellodigi.services.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class VilleControleur {

  @Autowired
  private VilleService villeService;

  @GetMapping("/villes")
  public List<Ville> getVilles() {
    return villeService.getVilles();
  }

  @GetMapping("/villes/{id}")
  public Ville getVilles(@PathVariable int id) {
    return villeService.getVilleById(id).orElse(null);
  }

  @PostMapping("/villes")
  public ResponseEntity<String> createVilles(@RequestBody Ville ville) {

    if (villeService.isPresent(ville)) {
      return new ResponseEntity<>(" La ville existe déjà ", HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity.ok(villeService.createVille(ville));
  }


  @PutMapping("/villes")
  public ResponseEntity<Object> update(@RequestBody Ville ville) {
    villeService.updateVille(ville);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping("/villes/{id}")
  public ResponseEntity<Object> deleteVille(@PathVariable int id) {
    villeService.deleteVille(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
