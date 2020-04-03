import java.util.ArrayList;

/**
 * Represents a book of recipes, handles all recipes and keeps track of
 * which ingredients exist in recipes.
 * @version 1.0
 * @author Andreas Månsson
 */
public class RecipeBook {

    private ArrayList<Recipe> recipes;
    private ArrayList<String> ingredients = new ArrayList<String>();

    public RecipeBook() {
        importRecipes();
        listRecipes();
        checkIngredients();
    }

    /**
     * Imports all recipes using the RecipeReader-class.
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

        /* Test
        for(int i = 0; i<ingredients.size(); i++) {
            System.out.println(ingredients.get(i));
        }*/
    }

    public void listRecipes() {
        for(int i = 0; i<recipes.size(); i++) {
            System.out.println(recipes.get(i).toString());
        }
    }

    /*public void filter(ArrayList<Ingredients> list) {
        ArrayList<Recipe> filtered = new ArrayList<Recipe>();

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
