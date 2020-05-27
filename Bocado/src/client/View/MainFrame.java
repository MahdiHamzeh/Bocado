package client.View;

import client.Control.Controller;

import javax.swing.*;

public class MainFrame extends JFrame {
    Controller controller;
    MainPanel panel;

        public MainFrame(Controller controller){
            this.controller=controller;
            start();
        }

        public void start(){

            setSize(800,600);
            setTitle("Bocado v1.0");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocation(150,100);
            panel = new MainPanel(controller);
            add(panel);
            pack();


            setVisible(true);
        }

        public NorthPanel getNortherPanel(){
            return panel.getNorthpanel();
        }

        public CenterPanel getCenterpanel(){
            return panel.getCenterpanel();
        }

}
