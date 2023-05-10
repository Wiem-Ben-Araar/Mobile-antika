/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.gui.AjoutAvisForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.WalkthruForm;
import com.mycompany.utilies.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceUser {

    //singleton 
    public static ServiceUser instance = null;
    public static boolean resultOK = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public ServiceUser() {
        req = new ConnectionRequest();
    }

    // Signup
    public void signup(TextField nom, TextField password, TextField email, TextField confirmPassword, TextField prenom,
            TextField telephone, TextField adresse, ComboBox<String> roles, Resources res) {
        String url = Statics.BASE_URL + "/user/signup";
        req.setUrl(url);
        req.setPost(true); // changer la méthode HTTP en POST

        // ajouter les paramètres au corps de la requête
        req.addArgument("email", email.getText());
        req.addArgument("password", password.getText());
        req.addArgument("nom", nom.getText());
        req.addArgument("prenom", prenom.getText());
        req.addArgument("telephone", telephone.getText());
        req.addArgument("adresse", adresse.getText());
        req.addArgument("roles", roles.getSelectedItem());

        // Control saisi
        if (nom.getText().equals("") || password.getText().equals("") || email.getText().equals("")
                || confirmPassword.getText().equals("") || prenom.getText().equals("") || adresse.getText().equals("")
                || telephone.getText().equals("")) {
            Dialog.show("Erreur", "Veuillez remplir les champs", "OK", null);
            return;
        }

        // ajouter un listener pour la réponse
        req.addResponseListener((e) -> {
            // récupérer la réponse de la requête
            byte[] data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            System.out.println("response data ===> " + responseData);
        });

        // ajouter la requête à la queue de NetworkManager pour l'exécuter
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    //SignIn
    public void signin(TextField email, TextField password, Resources res) {

        String url = Statics.BASE_URL + "/user/signin?email=" + email.getText().toString() + "&password=" + password.getText().toString();

        req = new ConnectionRequest(url, false);
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    System.out.println("data ==" + json);

                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    if (user.containsKey("id")) {
                        //Session 
                        float id = Float.parseFloat(user.get("id").toString());
                        SessionManager.setId((int) id);

                        SessionManager.setPassowrd(user.get("password").toString());

                        SessionManager.setEmail(user.get("email").toString());
                        SessionManager.setNom(user.get("nom").toString());
                        SessionManager.setPrenom(user.get("prenom").toString());
                        SessionManager.setTelephone(user.get("telephone").toString());
                        SessionManager.setAdresse(user.get("adresse").toString());

                        // redirect to home page
                        new AjoutAvisForm(res).show();
                    } else {
                        Dialog.show("Erreur", "Compte inexistant", "OK", null);
                        
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }


    // edit user

    public static void editUser(String email, String password, String nom, String prenom, String adresse, String telephone) {
        String url = Statics.BASE_URL + "/user/editUser";

        ConnectionRequest req = new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
        req.addArgument("id", String.valueOf(SessionManager.getId()));
        req.addArgument("email", email);
        req.addArgument("password", password);
        req.addArgument("nom", nom);
        req.addArgument("prenom", prenom);
        req.addArgument("adresse", adresse);
        req.addArgument("telephone", telephone);

        req.addResponseListener((response) -> {
            byte[] data = ((byte[]) response.getMetaData());
            String s = new String(data);
            System.out.println(s.equals("success"));
            if (s.equals("success")) {
                // do something

            } else {
                Dialog.show("Erreur", "Echec de modification", "ok", null);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    
    public String getPasswordByEmail(String email,Resources rs){
        
         String url = Statics.BASE_URL + "/user/getPasswordByEmail?email=" + email ;

        req = new ConnectionRequest(url, false); //false yaaneha url mezelt matbaathtch l server
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

           json= new String(req.getResponseData()) + "";

            try {

               
                    System.out.println("data ==" + json);

                    Map<String, Object> password = j.parseJSON(new CharArrayReader(json.toCharArray()));

                 
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
       return json;
    }
    }

