/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author wiemb
 */
public class SessionManager {

    public static Preferences pref; // 3ibara memoire sghira nsajlo fiha data 

    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id;

    private static String email;
    private static String passowrd;
    private static String nom;
    private static String prenom;
    private static String adresse;
    private static String telephone;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id", id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id", id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getEmail() {
        return pref.get("email", email);
    }

    public static void setEmail(String email) {
        pref.set("email", email);
    }

    public static String getPassowrd() {
        return pref.get("passowrd", passowrd);
    }

    public static void setPassowrd(String passowrd) {
        pref.set("passowrd", passowrd);
    }

    public static void setNom(String nom) {
        pref.set("nom", nom);

    }

    public static String getNom() {
        return pref.get("nom", nom);
    }

    public static String getPrenom() {
        return pref.get("prenom", prenom);
    }

    public static String getAdresse() {
        return pref.get("adresse", adresse);
    }

    public static String getTelephone() {
        return pref.get("telephone", telephone);
    }

    public static void setPrenom(String prenom) {
        pref.set("prenom", prenom);

    }

    public static void setAdresse(String adresse) {
        pref.set("adresse", adresse);

    }

    public static void setTelephone(String telephone) {
        pref.set("telephone", telephone);

    }
}
