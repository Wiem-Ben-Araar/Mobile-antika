/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Livraison;
import com.mycompany.entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Wael
 */
public class ServiceLivraison {
         public ArrayList<Livraison> lists;
    public static ServiceLivraison instance ; 
    public boolean resultOK;
    private  ConnectionRequest req; 
 public static final String BASE_URL="http://127.0.0.1:8000/livraison/api";
 private ServiceLivraison() {
        req = new ConnectionRequest() ; 
         }
    
    public static ServiceLivraison getInstance() {
        if (instance == null)
        {
            instance = new ServiceLivraison();
        }
         return instance;
    }
 
 public ArrayList<Livraison> parseEntitie(String jsonText){
        try {
            lists= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ProduitListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) ProduitListJson.get("root");
           for ( Map<String,Object> obj: list){
             Livraison p = new Livraison();
                                  Map<String,Object> userObj = (Map<String,Object>) obj.get("user");

             float idUser = Float.parseFloat(userObj.get("id").toString());
             User u =new User();
             u.setId((int)idUser);
             float id = Float.parseFloat(obj.get("id").toString());
               float total = Float.parseFloat(obj.get("total").toString());
             p.setId((int)id);
           p.setStatut(obj.get("statut").toString());
             p.setAdresse(obj.get("adresse").toString());
             p.setDate_livraison(obj.get("date_livraison").toString());
             p.setTotal(total);
             p.setUser(u);
             lists.add(p);
         
        } }
           catch (IOException ex) {
        }
          return lists;
 }
     public ArrayList<Livraison> getAll(){
          String url = BASE_URL+"/livraisonsJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                lists = parseEntitie(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return lists;
    }

                public void Valider(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(BASE_URL+"/validerLivraisonJson/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
                        public void annuler(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(BASE_URL+"/annulerLivraisonJson/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
       public boolean add (TextField tfAdresse,int idUser)
    { 

       String url = BASE_URL+"/addLivraisonJson?adresse="+tfAdresse.getText()+"&idUser="+idUser;
       req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){ 
           @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
             }
    });
       NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }
       
            public boolean edit (TextField tfAdresse,int idLivraison)
    { 

       String url = BASE_URL+"/editLivraisonJson/"+idLivraison+"?adresse="+tfAdresse.getText();
       req.setUrl(url);
       req.addResponseListener(new ActionListener<NetworkEvent>(){ 
           @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
             }
    });
       NetworkManager.getInstance().addToQueue(req);
        return resultOK;
    }
      
}
