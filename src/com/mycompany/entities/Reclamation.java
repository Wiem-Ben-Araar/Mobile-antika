/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author rihem
 */
public class Reclamation {
  private int id;
  private String titre;
   private String description;
   
   private String date;
   private String state;
      private String reponse;
      private Produit produit;

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", date=" + date + ", state=" + state + ", reponse=" + reponse + ", produit=" + produit + '}';
    }

    public Reclamation(int id, String titre, String description, String date, String state, String reponse, Produit produit) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date = date;
        this.state = state;
        this.reponse = reponse;
        this.produit = produit;
    }

    public Reclamation() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getState() {
        return state;
    }

    public String getReponse() {
        return reponse;
    }

    public Produit getProduit() {
        return produit;
    }
}
