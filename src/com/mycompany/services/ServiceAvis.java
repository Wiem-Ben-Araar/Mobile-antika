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
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Avis;
import com.mycompany.utilies.Statics;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author wiemb
 */
public class ServiceAvis {
       //singleton 
    public static ServiceAvis instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceAvis getInstance() {
        if(instance == null )
            instance = new ServiceAvis();
        return instance ;
    }
    
    
    
    public ServiceAvis() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
public void ajoutAvis(Avis avis) {
    String url = Statics.BASE_URL + "/addAvis?user_id=82&commentaire=" + avis.getCommentaire() + "&note=" + avis.getNote();
    req.setUrl(url);
    req.setPost(false);
    req.addResponseListener((e) -> {
        String str = new String(req.getResponseData());
        System.out.println("data == " + str);
    });

    NetworkManager.getInstance().addToQueueAndWait(req);
}

    
      //affichage
    
public ArrayList<Avis> affichageAvis(int user_id)  {
    ArrayList<Avis> result = new ArrayList<>();

    String url = Statics.BASE_URL + "/detailsAvis?user_id=" + user_id;

    req.setUrl(url);

    req.addResponseListener((NetworkEvent evt) -> {
        JSONParser jsonp = new JSONParser();

        try {
            Map<String,Object> mapAvis = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
            List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapAvis.get("root");

            for(Map<String, Object> obj : listOfMaps) {
                Avis avis = new Avis();

                float id = Float.parseFloat(obj.get("id").toString());
                String commentaire = obj.get("commentaire").toString();
                float note = Float.parseFloat(obj.get("note").toString());

                avis.setId((int) id);
                avis.setCommentaire(commentaire);
                avis.setNote((int) note);
                avis.setUser_id(user_id); //yomkn ghalta 

                
                String dateConverter = obj.get("createdAt").toString().substring(obj.get("createdAt").toString().indexOf("timestamp") + 10 , obj.get("createdAt").toString().lastIndexOf("}"));
                Date currentTime = new Date(Double.valueOf(dateConverter).longValue() * 1000);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(currentTime);
                avis.setCreatedAt(dateString);
                
               
                
                result.add(avis);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    });
    
    NetworkManager.getInstance().addToQueueAndWait(req);

    return result;
}

}
