package client.View;

import client.Control.Controller;
import server.recipeHandler.Recipe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class NorthPanel extends JPanel {

    DefaultListModel dm = new DefaultListModel();
    Controller controller;

    private JPanel leftPanel,rightPanel,buttonPanel;
    private JButton btnSearch,btnAdd, btnDelete,btnClearAll;
    private JComboBox cbSearch;
    private JList list;
    private JScrollPane scrollList;

    public NorthPanel(Controller controller){
        this.controller=controller;
        setLayout(new GridLayout(1,2,4,4));
        leftPanel();
        rightPanel();
        add(leftPanel);
        add(rightPanel);
    }

    public Object getIngredientsText(){
        return cbSearch.getSelectedItem();
    }

    public String getListIngredients(){
        String str="";
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

    public void rightPanel(){

        ArrayList<String> ingredients = getReadIngredients();

        rightPanel = new JPanel(new FlowLayout());
        buttonPanel = new JPanel(new GridLayout(1,3,4,4));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Sök"));

        cbSearch = new JComboBox(ingredients.toArray());
        cbSearch.setPreferredSize(new Dimension(200,25));
        cbSearch.setEditable(false);

        rightPanel.add(cbSearch);

        btnAdd = new JButton("Lägg Till");
        btnDelete = new JButton("Ta Bort");
        btnSearch = new JButton("Sök");
        btnClearAll = new JButton("Rensa Allt");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);
        btnSearch.setEnabled(false);
        buttonPanel.add(btnClearAll);

        ButtonListener listener = new ButtonListener();
        btnAdd.addActionListener(listener);
        btnDelete.addActionListener(listener);
        btnSearch.addActionListener(listener);
        btnClearAll.addActionListener(listener);

        rightPanel.add(buttonPanel);

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

        }
    }
}
