package server.controller;

import server.network.*;
import server.recipeHandler.*;

import java.util.ArrayList;


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
}
