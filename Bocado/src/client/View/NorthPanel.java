package client.View;

import client.Control.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JTabbedPane;

/**
 * JPanel that handles inputs.
 * @version 1.5
 * @author Gustaf Hermansson
 * @edited Andreas Månsson, Mahdi Hamzeh
 */
public class NorthPanel extends JPanel {

    private DefaultListModel dm = new DefaultListModel();
    private DefaultListModel personalIngredients = new DefaultListModel();
    private Controller controller;

    private JPanel buttonPanel;
    private JTabbedPane rightPanel,leftPanel;
    private JButton btnSearch,btnAdd, btnDelete,btnClearAll,btnRecipeSearch,btnSave;
    private JCheckBox cbSaved;
    private JComboBox cbSearch, cbRecipe;
    private JList listIngredients,listSaved;
    private JScrollPane scrollList;
    private ButtonListener listener = new ButtonListener();

    public NorthPanel(Controller controller){
        this.controller=controller;
        setLayout(new GridLayout(1,2,4,4));
        leftPanel();
        add(leftPanel);
        rightPanel();
        add(rightPanel);
    }

    /**
     * Gets the selected item of the list of ingredients.
     * @return the currently selected item
     */
    public Object getIngredientsText(){
        return cbSearch.getSelectedItem();
    }

    /**
     * Gets all ingredients that have been added to the left list.
     * @return list of ingredients in a formatted String.
     */
    public String getListIngredients(){
        String str="ING,";
        if(cbSaved.isSelected() == true) {
            for(int i = 0; i< listIngredients.getModel().getSize(); i++){
                str += listIngredients.getModel().getElementAt(i) + ",";
            }
            for(int i = 0; i<listSaved.getModel().getSize(); i++) {
                str += listSaved.getModel().getElementAt(i) + ",";
            }
        }
        else {
            for(int i = 0; i< listIngredients.getModel().getSize(); i++){
                str += listIngredients.getModel().getElementAt(i) + ",";
            }
        }
        return str;
    }

    /**
     * Calls the controller to get all ingredients from text file.
     * @return list of all ingredients.
     */
    public ArrayList<String> getReadIngredients(){
        ArrayList<String> ingredients;
        ingredients= controller.readIngredients();
        return ingredients;
    }

    /**
     * Sets the selected index of the ingredients list to 0.
     */
    public void clearIngredientsText(){
        cbSearch.setSelectedIndex(0);
    }

