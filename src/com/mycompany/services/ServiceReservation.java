
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
import com.mycompany.entities.Reservation;
import com.mycompany.entities.User;
import static com.mycompany.services.ServiceEvenement.resultOk;
import com.mycompany.utilies.Statics;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author nadab
 */
public class ServiceReservation {
    
      public static ServiceReservation  instance = null ;
      public static boolean resultOk = true;
    //initialiser connection request
    private ConnectionRequest request;
    
    public static ServiceReservation getInstance(){
        if (instance == null)
            instance = new ServiceReservation();
        return instance;
    }
    
    public ServiceReservation() {
        request = new ConnectionRequest();
    }
    
//add
    public void addRes (Evenement evenement, User user, Resources res){
        String url= Statics.BASE_URL+"/api/addRes";
        request.setUrl(url);
        request.setPost(true); // changer la méthode HTTP en POST
               
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
    
    public Reservation getEvent(int id, Reservation reservation){
        String url= Statics.BASE_URL+"/api/detailRes"+id;
        request.setUrl(url);
        String str = new String(request.getResponseData());
        request.addResponseListener((evt) -> {
            JSONParser jsonParser = new JSONParser();
            
            try {
                Map<String, Object>obj = jsonParser.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                reservation.setEvenement((Evenement) obj.get("evenement"));
                reservation.setUser((User) obj.get("user"));
                } catch (IOException e) {
                System.err.println("Error related to sql:("+e.getMessage()+")");
            }
            System.out.println("Data =="+ str);
        });
        
       NetworkManager.getInstance().addToQueueAndWait(request);
       return reservation;
    }
    
    public boolean deleteRes(int id ) {
        String url = Statics.BASE_URL +"/api/deleteRes?id="+id;
         request.setUrl(url);
         request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
             request.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(request);
        return  resultOk;
    }

    
}
