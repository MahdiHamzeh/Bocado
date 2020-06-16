package server.controller;

import server.network.*;
import server.recipeHandler.*;
import server.userHandler.User;
import server.userHandler.UserDatabase;

import java.util.ArrayList;

/**
 * Controller-class that handles communication between the
 * network- and data-parts of the application.
 * @version 1.1
 * @author Andreas Månsson
 */

public class Controller {

    private RecipeBook recipeBook;
    private ClientReceiver clientReceiver;
    private UserDatabase userDatabase;
    private UserNetwork userNetwork;

    public Controller() {
        recipeBook = new RecipeBook();
        clientReceiver = new ClientReceiver(this, 2500);
        userDatabase = new UserDatabase();
        userNetwork = new UserNetwork(this, 3000);
    }

    /**
     * Sends request to the RecipeBook-object to filter and sort recipes based on the parameter filter.
     * @param filter ArrayList of Strings which contain the ingredients to filter and sort by.
     * @return an ArrayList of Recipe-objects after they are filtered and sorted.
     */
    public ArrayList<Recipe> getFilteredAndSortedRecipes(ArrayList<String> filter) {

        return recipeBook.filterRecipes(filter);


    }

    /**
     * Retunrs a single Recipe-object based on the parameter search.
     * @param search String of the name of the Recipe-object to return.
     * @return Recipe-objects whose name matches the search parameter.
     */
    public Recipe getRecipeSearch(String search) {

        return recipeBook.recipeSearch(search);
    }

    public User getUser(String[] userData) {
        User user = userDatabase.getUser(userData);
        return user;
    }

    public void updateUser(String[] userData) {
        userDatabase.updateUser(userData);
    }

}
