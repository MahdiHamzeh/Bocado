package client.Control;
import client.View.CenterPanel;
import client.View.MainFrame;
import client.View.NorthPanel;
import server.recipeHandler.Recipe;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import server.userHandler.User;

import javax.swing.*;

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
        String username = "LOG," + JOptionPane.showInputDialog("Skriv in ditt användarnamn: ") + ",";
        getUser(username);
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

    /**
     * Creates a UserGetter-object that looks up the user matching the parameter,
     * then uses the resulting User-object to update information in the view.
     * @param username String containing username
     */
    public void getUser(String username) {
        UserGetter userGetter = new UserGetter("localhost", 3000);

        NorthPanel np = mainframe.getNortherPanel();

        np.setUser(userGetter.getUser(username));
    }

    /**
     * Sends data to the server to update a User-object.
     * @param userData formatted String containing user data.
     */
    public void updateUser(String userData) {
        UserUpdater userUpdater = new UserUpdater("localhost", 3000);
        userUpdater.sendUser(userData);
    }

}
