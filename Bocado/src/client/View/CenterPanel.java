package client.View;

import client.Control.Controller;
import server.recipeHandler.Recipe;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

/**
 * JPanel that handles display of outputs.
 * @version 1.5
 * @author Gustaf Hermansson
 * @edited Andreas Månsson, Mahdi Hamzeh
 */
public class CenterPanel extends JPanel {

    private Controller controller;

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

    /**
     * Sets the list of recipes to the parameter.
     * @param recipes ArrayList of Recipe-objects
     */
    public void setRecipeSearchResult(ArrayList<Recipe> recipes){

        dlm = new DefaultListModel();

        for(int i =0; i<recipes.size();i++){
            dlm.addElement(recipes.get(i));
        }
        searchList.setModel(dlm);
    }

    /**
     * Clears all info from all fields.
     */
    public void clearRecipes() {
        try{
            dlm.removeAllElements();
        }catch(NullPointerException e) {
        }

        txtAreaRecipe.setText("");
    }

    /**
     * Gets information from the Recipe-object input and outputs the formatted info
     * in the text area.
     * @param recipe
     */
    public void setSelectedRecipe(Recipe recipe) {

        String ingredients = "";
        ArrayList<String> arr = recipe.getIngredients();
        for(int i = 0; i<arr.size(); i++) {
            ingredients += arr.get(i) + "\n";
        }
        String str = recipe.getName() + "\n\n" + ingredients + "\n" + recipe.getInstructions();

        txtAreaRecipe.setText(str);
    }


    /**
     * Populuates the left panel.
     */
    public void LeftPanel(){
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Sök Resultat"));

        searchList = new JList();
        searchList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPaneSearchList = new JScrollPane(searchList,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        leftPanel.add(scrollPaneSearchList,BorderLayout.CENTER);
        searchList.addListSelectionListener(new ListListener());
    }

    /**
     * Populates the right panel.
     */
    public void RightPanel(){
        rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Recept"));

        txtAreaRecipe = new JTextArea();
        txtAreaRecipe.setEditable(false);
        scrollPaneRecipe = new JScrollPane(txtAreaRecipe,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        rightPanel.add(scrollPaneRecipe,BorderLayout.CENTER);


    }

    /**
     * Listener class that listens to the list of Recipe-objects.
     * @author Andreas Månsson, Gustaf Hermansson
     */
    private class ListListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {

            try{
                setSelectedRecipe((Recipe)searchList.getSelectedValue());
            } catch(NullPointerException l) {

            }
        }
    }
}
