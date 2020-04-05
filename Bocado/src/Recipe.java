import java.util.ArrayList;

/**
 * Represents a recipe, handles the name, ingredients and instructions
 * of the recipe.
 * @version 1.1
 * @author Andreas Månsson
 */
public class Recipe {

    private String name;
    private ArrayList<String> ingredients;
    private String instructions;

    public Recipe(String name, ArrayList<String> ingredients, String instructions) {
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
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
    public String toString() {
        String res = name + ": " + ingredients.toString() + "\n\n" + instructions;

        return res;
    }
}
