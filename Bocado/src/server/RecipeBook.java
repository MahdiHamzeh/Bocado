package server;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a book of recipes, handles all recipes and keeps track of
 * which ingredients exist in recipes.
 * @version 1.1
 * @author Andreas Månsson
 */
public class RecipeBook {

    private ArrayList<Recipe> recipes;
    private ArrayList<String> ingredients = new ArrayList<String>();

    /**
     * Constructor for the server.Recipe-book. Runs the methods importRecipes() and checkIngredients to populate
     * the recipes- and ingredients-variables.
     */
    public RecipeBook() {
        importRecipes();
        //listRecipes();
        checkIngredients();
        //Constructor currently contains some server.test-values just to see that the code works.
        ArrayList<String> testFilter = new ArrayList<String>();
        testFilter.add("Broccoli");
        testFilter.add("Gul lök");
        testFilter.add("Vitlök");
        testFilter.add("Pasta");
        testFilter.add("Ris");
        testFilter.add("Rättika");
        ArrayList<Recipe> testRecipes = filterRecipes(testFilter);

        System.out.println("Filtered recipes:");
        System.out.println(testRecipes.toString());
        sortFilteredRecipes(testRecipes, testFilter);
    }

    /**
     * Imports all recipes using the server.RecipeReader-class.
     * Stores them in the recipes-variable.
     */
    public void importRecipes() {
        RecipeReader rr = new RecipeReader();
        recipes = rr.readAllRecipes();
    }

    /**
     * Checks the ingredients of all recipes in the recipes-variable, then adds
     * them to the ingredients-variable. Filters out duplicates.
     */
    public void checkIngredients() {

        for(int i = 0; i<recipes.size(); i++) {

            ArrayList<String> currentRecipeIngredients = recipes.get(i).getIngredients();

            for(int j = 0; j<currentRecipeIngredients.size(); j++) {
                int count = 0;
                for(int b = 0; b<ingredients.size(); b++) {
                    if(currentRecipeIngredients.get(j).equals(ingredients.get(b))) {
                        count++;
                    }
                }

                if(count == 0) {
                    ingredients.add(currentRecipeIngredients.get(j));
                }
            }
        }

        /* Test, lists out all ingredients added
        for(int i = 0; i<ingredients.size(); i++) {
            System.out.println(ingredients.get(i));
        }*/
    }

    /**
     * Iterates through the recipes and returns a new ArrayList of server.Recipe-objects,
     * with only the ingredients in the parameter.
     * @param ingredients array of ingredients to filter by
     * @return new ArrayList of server.Recipe-objects after filtering
     */
    public ArrayList<Recipe> filterRecipes(ArrayList<String> ingredients) {

        ArrayList<Recipe> filteredRecipes = new ArrayList<Recipe>();

        for(int i = 0; i<recipes.size(); i++) {

            int count = 0;

            ArrayList<String> currentRecipeIngredients = recipes.get(i).getIngredients();

            for(int j = 0; j<currentRecipeIngredients.size(); j++) {

                for(int b = 0; b<ingredients.size(); b++) {

                    if(currentRecipeIngredients.get(j).equals(ingredients.get(b))) {
                        count++;
                    }
                }
            }

            if(count != 0) {
                filteredRecipes.add(recipes.get(i));
                recipes.get(i).toString();
            }
        }

        return filteredRecipes;
    }

    /**
     * Recieves the newly filtered recipe-list and the ingredients the recipes were filtered by,
     * then sorts them by how many ingredients from the filter that corresponds with the recipe.
     * @param filteredRecipes the recipes that contain any of the ingredients
     * @param ingredients the ingredients the recipes have been filtered by.
     * @return returns the sorted ArrayList
     */
    public ArrayList<Recipe> sortFilteredRecipes(ArrayList<Recipe> filteredRecipes, ArrayList<String> ingredients) {

        int[] ingredientCount = new int[filteredRecipes.size()];
        ArrayList<Recipe> sortedRecipes = new ArrayList<Recipe>();

        for(int i = 0; i<filteredRecipes.size(); i++) {

            System.out.println("Checking recipe: " + i);

            ArrayList<String> currentRecipeIngredients = filteredRecipes.get(i).getIngredients();
            int count = 0;

            for(int j = 0; j<currentRecipeIngredients.size(); j++) {
                for(int b = 0; b<ingredients.size(); b++) {
                    if(currentRecipeIngredients.get(j).equals(ingredients.get(b))) {
                        System.out.println("Adding count to recipe:" + i);
                        count++;
                    }
                }
            }

            ingredientCount[i] = count;
        }


        for(int i = 0; i<ingredientCount.length -1; i++) {
            int index = i;

            for(int j = i + 1; j<ingredientCount.length; j++) {
                if(ingredientCount[j] > ingredientCount[index]) {
                    index = j;

                    int smallerNumber = ingredientCount[index];
                    ingredientCount[index] = ingredientCount[i];
                    ingredientCount[i] = smallerNumber;
                    Collections.swap(filteredRecipes, index, i);
                }
            }
        }
        //Test: prints out the sorted int-array that's used for sorting the recipes
        for(int x : ingredientCount) {
            System.out.println(x);
        }
        //Test: prints out the name of the sorted recipes
        for(Recipe s : filteredRecipes) {
            System.out.println(s.getName());
        }
        return sortedRecipes;
    }

    /**
     * Prints out the recipes contained in the recipes-variable in the console-window.
     * Used for testing-purposes.
     */
    public void listRecipes() {
        for(int i = 0; i<recipes.size(); i++) {
            System.out.println(recipes.get(i).toString());
        }
    }

    /*public void filter(ArrayList<Ingredients> list) {
        ArrayList<server.Recipe> filtered = new ArrayList<server.Recipe>();

        for(int i = 0; i<recipes.size(); i++) {
            int count = 0;
            ArrayList<Ingredients> temp = recipes.get(i).getIngredients();

            for(int j = 0; j<temp.size(); j++) {
                for(int b = 0; b<list.size(); b++) {
                    if(temp.get(j) == list.get(b)) {
                        count++;
                    }
                }
            }

            if(count != 0) {
                filtered.add(recipes.get(i));
            }
        }

        System.out.println(filtered.toString());
    }*/
}
