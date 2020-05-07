package client.View;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends JPanel {

    private JList searchList;
    private JScrollPane scrollPaneSearchList, scrollPaneRecipe;
    private JTextArea txtAreaRecipe;

    private JPanel leftPanel, rightPanel;

    public CenterPanel(){
        setLayout(new GridLayout(1,2,4,4));
        LeftPanel();
        RightPanel();
        add(leftPanel);
        add(rightPanel);


    }

    public void LeftPanel(){
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Sök Resultat"));

        searchList = new JList();
        searchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPaneSearchList = new JScrollPane(searchList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        leftPanel.add(scrollPaneSearchList,BorderLayout.CENTER);


    }

    public void RightPanel(){
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Recept"));

        txtAreaRecipe = new JTextArea();
        scrollPaneSearchList = new JScrollPane(txtAreaRecipe,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        rightPanel.add(scrollPaneSearchList,BorderLayout.CENTER);

    }
}
