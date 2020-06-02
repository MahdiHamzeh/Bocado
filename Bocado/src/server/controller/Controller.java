package server.controller;

import server.network.*;
import server.recipeHandler.*;

import java.util.ArrayList;

/**
 * Controller-class that handles communication between the
 * network- and data-parts of the application.
 * @version 1.1
 * @author Andreas Månsson
 */

public class Controller {

    RecipeBook recipeBook;
    ClientReceiver clientReceiver;

    public Controller() {
        recipeBook = new RecipeBook();
        clientReceiver = new ClientReceiver(this, 2500);
    }


    public ArrayList<Recipe> getFilteredAndSortedRecipes(ArrayList<String> filter) {

        return recipeBook.filterRecipes(filter);


    }

    public Recipe getRecipeSearch(String search) {

        return recipeBook.recipeSearch(search);
    }
}
