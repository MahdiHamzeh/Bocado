package server.recipeHandler;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a recipe, handles the name, ingredients and instructions
 * of the recipe.
 * @version 1.1
 * @author Andreas Månsson
 */
public class Recipe implements Serializable {

    private String name;
    private ArrayList<String> ingredients;
    private String instructions;

    public Recipe(String name, ArrayList<String> ingredients, String instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public Recipe(String name) {
        this.name = name;
        this.ingredients = new ArrayList<String>();
        ingredients.add("null");
        this.instructions = "null";
    }

    /**
     * Gets the recipe's name.
     * @return the recipe's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the recipe's ingredients.
     * @return the recipe's ingredients
     */
    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    /**
     * Gets the recipe's instructions.
     * @return the recipe's instructions
     */
    public String getInstructions() {
        return instructions;
    }

    /**
     * Returns a formatted description of the recipe.
     * @return a String
     */

    /**
     * toString is used in a JList of recipe-names, hence it returns the name.
     * @return recipe-name
     */
    public String toString() {
        String res = getName();
        return res;
    }
}
