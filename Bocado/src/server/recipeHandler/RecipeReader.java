package server.recipeHandler;

import java.io.*;
import java.util.ArrayList;

/**
 * Handles reading of recipes from text files.
 * @version 1.1
 * @author Andreas Månsson
 * @edited Mahdi Hamza
 */
public class RecipeReader {

    public RecipeReader() {
        readAllRecipes();
    }


    /**
     * Iterates through all files in a directory and calls the readRecipe-method
     * for each file.
     */
    public ArrayList<Recipe> readAllRecipes() {

        File directory = new File("files");
        File[] directoryListing = directory.listFiles();
        ArrayList<Recipe> recipes = new ArrayList<Recipe>();

        if(directoryListing != null) {
            for(File child : directoryListing) {
                recipes.add(readRecipe(child.getAbsolutePath()));
            }
        }

        /*Test
        for(int i = 0; i<recipes.size(); i++) {
            System.out.println(recipes.get(i).toString());
        }*/

        return recipes;
    }
    /**
     * Reads a file with a specific format to create a server.recipeHandler.Recipe-object.
     * @param filepath path to the file being read
     * @return the finished server.recipeHandler.Recipe-object
     */
    public Recipe readRecipe(String filepath) {

        StringBuffer str = new StringBuffer();
        String line;
        ArrayList<String> ingredients = new ArrayList<String>();
        Recipe recipe;

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filepath), "UTF-8"))) {

            line = br.readLine();

            while(line!=null) {

                if(line.length() > 0 && line.substring(0, 4).equals("Ing:") == true) {
                    //System.out.println("Ingredient added: " + line.substring(4)); //Test
                    ingredients.add(line.substring(4));
                    line = br.readLine();
                }
                else {
                    //System.out.println("Instructions added: " + line); //Test
                    str.append(line+"\n");
                    line = br.readLine();
                }
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        String instructions = str.toString();
        String name = filepath.substring(filepath.lastIndexOf('\\')+1, filepath.lastIndexOf('.'));
        recipe = new Recipe(name, ingredients, instructions);

        return recipe;
    }
}
