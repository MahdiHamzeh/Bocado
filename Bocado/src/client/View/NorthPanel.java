package client.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthPanel extends JPanel {

    DefaultListModel dm = new DefaultListModel();

    private JPanel leftPanel,rightPanel,buttonPanel;
    private JButton btnSearch,btnAdd, btnDelete,btnClearAll;
    private JTextField tfSearch;
    private JList list;
    private JScrollPane scrollList;

    public NorthPanel(){
        setLayout(new GridLayout(1,2,4,4));
        leftPanel();
        rightPanel();
        add(leftPanel);
        add(rightPanel);
    }

    public String getIngredientsText(){
        return tfSearch.getText();
    }

    public String getListIngredients(){
        String str="";
        for(int i = 0; i<list.getModel().getSize();i++){
            str += list.getModel().getElementAt(i) + ",";
        } return str;
    }

    public void clearIngredientsText(){
        tfSearch.setText("");
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
        rightPanel = new JPanel(new FlowLayout());
        //setOpaque(false);
        //rightPanel.setBackground(Color.GRAY);
        buttonPanel = new JPanel(new GridLayout(1,4,4,4));
        //buttonPanel.setBackground(Color.GRAY);
        rightPanel.setBorder(BorderFactory.createTitledBorder("Sök"));


        tfSearch = new JTextField();

        tfSearch.setPreferredSize(new Dimension(200,25));
        rightPanel.add(tfSearch);

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
                getListIngredients();
            }
            else if (e.getSource()==btnClearAll){}

        }
    }
}
