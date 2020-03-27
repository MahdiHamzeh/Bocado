import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RecipeBook {


    private ArrayList<Recipe> recipes = new ArrayList<Recipe>();

    public RecipeBook(int count) {
        populateRecpies(count);
    }

    public void populateRecpies(int count) {

        for(int i = 0; i<count; i++) {
            recipes.add(randomRecipe());
        }
    }

    public Recipe randomRecipe() {
        String name = randomName().toString();
        Random rand = new Random();
        Recipe res = new Recipe(name);
        res.addIngredients(randomIngredients(rand.nextInt(3) + 2));

        return res;
    }

    public ArrayList<Ingredients> randomIngredients(int count) {
        List<Ingredients> list = Arrays.asList(Ingredients.values());
        ArrayList<Ingredients> res = new ArrayList<Ingredients>();
        Random rand = new Random();
        int lastIngredient = -1;
        for(int i = 0; i<count; i++) {
            int newIngredient = rand.nextInt(list.size());

            while(newIngredient == lastIngredient) {
                newIngredient = rand.nextInt(list.size());
            }

            res.add(list.get(newIngredient));
            lastIngredient = newIngredient;
        }
        return res;
    }

    public StringBuffer randomName() {
        String letters = "abcdefgijklmnopqrstuvwxyz";
        Random rand = new Random();
        StringBuffer res = new StringBuffer();

        for(int i = 0; i<rand.nextInt(10) + 1; i++) {
            res.append(letters.charAt(rand.nextInt(25)));
        }
        return res;
    }


    public void filter(ArrayList<Ingredients> list) {
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
    }



    public String toString() {
        StringBuffer res = new StringBuffer();

        for(int i = 0; i<recipes.size(); i++) {
            res.append(recipes.get(i).toString() + "\n");
        }
        return res.toString();
    }
}
