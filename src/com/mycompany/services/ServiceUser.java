/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.utilies.Statics;
import java.util.Map;

/**
 *
 * @author wiemb
 */
public class ServiceUser {
     //singleton 
    public static ServiceUser instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUser getInstance() {
        if(instance == null )
            instance = new ServiceUser();
        return instance ;
    }
    
    
    
    public ServiceUser() {
        req = new ConnectionRequest();
        
    }
    
    //Signup
  public void signup(TextField nom, TextField password, TextField email, TextField confirmPassword, TextField prenom, TextField telephone, TextField adresse, Resources res) {
    String url = Statics.BASE_URL+"/user/signup?email="+email.getText().toString()+
                "&password="+password.getText().toString()+"&nom="+nom.getText().toString()+"&prenom="+prenom.getText().toString()+"&telephone="+telephone.getText().toString()+"&adresse="+adresse.getText().toString();
        req.setUrl(url);
       
        //Control saisi
        if(nom.getText().equals(" ") && password.getText().equals(" ") && email.getText().equals(" ")&& confirmPassword.getText().equals(" ")&& prenom.getText().equals(" ")&& adresse.getText().equals(" ")&& telephone.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    
    

    
}
