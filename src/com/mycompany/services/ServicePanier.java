package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Panier;
import com.mycompany.entities.Produit;
import com.mycompany.entities.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Wael
 */
public class ServicePanier {
       public ArrayList<Panier> lists;
    public static ServicePanier instance ; 
    public boolean resultOK;
    private  ConnectionRequest req; 
 public static final String BASE_URL="http://127.0.0.1:8000/panier/api";
 private ServicePanier() {
        req = new ConnectionRequest() ; 
         }
    
    public static ServicePanier getInstance() {
        if (instance == null)
        {
            instance = new ServicePanier();
        }
         return instance;
    }
 
 public ArrayList<Panier> parseEntitie(String jsonText){
        try {
            lists= new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> ProduitListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
           List< Map<String,Object>> list =(List< Map<String,Object>>) ProduitListJson.get("root");
           for ( Map<String,Object> obj: list){
               Produit prod=new Produit();
                               Map<String,Object> userObj = (Map<String,Object>) obj.get("user");
                Map<String,Object> produitObj = (Map<String,Object>) obj.get("produit");
             float idProduit = Float.parseFloat(produitObj.get("id").toString());
               float prix = Float.parseFloat(produitObj.get("prix").toString());
             prod.setId((int)idProduit);
           prod.setNom(produitObj.get("nom").toString());
             prod.setImage(produitObj.get("image").toString());
             prod.setGenre(produitObj.get("genre").toString());
             prod.setPrix(prix);
             Panier p = new Panier();
             float id = Float.parseFloat(obj.get("id").toString());
               float quantite = Float.parseFloat(obj.get("quantite").toString());
                float total = Float.parseFloat(obj.get("total").toString());
                User u = new User();
                
                float userId=Float.parseFloat(userObj.get("id").toString());
                u.setId((int)userId);
             p.setId((int)id);
           p.setProduit(prod);
             p.setUser(u);
             p.setQuantite((int)quantite);
             p.setTotal(total);
             lists.add(p);
         
        } }
           catch (IOException ex) {
        }
          return lists;
 }
     public ArrayList<Panier> getAll(int idUser){
          String url = BASE_URL+"/panierJson?idUser="+idUser;
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
  
        public void deleteProdFromCart(int idProduit,int idUser) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(BASE_URL+"/deleteProdFromCart/"+idProduit+"?idUser="+idUser);
        con.setPost(false);
        con.addResponseListener((evt) -> {
            System.out.println(con.getResponseData());

        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
       public boolean add (int idProduit , int idUser)
    { 

       String url = BASE_URL+"/addToCart?idProduit="+idProduit+"&idUser="+idUser;
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
