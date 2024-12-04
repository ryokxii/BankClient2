package com.atoudeft.controleur;

import com.atoudeft.client.Client;
import com.atoudeft.vue.PanneauOperationsCompte;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurListeComptes extends MouseAdapter {

    private Client client;

    public EcouteurListeComptes(Client client) {
        this.client = client;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            JList<String> listeComptes = (JList<String>) evt.getSource();
            String compteSelectionne = listeComptes.getSelectedValue();

            if (compteSelectionne != null) {
                //PREND CE QU'IL Y A ENTRE CROCHETS
                String typeCompte = compteSelectionne.substring(
                        compteSelectionne.indexOf('[') + 1,
                        compteSelectionne.indexOf(']'));

                // LE TYPE
                if ("CHEQUE".equalsIgnoreCase(typeCompte)) {
                    client.envoyer("SELECT cheque");
                } else if ("EPARGNE".equalsIgnoreCase(typeCompte)) {
                    client.envoyer("SELECT epargne");
                }
            }
        }
    }
}