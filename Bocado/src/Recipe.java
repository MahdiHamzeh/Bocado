import java.util.ArrayList;

public class Recipe {

    private String name;
    private ArrayList<Ingredients> ingredients = new ArrayList<Ingredients>();

    public Recipe(String name) {
        this.name = name;
    }

    public void addIngredient(Ingredients ingredient) {
        ingredients.add(ingredient);
    }

    public void addIngredients(ArrayList<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Ingredients> getIngredients() {
        return ingredients;
    }

    public String toString() {

        String res = name + ": " + ingredients.toString();

        return res;
    }
}
