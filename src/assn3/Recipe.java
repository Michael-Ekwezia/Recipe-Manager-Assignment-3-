package assn3;

/**
 * A simple data class that stores a bread recipe: name and the amount of each
 * ingredient required for one loaf. Also holds the quantity ordered (loaves).
 *
 * All amounts are stored as double (grams or count for eggs/yeast).
 */
public class Recipe {
	
	 /** The name of the bread. */
    private String name;
    
    /** Amount of eggs required for one loaf. */
    private double eggs;
    
    /** Amount of yeast required for one loaf. */
    private double yeast;

    /** Amount of flour required for one loaf (grams). */
    private double flour;
    
    /** Amount of sugar required for one loaf (grams). */
    private double sugar;
    
    /** Amount of butter required for one loaf (grams). */
    private double butter;
    
    /** How many loaves of this bread have been ordered. */
    private int quantityOrdered;

    /**
     * Default constructor.
     */
    public Recipe() {
        this.name = "";
        this.quantityOrdered = 0;
    }
    
    /**
     * Constructor.
     *
     * @param name recipe name
     * @param eggs amount of eggs
     * @param yeast amount of yeast
     * @param flour amount of flour
     * @param sugar amount of sugar
     * @param butter amount of butter
     */
    public Recipe(String name, double eggs, double yeast, double flour, double sugar, double butter) {
        this.name = name;
        this.eggs = eggs;
        this.yeast = yeast;
        this.flour = flour;
        this.sugar = sugar;
        this.butter = butter;
        this.quantityOrdered = 0;
    }
    
    /**
     * Returns the recipe name.
     *
     * @return name of the recipe
     */
    public String getName() {
    	return this.name; 
    }
    
    /**
     * Set the recipe name.
     *
     * @param name the recipe name to set
     */
    public void setName(String name) {
    	this.name = name; 
    }

    /**
     * Returns eggs required for one loaf.
     *
     * @return eggs amount
     */

    public double getEggs() {
    	return this.eggs; 
    	}

    /**
     * Set eggs required for one loaf.
     *
     * @param eggs the eggs amount to set
     */
    public void setEggs(double eggs) {
    	this.eggs = eggs; 
    }

    /**
     * Returns yeast required for one loaf.
     *
     * @return yeast amount
     */
    public double getYeast() { 
    	return this.yeast; 
    }

    /**
     * Set yeast required for one loaf.
     *
     * @param yeast the yeast amount to set
     */
    public void setYeast(double yeast) { 
    	this.yeast = yeast; 
    }

    /**
     * Returns flour required for one loaf.
     *
     * @return flour amount
     */
    public double getFlour() { 
    	return this.flour; 
    }
    /**
     * Set flour required for one loaf.
     *
     * @param flour the flour amount to set
     */    
    public void setFlour(double flour) { 
    	this.flour = flour; 
    }

    /**
     * Returns sugar required for one loaf.
     *
     * @return sugar amount
     */
    public double getSugar() { 
    	return this.sugar; 
    }

    /**
     * Set sugar required for one loaf.
     *
     * @param sugar the sugar amount to set
     */
    public void setSugar(double sugar) { 
    	this.sugar = sugar; 
    }

    /**
     * Returns butter required for one loaf.
     *
     * @return butter amount
     */
    public double getButter() { 
    	return this.butter; 
    }

    /**
     * Set butter required for one loaf.
     *
     * @param butter the butter amount to set
     */
    public void setButter(double butter) { 
    	this.butter = butter; 
    }
    
    /**
     * Returns the ordered quantity for this recipe.
     *
     * @return quantity ordered (loaves)
     */
    public int getQuantityOrdered() { 
    	return this.quantityOrdered; 
    }
    /** 
     * Set the ordered quantity for this recipe.
     * 
     * @param quantityOrdered the amount of loaves to set
     */
    public void setQuantityOrdered(int quantityOrdered) { 
    	this.quantityOrdered = quantityOrdered; 
    }

    /**
     * Adds to the ordered quantity.
     *
     * @param amount quantity to add
     * @throws IllegalArgumentException if amount is negative
     * 
     */
    public void addToQuantityOrdered(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantityOrdered += amount;
    }

    /**
     * Resets ordered quantity to zero.
     */
    public void resetQuantityOrdered() {
        this.quantityOrdered = 0;
    }
}
