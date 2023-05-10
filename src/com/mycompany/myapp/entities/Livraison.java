/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Wael
 */
public class Livraison {
    private int id;
    private String adresse;
    private String date_livraison;
    private String statut;
    private double total;
    private User user;

    @Override
    public String toString() {
        return "Livraison{" + "id=" + id + ", adresse=" + adresse + ", date_livraison=" + date_livraison + ", statut=" + statut + ", total=" + total + ", user=" + user + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setDate_livraison(String date_livraison) {
        this.date_livraison = date_livraison;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getDate_livraison() {
        return date_livraison;
    }

    public String getStatut() {
        return statut;
    }

    public double getTotal() {
        return total;
    }

    public User getUser() {
        return user;
    }

    public Livraison(int id, String adresse, String date_livraison, String statut, double total, User user) {
        this.id = id;
        this.adresse = adresse;
        this.date_livraison = date_livraison;
        this.statut = statut;
        this.total = total;
        this.user = user;
    }

    public Livraison() {
    }
    
}
