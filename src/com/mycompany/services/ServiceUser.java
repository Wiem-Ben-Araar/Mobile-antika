/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.User;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.WalkthruForm;
import com.mycompany.utilies.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceUser {
    //singleton 
    public static ServiceUser instance = null;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceUser getInstance() {
        if (instance == null)
            instance = new ServiceUser();
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

        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    System.out.println("data ==" + json);

                    Map<String, Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    //Session 
                    float id = Float.parseFloat(user.get("id").toString());
                    SessionManager.setId((int) id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i

                    SessionManager.setPassowrd(user.get("password").toString());

                    SessionManager.setEmail(user.get("email").toString());

                    // redirect to home page
                    new WalkthruForm (res).show();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);

    }
    //affichage all users 
        public ArrayList<User> afficherAllClient() {
        ArrayList<User> result = new ArrayList<>();
        req.setUrl(Statics.BASE_URL + "user/liste");
        req.addResponseListener(new ActionListener<NetworkEvent>() {

            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapUser = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    List<Map<String, Object>> listOfMaps = (List<Map<String, Object>>) mapUser.get("root");
                    for (Map<String, Object> obj : listOfMaps) {
                        System.out.println("users : " + obj);
                        User userL = new User();

                        float id = Float.parseFloat(obj.get("id").toString());
                        userL.setNom(obj.get("nom").toString());
                        userL.setPrenom(obj.get("prenom").toString());
                        userL.setEmail(obj.get("email").toString());
                        userL.setAdresse(obj.get("adresse").toString());
                        userL.setId((int) id);

                        result.add(userL);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
}
