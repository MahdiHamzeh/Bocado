package client.Control;
import client.View.CenterPanel;
import client.View.MainFrame;
import server.recipeHandler.Recipe;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles communication between the view and network-class.
 * @version 1.3
 * @author Gustaf Hermansson
 * @edited Andreas Månsson
 */

public class Controller {

    private MainFrame mainframe;

    public Controller() {
        mainframe=new MainFrame(this);
    }

    /**
     * Recieves a filter in the form of a String, which is then sent to the network class.
     * @param filter
     */
    public void filterRecipes(String filter){
        ArrayList<Recipe> recipes=new ServerConnection("localhost",2500).sendRequest(filter);
        CenterPanel cPanel = mainframe.getCenterpanel();
        cPanel.setRecipeSearchResult(recipes);
    }

    /**
     * Clears recipes that are displayed.
     */
    public void clearRecipes() {
        CenterPanel cPanel = mainframe.getCenterpanel();
        cPanel.clearRecipes();
    }

    /**
     * Recieves a search-term in the form of a String which is then sent to the network class.
     * @param search
     */
    public void recipeSearch(String search) {

        Recipe result = new ServerConnection("localhost", 2500).sendSearch(search);
        CenterPanel cPanel = mainframe.getCenterpanel();
        cPanel.setSelectedRecipe(result);

    }

    /**
     * Reads all ingredients from the file "Ingredients.txt" and returns them in an ArrayList.
     * @return all ingredients read.
     */
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