    /**
     * Deletes an ingredient from the list of selected ingredients.
     */
    public void deleteIngredientsText(DefaultListModel dlm, JList list){
        try {
            int index = list.getSelectedIndex();
            dlm.removeElementAt(index);
        } catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null,"Markera den ingrediens du vill ta bort");
        }
    }

    /**
     * Clears all search-fields and selected recipes.
     */
    public void clearAll() {
        dm.removeAllElements();
        controller.clearRecipes();
    }

    /**
     * Checks if the list of ingredients is empty.
     * @return a boolean of the result of the check.
     */
    public boolean isIngredientsEmpty() {
        if(listIngredients.getModel().getSize() == 0 && listSaved.getModel().getSize() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Sets up the left panel.
     */

    public void leftPanel(){
        leftPanel = new JTabbedPane();
        JComponent panel1 = makeLeftPanel();
        leftPanel.addTab("Ingredienser", panel1);
        leftPanel.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makeLeftPanel2();
        leftPanel.add("Mitt Skafferi", panel2);
        leftPanel.setMnemonicAt(1, KeyEvent.VK_2);
    }

    public JComponent makeLeftPanel(){
        JPanel panel = new JPanel(false);
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Ingredienssök"));

        listIngredients = new JList();
        listIngredients.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listIngredients.setSelectedIndex(0);
        listIngredients.setPreferredSize(new Dimension(250, 250));
        scrollList = new JScrollPane(listIngredients, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollList);

        return panel;

    }

    public JComponent makeLeftPanel2(){
        JPanel panel = new JPanel(false);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Mitt Skafferi"));

        listSaved = new JList();
        listSaved.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listSaved.setSelectedIndex(0);
        listSaved.setPreferredSize(new Dimension(250, 250));
        scrollList = new JScrollPane(listSaved, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(scrollList,BorderLayout.CENTER);

        btnSave = new JButton("Spara Skafferi");
        panel.add(btnSave,BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Sets up the right panel.
     */
    public void rightPanel() {

        rightPanel = new JTabbedPane();
        JComponent panel1 = makeRightPanel();
        rightPanel.addTab("Ingredienssök", panel1);
        rightPanel.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makeRightPanel2();
        rightPanel.add("Receptsök", panel2);
        rightPanel.setMnemonicAt(1, KeyEvent.VK_2);

    }

    /**
     * Creates and returns a panel for the tabbed pane.
     * @return completed JPanel
     */
    public JComponent makeRightPanel() {

        ArrayList<String> ingredients = getReadIngredients();

        JPanel panel = new JPanel(false);
        panel.setLayout(new FlowLayout());
        buttonPanel = new JPanel(new GridLayout(1,3,4,4));
        panel.setBorder(BorderFactory.createTitledBorder("Sök"));

        cbSearch = new JComboBox(ingredients.toArray());
        cbSearch.setPreferredSize(new Dimension(200,25));
        cbSearch.setEditable(false);

        panel.add(cbSearch);

        cbSaved = new JCheckBox("Sök med Skafferi");
        cbSaved.addActionListener(listener);

        panel.add(cbSaved);

        btnAdd = new JButton("Lägg Till");
        btnDelete = new JButton("Ta Bort");
        btnSearch = new JButton("Sök");
        btnClearAll = new JButton("Rensa Allt");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnSearch);
        buttonPanel.add(btnDelete);
        btnSearch.setEnabled(false);
        buttonPanel.add(btnClearAll);


        btnAdd.addActionListener(listener);
        btnDelete.addActionListener(listener);
        btnSearch.addActionListener(listener);
        btnClearAll.addActionListener(listener);

        panel.add(buttonPanel);

        return panel;
    }

    /**
     * Creates and returns a panel for the tabbed pane.
     * @return completed JPanel
     */
    public JComponent makeRightPanel2() {

        JPanel panel = new JPanel(false);
        panel.setLayout(new FlowLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Sök"));
        
        String[] recipe = {"Carbonara", "Lasagne", "Hopkok", "Curry", "Fransk Potatissallad", "BangBangKyckling","Ungsbackad Lax med Teriyaki",
        "Wok", "Pasta med pesto"};


        cbRecipe = new JComboBox(recipe);
        cbRecipe.setPreferredSize(new Dimension(200,25));
        cbRecipe.setEditable(false);

        btnRecipeSearch = new JButton("Sök");
        btnRecipeSearch.addActionListener(listener);

        panel.add(cbRecipe);
        panel.add(btnRecipeSearch);

        return panel;
    }

    /**
     * Listener class that listens for button-presses, and handles them accordingly.
     */
    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if  (e.getSource()== btnAdd){
                if(leftPanel.getSelectedIndex() == 0) {
                    listIngredients.setModel(dm);
                    dm.addElement(getIngredientsText());
                    clearIngredientsText();
                }
                else if(leftPanel.getSelectedIndex() == 1) {
                    listSaved.setModel(personalIngredients);
                    personalIngredients.addElement(getIngredientsText());
                    clearIngredientsText();
                }

                if(isIngredientsEmpty() == true) {
                    btnSearch.setEnabled(false);
                }
                else {
                    btnSearch.setEnabled(true);
                }
            }
            else if (e.getSource()== btnDelete){

                if(leftPanel.getSelectedIndex() == 0) {
                    deleteIngredientsText(dm, listIngredients);
                }
                else if(leftPanel.getSelectedIndex() == 1) {
                    deleteIngredientsText(personalIngredients, listSaved);
                }

                if(isIngredientsEmpty() == true) {
                    btnSearch.setEnabled(false);
                }
                else {
                    btnSearch.setEnabled(true);
                }
            }
            else if (e.getSource()==btnSearch){
                String str=getListIngredients();
                controller.filterRecipes(str);
                if(isIngredientsEmpty() == true) {
                    btnSearch.setEnabled(false);
                }
                else {
                    btnSearch.setEnabled(true);
                }
            }
            else if (e.getSource()==btnClearAll){
                clearAll();
                if(isIngredientsEmpty() == true) {
                    btnSearch.setEnabled(false);
                }
                else {
                    btnSearch.setEnabled(true);
                }
            }
            else if (e.getSource() == btnRecipeSearch) {
                String str = "REC,";
                str += cbRecipe.getSelectedItem().toString() + ",";
                controller.recipeSearch(str);
            }

        }
    }
}
