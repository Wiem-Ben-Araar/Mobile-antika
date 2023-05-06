/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkManager;
import com.mycompany.entities.Avis;
import com.mycompany.utilies.Statics;

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
        
        String url = Statics.BASE_URL + "/addAvis?commentaire=" + avis.getCommentaire() + "&note=" + avis.getNote(); //ghalet lakthareya 
        
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
}
