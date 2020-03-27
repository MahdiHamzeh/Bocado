import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        RecipeBook rb = new RecipeBook(10);

        JOptionPane.showMessageDialog(null, "10 recept skapas, namn och ingredienser slumpas");
        System.out.println(rb.toString());
        JOptionPane.showMessageDialog(null, "Filtrerar dessa 10 recept efter ingredienserna beef och salt");
        ArrayList<Ingredients> list = new ArrayList<Ingredients>();
        list.add(Ingredients.Salt);
        list.add(Ingredients.Beef);
        rb.filter(list);


    }
}
