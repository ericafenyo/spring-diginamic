package com.diginamic.hellodigi.services;

import com.diginamic.hellodigi.model.Ville;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VilleService {
  public List<Ville> getVilles() {
    List<Ville> villeList = new ArrayList<>();
    villeList.add(new Ville("Pau", 72200));
    villeList.add(new Ville("Paris", 2133111));
    villeList.add(new Ville("Marseille", 873076));
    villeList.add(new Ville("Lyon", 522250));
    villeList.add(new Ville("Toulouse", 504078));
    villeList.add(new Ville("Nice", 348085));
    villeList.add(new Ville("Nantes", 323204));


    return villeList;
  }
}
