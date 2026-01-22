/* File name: RecipeManager.java
 *Author: Ekwezia Michael Chika, 041178206
 *Course: CST8284 -OOP
 *Assignment 3
 *Date: 11/12/2025
 *Professor: Veda Vasavi Erukulla
 *Class List: Assignment3.java, Recipe.java, RecipeManager.java
 */

package assn3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Loads recipes from a text file, stores them,
 * manages bread orders, and builds/saves shopping lists.
 *
 * @author Ekwezia Michael Chika
 * @version 21.0.8
 * @since JDK 21
 */
public class RecipeManager {

	/** List storing all loaded recipes. */
    private List<Recipe> recipes;

    /**
     * Constructs a new empty RecipeManager.
     */
    public RecipeManager() {
        this.recipes = new ArrayList<Recipe>();
    }

    /**
     * Loads recipes from a text file
     * 
     * @param filename recipe list file name
     * @throws FileNotFoundException if file cannot be found
     */
    public void loadRecipes(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scan = new Scanner(file);

        Recipe current = null;

        while (scan.hasNextLine()) {
            String line = scan.nextLine().trim();
            
            //Skips blank lines
            if (line.length() == 0) {
                continue;
            }
            //Separate recipe name saves it to recipe object
            if (line.startsWith("Recipe")) {
                if (current != null) {
                    this.recipes.add(current);
                }

                current = new Recipe();
                String[] parts = line.split(" ", 2);

                if (parts.length == 2) {
                    current.setName(parts[1]);
                } else {
                    current.setName("Unnamed");
                }

                continue;
            }

            // Split ingredient lines and values
            if (current != null) {
                String[] parts = line.split(" ");
                if (parts.length == 2) {
                    double value = 0.0;

                    try {
                        value = Double.parseDouble(parts[1]);
                    } catch (NumberFormatException e) {
                        value = 0.0;
                    }

                    String ingredient = parts[0].toLowerCase();

                    if (ingredient.equals("eggs")) { current.setEggs(value); }
                    else if (ingredient.equals("yeast")) { current.setYeast(value); }
                    else if (ingredient.equals("flour")) { current.setFlour(value); }
                    else if (ingredient.equals("sugar")) { current.setSugar(value); }
                    else if (ingredient.equals("butter")) { current.setButter(value); }
                }
            }
        }

        if (current != null) {
            this.recipes.add(current);
        }

        scan.close();
    }

    /**
     * Returns a list of recipe names.
     *
     * @return list of recipe names
     */
    public List<String> getRecipeNames() {
        List<String> names = new ArrayList<String>();
        for (Recipe r : this.recipes) {
            names.add(r.getName());
        }
        return names;
    }
    

    /**
     * Returns number of recipes loaded.
     *
     * @return recipe count
     */
    public int getRecipeCount() {
        return this.recipes.size();
    }
    
    /**
     * Adds a quantity of bread to the selected recipe by index.
     * 
     * @param index 1-based recipe index
     * @param quantity amount to order
     */
    public void orderBread(int index, int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        /**
         * Subtracts 1 from index to match user choice with recipe index.
         */
        int actual = index - 1;
        Recipe r = this.recipes.get(actual);
        r.addToQuantityOrdered(quantity);
    }

    /**
     * Builds the printable shopping list as a single String.
     * 
     * @return formatted shopping list
     */
    public String buildShoppingListString() {
        String result = "";

        double totalYeast = 0;
        double totalFlour = 0;
        double totalSugar = 0;
        double totalEggs = 0;
        double totalButter = 0;

        boolean anyOrders = false;

        for (Recipe r : this.recipes) {
            if (r.getQuantityOrdered() > 0) {
                anyOrders = true;
                result += r.getQuantityOrdered() + " " + r.getName() + " loaf/loaves.\n";

                totalYeast += r.getYeast() * r.getQuantityOrdered();
                totalFlour += r.getFlour() * r.getQuantityOrdered();
                totalSugar += r.getSugar() * r.getQuantityOrdered();
                totalEggs += r.getEggs() * r.getQuantityOrdered();
                totalButter += r.getButter() * r.getQuantityOrdered();
            }
        }

        if (!anyOrders) {
            return "No breads have been ordered.\n";
        }

        result += "\nYou will need a total of:\n";

        if (totalYeast > 0) { result += (int)totalYeast + " grams of yeast\n"; }
        if (totalFlour > 0) { result += (int)totalFlour + " grams of flour\n"; }
        if (totalSugar > 0) { result += (int)totalSugar + " grams of sugar\n"; }
        if (totalEggs > 0) { result += (int)totalEggs + " egg(s)\n"; }
        if (totalButter > 0) { result += (int)totalButter + " grams of butter\n"; }

        return result;
    }

    /**
     * Saves the shopping list to shoppinglist.txt
     * 
     * @param filename file to save to
     * @throws IOException if writing fails
     */
    public void saveShoppingListToFile(String filename) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter(filename));
        out.print(buildShoppingListString());
        out.close();
    }
}
