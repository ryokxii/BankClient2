package com.atoudeft.vue;

import javax.swing.*;

import javafx.css.ParsedValue;

import java.awt.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauConfigServeur extends JPanel {
    private JTextField txtAdrServeur, txtNumPort;

    public PanneauConfigServeur(String adr, int port) {
        txtAdrServeur = new JTextField(10);
        txtNumPort = new JTextField(10);

        JLabel lIP = new JLabel("Adresse IP : ");
        JLabel lPort = new JLabel("Port : ");

        txtAdrServeur.setText(adr);
        txtNumPort.setText(String.valueOf(port));

        txtAdrServeur.setBorder(BorderFactory.createTitledBorder("Adresse IP : "));
        txtNumPort.setBorder(BorderFactory.createTitledBorder("Port : "));

        this.setLayout(new GridLayout(2, 2, 10, 10));
        this.add(lIP);
        this.add(txtAdrServeur);
        this.add(lPort);
        this.add(txtNumPort);
    }
    public String getAdresseServeur() {
        return txtAdrServeur.getText();
    }
    public String getPortServeur() {
        return txtNumPort.getText();
    }
}
