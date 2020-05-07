package client.Model;

public class NewIngredients {
    private String[] searchItems;
    private final int MAX_ITEMS = 30;
    private String newIngredients = "";

    public NewIngredients(){
        searchItems = new String[MAX_ITEMS];
    }

    public String getNewIngredients() {
        return newIngredients;
    }

    public void setNewIngredients(String newIngredients) {
        this.newIngredients = newIngredients;
    }

    public String toString(){
        return getNewIngredients();
    }

}
