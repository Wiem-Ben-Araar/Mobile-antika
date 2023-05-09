/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

/**
 *
 * @author nadab
 */
public class Reservation {
    
    private int id;
    public Evenement evenement;
    private User user;
    
        public Reservation(){

        } 
        
        public Reservation(int id, Evenement evenement, User user){

            this.id=id;
            this.evenement=evenement;
            this.user=user;
        }
        
         public Reservation( Evenement evenement, User user){

            this.evenement=evenement;
            this.user=user;
        }
          public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
     public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }
    
     public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
