package client.Control;

import client.View.CenterPanel;
import client.View.MainFrame;
import client.View.NorthPanel;

import server.recipeHandler.Recipe;

import javax.swing.*;
import java.util.ArrayList;

public class Controller {

    MainFrame mainframe;

    public Controller() {
        mainframe=new MainFrame(this);
    }

    public void add(String ingredients){

    }

    public void filterRecipes(String filter){
        ArrayList<Recipe> recipes=new ServerConnection("localhost",2500).sendRequest(filter);
        CenterPanel cPanel = mainframe.getCenterpanel();
        cPanel.setRecipeList(recipes);
    }

}
