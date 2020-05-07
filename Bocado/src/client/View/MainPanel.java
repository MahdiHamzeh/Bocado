package client.View;

import client.Control.Controller;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    Controller controller;
    NorthPanel nPanel;
    CenterPanel cPanel;

    public MainPanel(Controller controller){
        this.controller=controller;
        setPreferredSize(new Dimension(800,600));
        setLayout(new BorderLayout());
        nPanel = new NorthPanel(controller);
        cPanel = new CenterPanel(controller);
        add(nPanel,BorderLayout.NORTH);
        add(cPanel,BorderLayout.CENTER);

    }

    public NorthPanel getNorthpanel(){
        return nPanel;
    }

    public CenterPanel getCenterpanel(){
        return cPanel;
    }

}
