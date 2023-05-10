/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Evenement;
import com.mycompany.services.ServiceEvenement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nadab
 */
public class ListEventForm extends BaseForm{
        Form current;
         public ListEventForm(Resources res ) {
        super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("List of events");
        getContentPane().setScrollVisible(false);
        
        
        tb.addSearchCommand(e ->  {
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1 = new Label();
        Label s2 = new Label();
        

        
      swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

     

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton myLists = RadioButton.createToggle("My Events", barGroup);
        myLists.setUIID("SelectBar");
        RadioButton list = RadioButton.createToggle("Others", barGroup);
        list.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Add your event", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        myLists.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        
          //  a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, myLists, list, partage),
                FlowLayout.encloseBottom(arrow)
        ));

       
        
              //Appel affichage methode
        ArrayList<Evenement> listEvent = ServiceEvenement.getInstance().getEvents();

            for (Evenement evenement : listEvent) {
            Container eventContainer = new Container(BoxLayout.y());
                    eventContainer.addAll(new Label("Nom: " + evenement.getNom(), "NewsTopLine"));
                    eventContainer.addAll(new Label("Lieu: " + evenement.getLieu(), "NewsTopLine"));
                    eventContainer.addAll(new Label("Description: " + evenement.getDescription(), "NewsTopLine"));
                    eventContainer.addAll(new Label("Capacite: " + evenement.getCapacite(), "NewsTopLine"));
                addButton(evenement, eventContainer);
                deleteButton(evenement, eventContainer);
                reserverButton(evenement, eventContainer);
                add(eventContainer);
                
            }
         }
    
    private void addButton(Evenement evenement, Container eventContainer) {
        
        Button button = new Button("View Details");
        button.addActionListener(e -> {
             Dialog.show("Evenement", "Nom: " + evenement.getNom() + "\nLieu: " + evenement.getLieu() 
                + "\nDescription: " + evenement.getDescription() + "\nCapacite: " + evenement.getCapacite(), "OK", "Cancel");
                System.out.println(evenement.getId()
);

        });
        eventContainer.add(button);
    }
        
       private void deleteButton(Evenement evenement, Container eventContainer) {   
         
        Button deleteButton = new Button("Delete");
        deleteButton.addActionListener(evt -> {
        if (Dialog.show("Confirmation", "Are you sure you want to delete?", "OK", "Cancel")) {
            // Delete object from database
            if (evenement instanceof Evenement) {
                ServiceEvenement.getInstance().deleteEvent(evenement.getId());
            } else {
                System.out.println("erreur");
            }
            // Remove object from list and container
            
            eventContainer.remove();
            eventContainer.revalidate();
            eventContainer.animateLayout(150);
        }
    });
    eventContainer.add(deleteButton);
}
       private void reserverButton(Evenement evenement, Container eventContainer) {   
         
        Button reserverButton = new Button("Book");
        reserverButton.addActionListener(evt -> {
        if (Dialog.show("Confirmation", "Your reservation has been added.", "OK", "")) {
            // Delete object from database
            if (evenement instanceof Evenement) {
                ServiceEvenement.getInstance().reserverEvent(evenement.getId());
            } else {
                System.out.println("erreur");
            }
            // Remove object from list and container
            
            eventContainer.remove();
            eventContainer.revalidate();
            eventContainer.animateLayout(150);
        }
    });
    eventContainer.add(reserverButton);
}

}


    