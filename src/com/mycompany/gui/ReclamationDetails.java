/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Form;
import com.mycompany.entities.Reclamation;

import com.codename1.ui.util.Resources;
import java.io.IOException;
/**
<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
/**
 *
 * @author rihem
 */
public class ReclamationDetails extends BaseFormBack {

    public ReclamationDetails(Form previous,Resources res,Reclamation r) throws IOException {
//          super("reponse", BoxLayout.y());
//        Toolbar tb = new Toolbar(true);
//        setToolbar(tb);
//        getTitleArea().setUIID("Container");
//        setTitle("Expositions");
//        getContentPane().setScrollVisible(false);
//        
//        super.addSideMenu(res);
//        //tb.addSearchCommand(e -> {});
//                Tabs swipe = new Tabs();
//                        Label spacer1 = new Label();
//        Label spacer2 = new Label();
//
//        addTab(swipe, spacer1);
//          swipe.setUIID("Container");
//        swipe.getContentPane().setUIID("Container");
//        swipe.hideTabs();
//        ButtonGroup bg = new ButtonGroup();
//        int size = Display.getInstance().convertToPixels(1);
//        Image unselectedWalkthru = Image.createImage(size, size, 0);
//        Graphics g = unselectedWalkthru.getGraphics();
//        g.setColor(0xffffff);
//        g.setAlpha(100);
//        g.setAntiAliased(true);
//        g.fillArc(0, 0, size, size, 0, 360);
//        Image selectedWalkthru = Image.createImage(size, size, 0);
//        g = selectedWalkthru.getGraphics();
//        g.setColor(0xffffff);
//        g.setAntiAliased(true);
//        g.fillArc(0, 0, size, size, 0, 360);
//        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
//        FlowLayout flow = new FlowLayout(CENTER);
//        flow.setValign(BOTTOM);
//        Container radioContainer = new Container(flow);
//        for(int iter = 0 ; iter < rbs.length ; iter++) {
//            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
//            rbs[iter].setPressedIcon(selectedWalkthru);
//            rbs[iter].setUIID("Label");
//            radioContainer.add(rbs[iter]);
//        }
//                
//        rbs[0].setSelected(true);
//        swipe.addSelectionListener((i, ii) -> {
//            if(!rbs[ii].isSelected()) {
//                rbs[ii].setSelected(true);
//            }
//        });
//        
//        Component.setSameSize(radioContainer, spacer1, spacer2);
//        add(LayeredLayout.encloseIn(swipe, radioContainer));
//        
//          swipe.setUIID("Container");
//        swipe.getContentPane().setUIID("Container");
//        swipe.hideTabs();
//              setTitle("Reclamation Details");
//        setLayout(BoxLayout.yCenter());
//          ArrayList<Produit> list;
//        list = new ArrayList<>();
//        list = ServiceProduit.getInstance().getReponsesByRecId(r.getId_reclamation());
// Label spReclamation = new Label("Reclamationinfo: "); 
//         spReclamation.getAllStyles().setFgColor(0x00000);
//            Label reclamationId = new Label("Reclamation Id: " + "  " + r.getId_reclamation()); 
//         reclamationId.getAllStyles().setFgColor(0x00000);
//                Label descriptionRec = new Label("Reclamation description : " + "  " + r.getDescription()); 
//         descriptionRec.getAllStyles().setFgColor(0x00000);
//         Label reclamationType = new Label("Reclamation type: " + "  " + r.getType_reclamation());
//         reclamationType.getAllStyles().setFgColor(0x00000);
//  
// Label spDate = new Label("Reclamation Date: " + "  " + r.getDate());
//    spDate.getAllStyles().setFgColor(0x00000);      
//    addAll(spReclamation,reclamationId,descriptionRec,reclamationType);
//        for ( Produit p : list) {
//            
//              
//                  Label i = new Label();
//                  
//        
//         Label spl = new Label("ID Reponse: " + "  " + p.getIdReponse()); 
//         spl.getAllStyles().setFgColor(0x00000);
//         Label spl2 = new Label("message Reponse: " + "  " + p.getReponse()); 
//         spl2.getAllStyles().setFgColor(0x00000);
//        
//          Label spl7 = new Label("Date Reponse: " + "  " + p.getDater());
//          spl7.getAllStyles().setFgColor(0x00000);
//   
//
//         Button sup = new Button("Delete");
//                  Button upd = new Button("Update");
//
//             
//                sup.addActionListener((evt) -> {
//                   ServiceProduit.getInstance().Supprimer(p.getIdReponse());
//                    System.out.println("Reponse deleted successfully");
//                    Dialog.show("Alert", "Delete Reponse ?", new Command("OK"));
//                    Dialog.show("Success", "Reponse deleted successfully", new Command("OK"));
//                     Message m = new Message("Reponse has been deleted successfully !");
//                        Display.getInstance().sendMessage(new String[] {"rihem.briki@esprit.tn"}, "Confirmation", m);
//                    });
//                 upd.addActionListener((evt) -> {
//                        new EditReponseForm(this.getComponentForm(),res,p.getIdReponse()).show();
//                    });
//        addAll(spl,spl2,spl7,sup,upd)
//                ;}
//        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
//    }
//    
//    private void addTab(Tabs swipe,  Label spacer) {
//        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
//        
//        Label overlay = new Label(" ", "ImageOverlay");
//        
//        Container page1 = 
//            LayeredLayout.encloseIn(
//               
//                overlay,
//                BorderLayout.south(
//                    BoxLayout.encloseY(
//                           
//                            spacer
//                        )
//                )
//            );
//
//        swipe.addTab("", page1);
    }
}

