# Recipe-Manager-Assignment-3-
A Java console application that manages bread recipes, allows users to place bread orders, and generates a shopping list based on ingredient requirements.  This project was created as part of CST8284 – Object-Oriented Programming.

📌 Features

  Load bread recipes from a text file
  
  Display available recipes to the user
  
  Order multiple loaves of different breads
  
  Automatically calculate total ingredients needed
  
  Print and save a shopping list to a file
  
  Input validation using Regular Expressions
  
  Unit testing of core logic using JUnit 4

🧠 Concepts Used

  Object-Oriented Programming (OOP)
  
  Classes and encapsulation
  
  File I/O (reading and writing text files)
  
  Exception handling
  
  Collections (ArrayList)
  
  Regular Expressions
  
  JUnit 4 unit testing
  
  JavaDoc documentation

🗂️ Project Structure
  assn3/
  │── Assignment3.java        // Main driver class
  │── Recipe.java             // Data model for a recipe
  │── RecipeManager.java      // Handles recipes and orders
  │── RecipeManagerTest.java  // JUnit 4 tests
  │── recipelist.txt          // Input recipe file
  │── shoppinglist.txt        // Generated output file

▶️ How to Run

  Open the project in an IDE (Eclipse / IntelliJ / VS Code)
  
  Make sure recipelist.txt is in the project root
  
  Run Assignment3.java

Follow the on-screen menu to order bread and generate a shopping list

🧪 Running Tests

Tests are written using JUnit 4

Run RecipeManagerTest.java to verify core functionality

📄 Sample Recipe File Format
Recipe White Bread
eggs 2
yeast 5
flour 300
sugar 10
butter 20

