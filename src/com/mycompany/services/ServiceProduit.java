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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rihem
 */
public class ServiceProduit {
     public ArrayList<Produit> lists;
    public static ServiceProduit instance ; 
    public boolean resultOK;
    private  ConnectionRequest req; 
 public static final String BASE_URL="http://127.0.0.1:8000/produits/api";
 private ServiceProduit() {
        req = new ConnectionRequest() ; 
         }
    
    public static ServiceProduit getInstance() {
        if (instance == null)
        {
            instance = new ServiceProduit();
        }
         return instance;
    }
 
 public ArrayList<Produit> parseEntitie(String jsonText){
        try {
            lists= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ProduitListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) ProduitListJson.get("root");
           for ( Map<String,Object> obj: list){
             Produit p = new Produit();
             float id = Float.parseFloat(obj.get("id").toString());
               float prix = Float.parseFloat(obj.get("prix").toString());
             p.setId((int)id);
           p.setNom(obj.get("nom").toString());
             p.setImage(obj.get("image").toString());
             p.setGenre(obj.get("genre").toString());
             p.setPrix(prix);
             lists.add(p);
         
        } }
           catch (IOException ex) {
        }
          return lists;
 }
     public ArrayList<Produit> getAll(){
          String url = BASE_URL+"/produitsJson";
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
          public ArrayList<Produit> getByName(String search){
          String url = BASE_URL+"/produitsSearchJson?search="+search;
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
  
  
        public void Supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(BASE_URL+"/deleteProdJson/"+id);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
       public boolean add (TextField tfNom,TextField tfPrix,TextField tfGenre)
    { 

       String url = BASE_URL+"/addProduitJson?nom="+tfNom.getText()+"&prix="+tfPrix.getText()+"&genre="+tfGenre.getText();
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
           public boolean edit (TextField tfNom,TextField tfPrix,TextField tfGenre,int id)
    { 

       String url = BASE_URL+"/editProduitJson/"+id+"?nom="+tfNom.getText()+"&prix="+tfPrix.getText()+"&genre="+tfGenre.getText();
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
