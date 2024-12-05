package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauConfigServeur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2024-11-01
 */
public class EcouteurMenuPrincipal implements ActionListener {
    private Client client;
    private JFrame fenetre;

    public EcouteurMenuPrincipal(Client client, JFrame fenetre) {
        this.client = client;
        this.fenetre = fenetre;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        Object source = evt.getSource();
        String action;
        String alias;
        int res;

        if (source instanceof JMenuItem) {
            action = ((JMenuItem)source).getActionCommand();
            switch (action) {
                case "CONNECTER":
                    if (!client.isConnecte()) {
                        if (!client.connecter()) {
                            JOptionPane.showMessageDialog(fenetre, "Le serveur ne répond pas");
                            break;
                        }
                    }
                    break;
                case "DECONNECTER":
                    if (!client.isConnecte())
                        break;
                    res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                            "Confirmation Déconnecter",
                            JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                    if (res == JOptionPane.OK_OPTION){
                        client.deconnecter();
                    }
                    break;

                case "CONFIGURER":
                     if (!client.isConnecte()) {
                         boolean config = true;

                         PanneauConfigServeur panneau = new PanneauConfigServeur(client.getAdrServeur(), client.getPortServeur());

                         while (config) {
                             try {
                                 fenetre.add(panneau);
                                 res = JOptionPane.showConfirmDialog(fenetre, panneau,
                                         "Configuration serveur", JOptionPane.OK_CANCEL_OPTION,
                                         JOptionPane.PLAIN_MESSAGE);
                                 if (res == JOptionPane.OK_OPTION) {
                                     //SI L'UTILISATEUR MARQUE DE NOUVELLES VALEURS
                                     String newAdr = panneau.getAdresseServeur();
                                     String portTexte = panneau.getPortServeur();

                                     // MAJ DES VALEURS (AVEC LES NOUVELLES VALEURS)
                                     int newPort = Integer.parseInt(portTexte);
                                     client.setAdrServeur(newAdr);
                                     client.setPortServeur(newPort);

                                     config = false;
                                 } else {
                                     config = false;
                                 }
                             } catch (NumberFormatException e) {
                                 JOptionPane.showMessageDialog(fenetre, "Le numéro de port saisi n’est pas un entier");
                             }
                         }
                     }
                    
                    break;

                case "QUITTER":
                    if (client.isConnecte()) {
                        res = JOptionPane.showConfirmDialog(fenetre, "Vous allez vous déconnecter",
                                "Confirmation Quitter",
                                JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                        if (res == JOptionPane.OK_OPTION){
                            client.deconnecter();
                            System.exit(0);
                        }
                    }
                    else
                        System.exit(0);
                    break;
            }
        }
    }
}