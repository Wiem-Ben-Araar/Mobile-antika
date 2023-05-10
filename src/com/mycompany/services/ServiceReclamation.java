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
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Produit;
import com.mycompany.entities.Reclamation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author rihem
 */
public class ServiceReclamation {
    public ArrayList<Reclamation> reclamations;
    public static ServiceReclamation instance ; 
    public boolean resultOK;
    private  ConnectionRequest req; 
 public static final String BASE_URL="http://127.0.0.1:8000/reclamation/api";
 
 
 private ServiceReclamation() {
        req = new ConnectionRequest() ; 
         }
    /* Singleton patron de conception de creation */
    public static ServiceReclamation getInstance() {
        if (instance == null)
        {
            instance = new ServiceReclamation();
        }
         return instance;
    }
 
 public ArrayList<Reclamation> parseReclamations(String jsonText){
        try {
            reclamations= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> CategorieListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) CategorieListJson.get("root");
           for ( Map<String,Object> obj: list){
                                    

             Reclamation c = new Reclamation();
             float id = Float.parseFloat(obj.get("id").toString());
              
              //float id_user = Float.parseFloat(obj.get("id_user").toString());
             
             c.setId((int)id);
             
             c.setTitre(obj.get("Titre").toString());
             c.setDate(obj.get("Date").toString());
             
             c.setState(obj.get("State").toString());
             c.setReponse(obj.get("Response").toString());
             c.setDescription(obj.get("Description").toString());
             /*Produit */
               Produit p = new Produit();
              Map<String,Object> prodObj = (Map<String,Object>) obj.get("Produit");
              float idProduit = Float.parseFloat(prodObj.get("id").toString());
              float prix = Float.parseFloat(prodObj.get("prix").toString());
                           p.setImage(prodObj.get("image").toString());
                             p.setGenre(prodObj.get("genre").toString());

                          p.setNom(prodObj.get("nom").toString());
 p.setId((int)idProduit);
 p.setPrix(prix);
 c.setProduit(p);
             reclamations.add(c);
         
        } }
           catch (IOException ex) {
//            Logger.getLogger(ServiceOeuvre.class.getName()).log(Level.SEVERE, null, ex);
             
        }
          return reclamations;
 }
     public ArrayList<Reclamation> getAllReclamations(){
          String url = BASE_URL+"/reclamationsJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reclamations = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return reclamations;
    }
        public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(BASE_URL+"/deleteReclamation/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
       public boolean addReclamation (TextField tfDescription,TextField tfTitre,TextField tfState,TextField tfReponse,int idProd)
    { 

       String url = BASE_URL+"/addReclamationApi?description="+tfDescription.getText()+"&titre="+tfTitre.getText()+"&state="+tfState.getText()+"&reponse="+tfReponse.getText()+"&idProduit="+idProd;
       req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){ 
           @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
             }
    });
        System.out.println(""+resultOK);
       NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }
           public boolean updateReclamation (TextField titre,TextField description,TextField reponse ,int id)
    { 

       String url = BASE_URL+"/editReclamationApi/"+id+"?description="+description.getText()+"&titre="+description.getText()+"&reponse="+reponse.getText();
       req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){ 
           @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
             }
    });
        System.out.println(""+resultOK);
       NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }

}

