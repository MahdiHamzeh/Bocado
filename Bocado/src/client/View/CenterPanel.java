package client.View;

import client.Control.Controller;
import server.recipeHandler.Recipe;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

public class CenterPanel extends JPanel {

    Controller controller;

    private JList searchList;
    private JScrollPane scrollPaneSearchList, scrollPaneRecipe;
    private JTextArea txtAreaRecipe;

    private JPanel leftPanel, rightPanel;

    private DefaultListModel dlm;

    public CenterPanel(Controller controller){
        this.controller=controller;
        setLayout(new GridLayout(1,2,4,4));
        LeftPanel();
        RightPanel();
        add(leftPanel);
        add(rightPanel);
    }

    public void setRecipeSearchResult(ArrayList<Recipe> recipes){

        dlm = new DefaultListModel();

        for(int i =0; i<recipes.size();i++){
            dlm.addElement(recipes.get(i));
        }
        searchList.setModel(dlm);
    }

    public void clearRecipes() {
        dlm.removeAllElements();
        txtAreaRecipe.setText("");
    }

    
    public void setSelectedRecipe(Recipe recipe) {

        String ingredients = "";
        ArrayList<String> arr = recipe.getIngredients();
        for(int i = 0; i<arr.size(); i++) {
            ingredients += arr.get(i) + "\n";
        }
        String str = recipe.getName() + "\n\n" + ingredients + "\n" + recipe.getInstructions();

        txtAreaRecipe.setText(str);
    }



    public void LeftPanel(){
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Sök Resultat"));

        searchList = new JList();
        searchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPaneSearchList = new JScrollPane(searchList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        leftPanel.add(scrollPaneSearchList,BorderLayout.CENTER);
        searchList.addListSelectionListener(new ListListener());
    }

    public void RightPanel(){
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Recept"));

        txtAreaRecipe = new JTextArea();
        txtAreaRecipe.setEditable(false);
        scrollPaneRecipe = new JScrollPane(txtAreaRecipe,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        rightPanel.add(scrollPaneRecipe,BorderLayout.CENTER);


    }

    private class ListListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {

            try{
                setSelectedRecipe((Recipe)searchList.getSelectedValue());
            } catch(NullPointerException l) {

            }
        }
    }
}
