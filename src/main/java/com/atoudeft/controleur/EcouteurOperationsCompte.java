package com.atoudeft.controleur;

import com.atoudeft.client.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class EcouteurOperationsCompte implements ActionListener {
    private Client client;

    public EcouteurOperationsCompte(Client client) {
        this.client = client;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // à compléter :
        Object source = e.getSource();
        String nomAction;
        if (source instanceof JButton) {
            nomAction = ((JButton) source).getActionCommand();
            if (nomAction.equals("EPARGNE")) {
                client.envoyer("EPARGNE");
            }
        }
    }
}
