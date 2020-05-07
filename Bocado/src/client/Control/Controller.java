package client.Control;
import client.View.CenterPanel;
import client.View.MainFrame;
import server.recipeHandler.Recipe;
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
        cPanel.setRecipeSearchResult(recipes);
    }

    public ArrayList<String> readIngredients(){
        ArrayList<String> ingredients = new ArrayList<>();
        try {
            File file = new File("files2/Ingredients.txt");
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                ingredients.add(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } return ingredients;
    }

}
