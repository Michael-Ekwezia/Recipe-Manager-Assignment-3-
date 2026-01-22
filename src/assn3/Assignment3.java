/* File name: Assignment3.java
 *Author: Ekwezia Michael Chika, 041178206
 *Course: CST8284 -OOP
 *Assignment 3
 *Date: 11/12/2025
 *Professor: Veda Vasavi Erukulla
 *Class List: Assignment3.java, Recipe.java, RecipeManager.java
 */

package assn3;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Driver class that displays menus, validates input using RegExp, and interacts
 * with the RecipeManager to handle ordering and printing bread recipes.
 *
 * @author Ekwezia Michael Chika
 * @version 21.0.8
 * @since JDK 21
 */
public class Assignment3 {

    /**
     * Entry point of the program. Loads recipes, displays menus,
     * and handles user choices.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        RecipeManager manager = new RecipeManager();

        try {
            manager.loadRecipes("recipelist.txt");
        } catch (Exception e) {
            System.out.println("Could not load recipe file.");
            scan.close();
            return;
        }

        System.out.println("Welcome to Chika's recipe manager.");
        printMenu();

        boolean quit = false;

        while (!quit) {
            System.out.print("Please enter your choice: ");
            String input = scan.nextLine().trim();

            //Uses RegExp to validate input 
            if (!input.matches("\\d+")) {
                System.out.println("Please only type digits.");
                System.out.println("Valid input are digits from 0 to 4.");
                continue;
            }

            int choice = Integer.parseInt(input);

            if (choice == 0) {
                printMenu();
            }
            else if (choice == 1) {
                showRecipes(manager);
            }
            else if (choice == 2) {
                orderBread(manager, scan);
            }
            else if (choice == 3) {
                printList(manager, scan);
            }
            else if (choice == 4) {
                quit = true;
            }
            else {
                System.out.println("Please enter a number from 0 to 4.");
            }
        }

        scan.close();
        System.out.println("Goodbye.");
    }

    /**
     * Displays the available menu options to the user.
     */
    private static void printMenu() {
        System.out.println("1. Show available recipes.");
        System.out.println("2. Create Shopping List.");
        System.out.println("3. Print Shopping List.");
        System.out.println("4. Quit Program.");
        System.out.println("0. to reprint this menu.");
    }

    /**
     * Prints the list of available bread recipes.
     *
     * @param manager the RecipeManager containing recipes
     */
    private static void showRecipes(RecipeManager manager) {
        List<String> names = manager.getRecipeNames();
        for (int i = 0; i < names.size(); i++) {
            System.out.println((i + 1) + ". " + names.get(i));
        }
    }

    /**
     * Allows the user to order bread by selecting bread type and quantity.
     *
     * @param manager the RecipeManager handling recipe data
     * @param scan scanner for user input
     */
    private static void orderBread(RecipeManager manager, Scanner scan) {
   
        System.out.print("Which bread would you like? ");

        String input = scan.nextLine();

       
        if (!input.matches("\\d+")) {
            System.out.println("Please only type digits.");
            System.out.println("Valid input are digits from 0 to 7.");
            return;
        }
        
        //Converts string input to int.
        int bread = Integer.parseInt(input);

        if (bread < 1 || bread > manager.getRecipeCount()) {
            System.out.println("Invalid bread number.");
            return;
        }

        System.out.print("How much of this bread would you like? ");
        String qtyStr = scan.nextLine();

        if (!qtyStr.matches("-?\\d+")) {
            System.out.println("Please only type digits.");
            return;
        }

        int qty = Integer.parseInt(qtyStr);

        if (qty < 0) {
            System.out.println("Cannot order negative bread.");
            return;
        }

        try {
            manager.orderBread(bread, qty);
        } catch (Exception e) {
            System.out.println("Could not order bread.");
        }
    }

    /**
     * Prints the shopping list and allows the user to save it.
     *
     * @param manager the RecipeManager providing the list
     * @param scan scanner for user input
     */

    private static void printList(RecipeManager manager, Scanner scan) {
        String list = manager.buildShoppingListString();
        System.out.println(list);

        System.out.print("Save list? (Y/n): ");
        String ans = scan.nextLine();

        if (ans.equalsIgnoreCase("n")) {
            return;
        }

        try {
            manager.saveShoppingListToFile("shoppinglist.txt");
            System.out.println("Saved to shoppinglist.txt");
        } catch (IOException e) {
            System.out.println("Could not save file.");
        }
    }
}
