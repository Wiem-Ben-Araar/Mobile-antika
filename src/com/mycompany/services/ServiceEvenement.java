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
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Evenement;
import com.mycompany.utilies.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nadab
 */
public class ServiceEvenement {
    
     //singleton
    public static ServiceEvenement instance = null ;
      public static boolean resultOk = true;
    //initialiser connection request
    private ConnectionRequest request;
    
    public static ServiceEvenement getInstance(){
        if (instance == null)
            instance = new ServiceEvenement();
        return instance;
    }
    
    public ServiceEvenement() {
        request = new ConnectionRequest();
    }
    
//add
    public void addEvent (TextField nom, TextField lieu, TextField description, TextField capacite, Resources res){
        String url= Statics.BASE_URL+"/mobile/addEvent";
        request.setUrl(url);
        request.setPost(true); // changer la méthode HTTP en POST
        //Response Json
        request.addArgument("nom", nom.getText());
        request.addArgument("lieu", lieu.getText());
        request.addArgument("description", description.getText());
        request.addArgument("capacite", capacite.getText());
          // Control saisi
        if (nom.getText().equals("") || lieu.getText().equals("") || description.getText().equals("")
                || capacite.getText().equals("")) {
            Dialog.show("Erreur", "Veuillez remplir les champs", "OK", null);
            return;
        }

        // ajouter un listener pour la réponse
        request.addResponseListener((e) -> {
            // récupérer la réponse de la requête
            byte[] data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            System.out.println("response data ===> " + responseData);
        });
    
        //execution du requette
              NetworkManager.getInstance().addToQueueAndWait(request);
    }

//show all 
    public ArrayList<Evenement> getEvents(){
    ArrayList<Evenement> result = new ArrayList<>();
    String url= Statics.BASE_URL+"/mobile/displayEvents";
    request.setUrl(url);
    request.addResponseListener((NetworkEvent evt) -> {
          JSONParser jsonParser = new JSONParser();
            try {
                Map<String, Object>mapEvenements = jsonParser.parseJSON(new CharArrayReader(new String(request.getResponseData()).toCharArray()));
                List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapEvenements.get("root");
                
                    for (Map<String, Object> obj : listOfMaps){
                        Evenement evenement = new Evenement();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("nom").toString();
                        String lieu = obj.get("lieu").toString();
                        String description = obj.get("description").toString();
                        float capacite = Float.parseFloat(obj.get("capacite").toString());
                    evenement.setId((int) id);    
                    evenement.setNom(nom);    
                    evenement.setLieu(lieu);
                    evenement.setDescription(description);
                    evenement.setCapacite((int) capacite);
               
                       
                        result.add(evenement);
                    }
            } catch (Exception e) {
                e.printStackTrace();
                }
         });
            NetworkManager.getInstance().addToQueueAndWait(request);
            return result;
    }

//show only one event
    public Evenement getEvent(int id, Evenement evenement){
        String url= Statics.BASE_URL+"/mobile/detailEvent"+id;
        request.setUrl(url);
        String str = new String(request.getResponseData());
        request.addResponseListener((evt) -> {
            JSONParser jsonParser = new JSONParser();
            
            try {
                Map<String, Object>obj = jsonParser.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                evenement.setNom(obj.get("nom").toString());
                evenement.setLieu(obj.get("lieu").toString());
                evenement.setDescription(obj.get("description").toString());
                evenement.setCapacite(Integer.parseInt(obj.get("capacite").toString()));
            } catch (IOException e) {
                System.err.println("Error related to sql:("+e.getMessage()+")");
            }
            System.out.println("Data =="+ str);
        });
        
       NetworkManager.getInstance().addToQueueAndWait(request);
       return evenement;
    }
    
public boolean deleteEvent(int id) {
    String url = Statics.BASE_URL + "/mobile/deleteEvent/" + id;
    request.setUrl(url);
    request.setPost(true);

    final boolean[] resultOk = {false}; // Use an array to store the result as a mutable value

    request.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            int responseCode = evt.getResponseCode();
            if (responseCode == 200) {
                // Successful response
                resultOk[0] = true;
            } else {
                // Handle the error case appropriately
                resultOk[0] = false;
            }
            
            // Remove the response listener
            request.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(request);

    return resultOk[0];
}
    
     public boolean modifierEvent(Evenement evenement) {
        String url = Statics.BASE_URL +"/mobile/updateEvent?id="+evenement.getId()+"&nom="+evenement.getNom()+"&lieu="+evenement.getLieu()+"&description="+evenement.getDescription()+"&capacite"+evenement.getCapacite();
        request.setUrl(url);
        
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = request.getResponseCode() == 200 ;  // Code response Http 200 ok
                request.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(request);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
     public boolean reserverEvent(int id) {
    String url = Statics.BASE_URL + "/mobile/reserver/" + id;
    request.setUrl(url);

    final boolean[] resultOk = {false}; // Use an array to store the result as a mutable value

    request.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            int responseCode = evt.getResponseCode();
            if (responseCode == 200) {
                // Successful response
                resultOk[0] = true;
            } else {
                // Handle the error case appropriately
                resultOk[0] = false;
            }
            
            // Remove the response listener
            request.removeResponseListener(this);
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(request);

    return resultOk[0];
}
    
}