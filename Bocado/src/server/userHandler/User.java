package server.userHandler;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class that represents a user and their saved ingredients.
 * @version 1.0
 * @author Andreas Månsson, Gustaf Hermansson, Mahdi Hamzeh
 */
public class User implements Serializable {

    private String name;
    private ArrayList<String> savedIngredients;

    public User(String name, ArrayList<String> savedIngredients) {
        this.name = name;
        this.savedIngredients = savedIngredients;
    }

    public User(String name) {
        this.name = name;
        this.savedIngredients = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getSavedIngredients() {
        return savedIngredients;
    }

    public void setName(String name) {
        this.name = name;
}

    public void setSavedIngredients(ArrayList<String> savedIngredients) {
        this.savedIngredients = savedIngredients;
    }

    public void addSavedIngredient(String ingredient) {
        savedIngredients.add(ingredient);
    }
}
