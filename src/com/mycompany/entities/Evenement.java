
package com.mycompany.entities;

/**
 *
 * @author nadab
 */
public class Evenement {
    public int id;
    private String nom, lieu, description;
    private int capacite;
    private String date;

    public Evenement() {
    }
    
    public Evenement(int id, String nom,String lieu, String description, int capacite, String date) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
        this.description = description;
        this.capacite = capacite;
        this.date = date;
    }
    
      public Evenement(String nom, String lieu, String description, String date) {
        this.nom = nom;
        this.lieu= lieu;
        this.description = description;
        this.capacite = capacite;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
      public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
        
}
