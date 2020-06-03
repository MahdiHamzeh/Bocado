package client.View;

import client.Control.Controller;
import server.recipeHandler.Recipe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.Flow;
import javax.swing.JTabbedPane;

public class NorthPanel extends JPanel {

    DefaultListModel dm = new DefaultListModel();
    Controller controller;

    private JPanel leftPanel,buttonPanel;
    private JTabbedPane rightPanel;
    private JButton btnSearch,btnAdd, btnDelete,btnClearAll,btnRecipeSearch;
    private JComboBox cbSearch, cbRecipe;
    private JList list;
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

    public Object getIngredientsText(){
        return cbSearch.getSelectedItem();
    }

    public String getListIngredients(){
        String str="ING,";
        for(int i = 0; i<list.getModel().getSize();i++){
            str += list.getModel().getElementAt(i) + ",";
        } return str;
    }

    public ArrayList<String> getReadIngredients(){
        ArrayList<String> ingredients;
        ingredients= controller.readIngredients();
        return ingredients;
    }

    public void clearIngredientsText(){
        cbSearch.setSelectedIndex(0);
    }

    public void deleteIngredientsText(){
        try {
            int index = list.getSelectedIndex();
            dm.removeElementAt(index);
        } catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null,"Markera den ingrediens du vill ta bort");
        }
    }

    public void clearAll() {
        dm.removeAllElements();
        controller.clearRecipes();
    }

    public boolean isIngredientsEmpty() {
        if(list.getModel().getSize() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public void leftPanel(){
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Ingredienser"));

        list = new JList();
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.setSelectedIndex(0);
        scrollList = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        leftPanel.add(scrollList);

    }


    public void rightPanel() {

        rightPanel = new JTabbedPane();
        JComponent panel1 = makePanel();
        rightPanel.addTab("Ingredienssök", panel1);
        rightPanel.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makePanel2();
        rightPanel.add("Receptsök", panel2);
        rightPanel.setMnemonicAt(1, KeyEvent.VK_2);

    }

    public JComponent makePanel() {

        ArrayList<String> ingredients = getReadIngredients();

        JPanel panel = new JPanel(false);
        panel.setLayout(new FlowLayout());
        buttonPanel = new JPanel(new GridLayout(1,3,4,4));
        panel.setBorder(BorderFactory.createTitledBorder("Sök"));

        cbSearch = new JComboBox(ingredients.toArray());
        cbSearch.setPreferredSize(new Dimension(200,25));
        cbSearch.setEditable(false);

        panel.add(cbSearch);

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

    public JComponent makePanel2() {

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


    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if  (e.getSource()== btnAdd){
                list.setModel(dm);
                dm.addElement(getIngredientsText());
                clearIngredientsText();
                if(isIngredientsEmpty() == true) {
                    btnSearch.setEnabled(false);
                }
                else {
                    btnSearch.setEnabled(true);
                }
            }
            else if (e.getSource()== btnDelete){
                deleteIngredientsText();
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
