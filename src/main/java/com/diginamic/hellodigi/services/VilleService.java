package com.diginamic.hellodigi.services;

import com.diginamic.hellodigi.model.Ville;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class VilleService {
  private Map<String, Ville> villeChache = new HashMap<>();
  private AtomicInteger index = new AtomicInteger(1);
//    villeList.add(new Ville("Pau", 72200));
//    villeList.add(new Ville("Paris", 2133111));
//    villeList.add(new Ville("Marseille", 873076));
//    villeList.add(new Ville("Lyon", 522250));
//    villeList.add(new Ville("Toulouse", 504078));
//    villeList.add(new Ville("Nice", 348085));
//    villeList.add(new Ville("Nantes", 323204));

  public List<Ville> getVilles() {
    return villeChache.values().stream().toList();
  }


  public Optional<Ville> getVilleById(int id) {
    return villeChache.values().stream()
        .filter(ville -> ville.getId() == id)
        .findFirst();
  }

  public String createVille(Ville ville) {
    Ville entity = ville.setId(index.getAndIncrement());
    villeChache.put(entity.getNom(), entity);

    return "Ville insérée avec succès";
  }

  public void deleteVille(int id) {
    getVilleById(id).ifPresent(value -> villeChache.remove(value.getNom()));
  }

  public void updateVille(Ville ville) {
    getVilleById(ville.getId()).ifPresent(value -> villeChache.put(value.getNom(), ville));
  }

  public boolean isPresent(Ville ville) {
    return Optional.ofNullable(villeChache.get(ville.getNom())).isPresent();
  }
}
