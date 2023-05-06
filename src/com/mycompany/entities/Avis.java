/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author wiemb
 */
public class Avis {
    private int id;
    private String commentaire;
    private int note;
    private String createdAt;

    public Avis() {
    }

    public Avis(int id, String commentaire, int note, String createdAt) {
        this.id = id;
        this.commentaire = commentaire;
        this.note = note;
        this.createdAt = createdAt;
    }

    public Avis(String commentaire, int note, String createdAt) {
        this.commentaire = commentaire;
        this.note = note;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", commentaire=" + commentaire + ", note=" + note + ", createdAt=" + createdAt + '}';
    }

    
    
}
