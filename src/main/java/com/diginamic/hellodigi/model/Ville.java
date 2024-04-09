package com.diginamic.hellodigi.model;

public class Ville {
  private int id;
  private String nom;
  private int nbHabitants;

  public Ville(int id, String nom, int nbHabitants) {
    this.id = id;
    this.nom = nom;
    this.nbHabitants = nbHabitants;
  }

  public int getId() {
    return id;
  }

  public Ville setId(int id) {
    this.id = id;
    return this;
  }

  public String getNom() {
    return nom;
  }

  public int getNbHabitants() {
    return nbHabitants;
  }
}
