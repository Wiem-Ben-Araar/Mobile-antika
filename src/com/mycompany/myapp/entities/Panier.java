/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Wael
 */
public class Panier {
    private int id;
    private int quantite;
    private double total;
    private Produit produit;
    private User user;

    @Override
    public String toString() {
        return "Panier{" + "id=" + id + ", quantite=" + quantite + ", total=" + total + ", produit=" + produit + ", user=" + user + '}';
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public int getQuantite() {
        return quantite;
    }

    public double getTotal() {
        return total;
    }

    public Produit getProduit() {
        return produit;
    }

    public User getUser() {
        return user;
    }

    public Panier(int id, int quantite, double total, Produit produit, User user) {
        this.id = id;
        this.quantite = quantite;
        this.total = total;
        this.produit = produit;
        this.user = user;
    }

    public Panier() {
    }
    
}
