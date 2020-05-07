package client.View;

import javax.swing.*;

public class MainFrame extends JFrame {

        public MainFrame(){
            start();
        }

        public void start(){

            setSize(800,600);
            setTitle("Bocado");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocation(150,100);
            MainPanel panel = new MainPanel();
            add(panel);
            pack();


            setVisible(true);
        }

}
