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
        int index = list.getSelectedIndex();
        dm.removeElementAt(index);
    }

    public void leftPanel(){
        leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Ingredienser"));

        list = new JList();
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        scrollList = new JScrollPane(list, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        leftPanel.add(scrollList);

    }

    public void rightPanel(){

        ArrayList<String> ingredients = getReadIngredients();

        rightPanel = new JPanel(new FlowLayout());
        buttonPanel = new JPanel(new GridLayout(1,4,4,4));

        rightPanel.setBorder(BorderFactory.createTitledBorder("Sök"));

        cbSearch = new JComboBox(ingredients.toArray());
        cbSearch.setPreferredSize(new Dimension(200,25));
        cbSearch.setEditable(true);

        rightPanel.add(cbSearch);

        btnAdd = new JButton("Lägg Till");
        btnDelete = new JButton("Ta Bort");
        btnSearch = new JButton("Sök");
        btnClearAll = new JButton("Rensa Allt");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnSearch);
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
            }
            else if (e.getSource()== btnDelete){
                deleteIngredientsText();
            }
            else if (e.getSource()==btnSearch){
                String str=getListIngredients();
                controller.filterRecipes(str);
            }
            else if (e.getSource()==btnClearAll){}

        }
    }
}
