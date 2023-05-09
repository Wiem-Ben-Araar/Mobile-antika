/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Evenement;
import com.mycompany.services.ServiceEvenement;

/**
 *
 * @author nadab
 */
public class UpdateEventForm extends Form {
    private Evenement evenement;
    private TextField nomField, lieuField, descriptionField, capaciteField;

    public UpdateEventForm(Resources res, Evenement evenement) {
        super("Update Event");
        this.evenement = evenement;
        nomField = new TextField(evenement.getNom(), "Nom");
        lieuField = new TextField(evenement.getLieu(), "Lieu");
        descriptionField = new TextField(evenement.getDescription(), "Description");
        capaciteField = new TextField(Integer.toString(evenement.getCapacite()), "Capacite", 4, TextField.NUMERIC);
        addAll(nomField, lieuField, descriptionField, capaciteField, new Button("Update", res.getImage("update.png")), new Button("Cancel"));
        getToolbar().setBackCommand("", e -> new ListEventForm(res).show());
        getToolbar().addCommandToRightBar("", res.getImage("logout.png"), e -> new NewsfeedForm(res).show());
        Command cancelCommand = new Command("Cancel");
        ((Button) getToolbar().findCommandComponent(cancelCommand)).addActionListener(e -> new ListEventForm(res).show());

        Command updateCommand = new Command("Update");
        ((Button) getToolbar().findCommandComponent(updateCommand)).addActionListener(e -> new ListEventForm(res).show());
    }

    private void updateEvent() {
        evenement.setNom(nomField.getText());
        evenement.setLieu(lieuField.getText());
        evenement.setDescription(descriptionField.getText());
        evenement.setCapacite(Integer.parseInt(capaciteField.getText()));
        if (ServiceEvenement.getInstance().modifierEvent(evenement)) {
            new ListEventForm(getResourceObject()).show();
        } else {
            Dialog.show("Error", "An error occurred while updating the event. Please try again.", "OK", null);
        }
    }

    private Resources getResourceObject() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

