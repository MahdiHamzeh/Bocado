package server.userHandler;

import java.util.ArrayList;

public class User {

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
