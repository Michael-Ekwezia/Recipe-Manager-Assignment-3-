/* File name: RecipeManagerTest.java
 * Author: Ekwezia Michael Chika, 041178206
 * Course: CST8284 - OOP
 * Assignment 3
 * Date: 11/12/2025
 * Professor: Veda Vasavi Erukulla
 */

package assn3;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * JUnit 4 test class for RecipeManager.
 * 
 * Tests core functionality including:
 * loading recipes, ordering bread, counting recipes,
 * and building shopping lists.
 *
 * @author Ekwezia Michael Chika
 * @version 21.0.8
 * @since JDK 21
 */
public class RecipeManagerTest {

    /** RecipeManager instance used for testing. */
    private RecipeManager manager;

    /** Temporary recipe file used for tests. */
    private File testFile;

    /**
     * Sets up test data before each test.
     * Creates a temporary recipe file and loads it.
     *
     * @throws IOException if file creation fails
     */
    @Before
    public void setUp() throws IOException {
        manager = new RecipeManager();

        testFile = new File("test_recipes.txt");
        PrintWriter out = new PrintWriter(new FileWriter(testFile));

        out.println("Recipe White Bread");
        out.println("eggs 2");
        out.println("yeast 5");
        out.println("flour 300");
        out.println("sugar 10");
        out.println("butter 20");
        out.println();
        out.println("Recipe Wheat Bread");
        out.println("eggs 1");
        out.println("yeast 4");
        out.println("flour 280");
        out.println("sugar 8");
        out.println("butter 15");

        out.close();

        manager.loadRecipes("test_recipes.txt");
    }

    /**
     * Cleans up test files after each test.
     */
    @After
    public void tearDown() {
        if (testFile.exists()) {
            testFile.delete();
        }
    }

    /**
     * Tests that recipes are loaded correctly
     * and the recipe count is accurate.
     */
    @Test
    public void testLoadRecipes() {
        assertEquals(2, manager.getRecipeCount());
    }

    /**
     * Tests that recipe names are returned correctly.
     */
    @Test
    public void testGetRecipeNames() {
        assertEquals("White Bread", manager.getRecipeNames().get(0));
        assertEquals("Wheat Bread", manager.getRecipeNames().get(1));
    }

    /**
     * Tests ordering bread with a valid index and quantity.
     */
    @Test
    public void testOrderBreadValid() {
        manager.orderBread(1, 2);
        String list = manager.buildShoppingListString();

        assertTrue(list.contains("2 White Bread"));
    }

    /**
     * Tests ordering bread with zero quantity.
     * Should not throw an exception.
     */
    @Test
    public void testOrderBreadZeroQuantity() {
        manager.orderBread(1, 0);
        String list = manager.buildShoppingListString();

        assertEquals("No breads have been ordered.\n", list);
    }

    /**
     * Tests ordering bread with a negative quantity.
     * Expects IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testOrderBreadNegativeQuantity() {
        manager.orderBread(1, -1);
    }

    /**
     * Tests shopping list calculation after multiple orders.
     */
    @Test
    public void testBuildShoppingListTotals() {
        manager.orderBread(1, 2); // White Bread
        manager.orderBread(2, 1); // Wheat Bread

        String list = manager.buildShoppingListString();

        assertTrue(list.contains("2 White Bread"));
        assertTrue(list.contains("1 Wheat Bread"));
        assertTrue(list.contains("grams of flour"));
    }

    /**
     * Tests shopping list output when no bread has been ordered.
     */
    @Test
    public void testBuildShoppingListNoOrders() {
        String list = manager.buildShoppingListString();
        assertEquals("No breads have been ordered.\n", list);
    }
}
