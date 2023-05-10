/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author rihem
 */
public class Produit {
    private int id;
    private String nom;
        private String image;
        
        private String genre;

    private double prix;

    public Produit(int id, String nom, String image, String genre, double prix) {
        this.id = id;
        this.nom = nom;
        this.image = image;
        this.genre = genre;
        this.prix = prix;
    }

    public Produit() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getImage() {
        return image;
    }

    public String getGenre() {
        return genre;
    }

    public double getPrix() {
        return prix;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", image=" + image + ", genre=" + genre + ", prix=" + prix + '}';
    }
    
    
}
