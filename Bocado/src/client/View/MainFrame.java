package client.View;

import client.Control.Controller;

import javax.swing.*;

/**
 * MainFrame-class that handles the entire View.
 * @version 1.3
 * @author Gustaf Hermansson
 */
public class MainFrame extends JFrame {
    private Controller controller;
    private MainPanel panel;


    public MainFrame(Controller controller){
            this.controller=controller;
            start();
        }

    /**
     * Sets up the frame.
     */
    public void start(){

            setSize(800,600);
            setTitle("Bocado");
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
