package client.Control;

import client.View.CenterPanel;
import client.View.MainFrame;
import client.View.NorthPanel;

import server.recipeHandler.Recipe;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

    MainFrame mainframe;

    public Controller() {
        mainframe=new MainFrame(this);
    }

    public void filterRecipes(String filter){
        ArrayList<Recipe> recipes=new ServerConnection("localhost",2500).sendRequest(filter);
        CenterPanel cPanel = mainframe.getCenterpanel();
        cPanel.setRecipeList(recipes);
    }

    public void readIngredients(){
        try {
            File file = new File("files2/Ingredients.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                String info = myReader.nextLine();
                System.out.println(info);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
