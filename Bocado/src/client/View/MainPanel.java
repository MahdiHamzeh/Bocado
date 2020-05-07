package client.View;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel(){
        setPreferredSize(new Dimension(800,600));
        setLayout(new BorderLayout());
        NorthPanel nPanel = new NorthPanel();
        CenterPanel cPanel = new CenterPanel();
        add(nPanel,BorderLayout.NORTH);
        add(cPanel,BorderLayout.CENTER);

    }

}
