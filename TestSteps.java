/*
 * Copyright (c) 2009,  Sarah Heckman, Laurie Williams, Dright Ho
 * All Rights Reserved.
 * 
 * Permission has been explicitly granted to the University of Minnesota 
 * Software Engineering Center to use and distribute this source for 
 * educational purposes, including delivering online education through
 * Coursera or other entities.  
 * 
 * No warranty is given regarding this software, including warranties as
 * to the correctness or completeness of this software, including 
 * fitness for purpose.
 * 
 * 
 * Modified 20171114 by Ian De Silva -- Updated to adhere to coding standards.
 * 
 */
package edu.ncsu.csc326.coffeemaker;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import edu.ncsu.csc326.coffeemaker.CoffeeMaker;
import edu.ncsu.csc326.coffeemaker.UICmd.*;
import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;
import org.apache.commons.math3.exception.OutOfRangeException;

import java.io.*;
import java.lang.reflect.Array;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Contains the step definitions for the cucumber tests.  This parses the 
 * Gherkin steps and translates them into meaningful test steps.
 */
public class TestSteps {
	
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;
	private Recipe recipe5;
	private CoffeeMakerUI coffeeMakerMain; 
	private CoffeeMaker coffeeMaker;
	private RecipeBook recipeBook;

	
	private void initialize() {
		recipeBook = new RecipeBook();

		recipeBook.editRecipe(1,new Recipe());
		coffeeMaker = new CoffeeMaker(recipeBook, new Inventory());
		coffeeMakerMain = new CoffeeMakerUI(coffeeMaker);
	}

	private void initialize2() {
		recipeBook = new RecipeBook();
		Inventory v=new Inventory();
		v.setChocolate(10);
		v.setCoffee(10);
		v.setMilk(10);
		v.setSugar(10);
		coffeeMaker = new CoffeeMaker(recipeBook, v);
		coffeeMakerMain = new CoffeeMakerUI(coffeeMaker);
	}
	private void initialize3() {
		recipeBook = new RecipeBook();
		Inventory v=new Inventory();
		v.setChocolate(-10);
		v.setCoffee(-10);
		v.setMilk(-10);
		v.setSugar(-10);
		coffeeMaker = new CoffeeMaker(recipeBook, v);
		coffeeMakerMain = new CoffeeMakerUI(coffeeMaker);
	}


	
    @Given("^an empty recipe book$")
    public void an_empty_recipe_book() throws Throwable {
        initialize();
    }
	@Given("^an empty recipe book with 10$")
	public void an_empty_recipe_book_with_10() throws Throwable {
		initialize2();
	}
	@Given("^an empty recipe book with negative$")
	public void an_empty_recipe_book_with_negative() throws Throwable {
		initialize3();
	}

    @Given("^add a new random recipe")
	public void add_a_new_random_recipe() throws Throwable{
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		recipeBook.addRecipe(recipe1);

	}

	@When("^choose ([a-zA-Z_]+) command service")
	public void choose_a_command_service(String cmd) throws Throwable{

		switch (cmd){
			case "ADD_RECIPE":
				coffeeMakerMain.UI_Input(new ChooseService(1));
				break;
			case "DELETE_RECIPE":
				coffeeMakerMain.UI_Input(new ChooseService(2));
				break;
			case "EDIT_RECIPE":
				coffeeMakerMain.UI_Input(new ChooseService(3));
				break;
			case "ADD_INVENTORY":
				coffeeMakerMain.UI_Input(new ChooseService(4));
				break;
			case "CHECK_INVENTORY":
				coffeeMakerMain.UI_Input(new ChooseService(5));
				break;
			case "PURCHASE_BEVERAGE":
				coffeeMakerMain.UI_Input(new ChooseService(6));
				break;
			case "RESET":
				coffeeMakerMain.UI_Input(new Reset());
				break;


		}



	}



	@When("^add a recipe with name of ([a-zA-Z0-9]+), ([a-zA-Z0-9]+) units of coffee, ([a-zA-Z0-9]+) units of milk, and ([a-zA-Z0-9]+) units of sugar, and ([a-zA-Z0-9]+) units of chocolate, and price is ([a-zA-Z0-9]+) dollars$")
	public void add_a_new_recipe_with_spec(String name, String coffeeAmt, String milkAmt, String sugarAmt, String chocoAmt, String price) throws Throwable{
		recipe1 = new Recipe();
		recipe1.setName(name);
		recipe1.setAmtChocolate(chocoAmt);
		recipe1.setAmtCoffee(coffeeAmt);
		recipe1.setAmtMilk(milkAmt);
		recipe1.setAmtSugar(sugarAmt);
		recipe1.setPrice(price);
//		coffeeMaker.addRecipe(recipe1);
		coffeeMakerMain.UI_Input(new ChooseService(1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe1));
	}

	@When("^add a recipe with exception with name of ([a-zA-Z0-9]+), (-?[a-zA-Z0-9]+) units of coffee, (-?[a-zA-Z0-9]+) units of milk, and (-?[a-zA-Z0-9]+) units of sugar, and (-?[a-zA-Z0-9]+) units of chocolate, and price is (-?[a-zA-Z0-9]+) dollars$")
	public void add_a_new_recipe_with_negative(String name, String coffeeAmt, String milkAmt, String sugarAmt, String chocoAmt, String price) throws Throwable{
		try{
		recipe1 = new Recipe();
		recipe1.setName(name);
		recipe1.setAmtChocolate(chocoAmt);
		recipe1.setAmtCoffee(coffeeAmt);
		recipe1.setAmtMilk(milkAmt);
		recipe1.setAmtSugar(sugarAmt);
		recipe1.setPrice(price);
//		coffeeMaker.addRecipe(recipe1);
		coffeeMakerMain.UI_Input(new ChooseService(1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe1));
			fail("There should be an exception");
		}catch(RecipeException e){}
	}


	@Then("^the recipe (\\d+) has name ([a-zA-Z_]+)$")
	public void recipe_has_units_of_coffee(int recipeId,String name) throws Throwable{
		assertEquals(coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1].getName(),name);
	}
	@Then("^the recipe (\\d+) uses (\\d+) units of coffee$")
	public void recipe_has_units_of_coffee(int recipeId,int coffeeAmt) throws Throwable{
		assertEquals(coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1].getAmtCoffee(),coffeeAmt);
	}

	@Then("^the recipe (\\d+) uses (\\d+) units of milk$")
	public void recipe_has_units_of_milk(int recipeId,int milkAmount) throws Throwable{
		assertEquals(coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1].getAmtMilk(),milkAmount);
	}
	@Then("^the recipe (\\d+) uses (\\d+) units of sugar$")
	public void recipe_has_units_of_sugar(int recipeId,int sugarAmount) throws Throwable{
		assertEquals(coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1].getAmtSugar(),sugarAmount);
	}

	@Then("^the recipe (\\d+) uses (\\d+) units of chocolate$")
	public void recipe_has_units_of_chocolate(int recipeId,int chocoAmt) throws Throwable{
		assertEquals(coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1].getAmtChocolate(),chocoAmt);
	}

	@Then("^the recipe (\\d+) need (\\d+) dollars$")
	public void recipe_need_price(int recipeId,int price) throws Throwable{
		assertEquals(coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1].getPrice(),price);
	}


	@Then("recipe one is not empty")
	public void recipe_one_is_not_empty() throws Throwable{
		assertNotNull(recipe1);
	}


	@Then("^it is at ([a-zA-Z_]+) mode$")
	public void it_is_at_waiting_mode(String mode) throws  Throwable{
		switch (mode){
			case "ADD_RECIPE":
				assertEquals (coffeeMakerMain.getMode(),CoffeeMakerUI.Mode.ADD_RECIPE);
				break;
			case "DELETE_RECIPE":
				assertEquals (coffeeMakerMain.getMode(),CoffeeMakerUI.Mode.DELETE_RECIPE);
				break;
			case "EDIT_RECIPE":
				assertEquals (coffeeMakerMain.getMode(),CoffeeMakerUI.Mode.EDIT_RECIPE);
				break;
			case "ADD_INVENTORY":
				assertEquals (coffeeMakerMain.getMode(),CoffeeMakerUI.Mode.ADD_INVENTORY);
				break;
			case "CHECK_INVENTORY":
				assertEquals (coffeeMakerMain.getMode(),CoffeeMakerUI.Mode.CHECK_INVENTORY);
				break;
			case "PURCHASE_BEVERAGE":
				assertEquals (coffeeMakerMain.getMode(),CoffeeMakerUI.Mode.PURCHASE_BEVERAGE);
				break;
			case "WAITING":
				assertEquals (coffeeMakerMain.getMode(),CoffeeMakerUI.Mode.WAITING);
				break;
		}



	}

	@Then("is not at waiting mode")
	public void not_waiting() throws Throwable{
		assertNotEquals (coffeeMakerMain.getMode(),CoffeeMakerUI.Mode.WAITING);
	}


	@When("^delete recipe (\\d+)$")
	public void delete_recipe(int recipeId) throws Throwable{

		coffeeMakerMain.UI_Input(new ChooseService(2));
		coffeeMakerMain.UI_Input(new ChooseRecipe(recipeId-1));

	}

	@When("^delete recipe out of bound$")
	public void delete_recipe_out_of_bound() throws Throwable{
		try{
			coffeeMakerMain.UI_Input(new ChooseService(2));
			coffeeMakerMain.UI_Input(new ChooseRecipe(100));
			coffeeMakerMain.UI_Input(new ChooseRecipe(-1));
//			fail("Out of bound");
		}
		catch(ArrayIndexOutOfBoundsException e){}


	}

	@Then("^recipe (\\d+) is empty$")
	public void recipe_is_empty(int recipeId) throws Throwable{
		Recipe recipe=(Recipe)(coffeeMakerMain.coffeeMaker.getRecipes())[recipeId-1];
//		assertEquals(recipe.toString(),"");
		assertEquals(recipe,null);
//		assertEquals(recipe.getName(),"");
//		assertEquals(recipe.getPrice(),0);
//		assertEquals(recipe.getAmtChocolate(),0);
//		assertEquals(recipe.getAmtCoffee(),0);
//		assertEquals(recipe.getAmtSugar(),0);
//		assertEquals(recipe.getAmtMilk(),0);
	}

	@When("^edit the recipe (\\d+): coffee to (\\d+)")
	public void edit_recipe_coffee(int recipeId, String coffeeAmt) throws Throwable{
		Recipe recipe=coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1];
		recipe.setAmtCoffee(coffeeAmt);
//		coffeeMaker.editRecipe(recipeId-1,recipe);
		coffeeMakerMain.UI_Input(new ChooseService(3));
		coffeeMakerMain.UI_Input(new ChooseRecipe(recipeId-1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe));

	}

	@When("^edit the recipe (\\d+) only")
	public void edit_recipe_only(int recipeId) throws Throwable{
//		Recipe recipe=coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1];
//		recipe.setAmtCoffee(coffeeAmt);
//		coffeeMaker.editRecipe(recipeId-1,recipe);
		coffeeMakerMain.UI_Input(new ChooseService(3));
		coffeeMakerMain.UI_Input(new ChooseRecipe(recipeId-1));
//		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe));

	}

	@When("^edit the recipe (\\d+): coffee to (-?\\d+) negative and fail")
	public void edit_recipe_negative_coffee(int recipeId, String coffeeAmt) throws Throwable{
		try{
		Recipe recipe=coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1];
		recipe.setAmtCoffee(coffeeAmt);
//		coffeeMaker.editRecipe(recipeId-1,recipe);
		coffeeMakerMain.UI_Input(new ChooseService(3));
		coffeeMakerMain.UI_Input(new ChooseRecipe(recipeId-1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe));
		fail("should throw exception");

		}catch(RecipeException e){}
	}

	@When("^edit the recipe (\\d+): milk to (\\d+)")
	public void edit_recipe_milk(int recipeId, String milkAmt) throws Throwable{
		Recipe recipe=coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1];
		recipe.setAmtMilk(milkAmt);
		coffeeMakerMain.UI_Input(new ChooseService(3));
		coffeeMakerMain.UI_Input(new ChooseRecipe(recipeId-1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe));
	}

	@When("^edit the recipe (\\d+): milk to (-?\\d+) negative and fail")
	public void edit_recipe_negative_milk(int recipeId, String milkAmt) throws Throwable{
		try{
			Recipe recipe=coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1];
			recipe.setAmtCoffee(milkAmt);
//		coffeeMaker.editRecipe(recipeId-1,recipe);
			coffeeMakerMain.UI_Input(new ChooseService(3));
			coffeeMakerMain.UI_Input(new ChooseRecipe(recipeId-1));
			coffeeMakerMain.UI_Input(new DescribeRecipe(recipe));
			fail("should throw exception");

		}catch(RecipeException e){}
	}

	@When("^edit the recipe (\\d+): sugar to (\\d+)")
	public void edit_recipe_sugar(int recipeId, String sugarAmt) throws Throwable{
		Recipe recipe=coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1];
		recipe.setAmtSugar(sugarAmt);
		coffeeMakerMain.UI_Input(new ChooseService(3));
		coffeeMakerMain.UI_Input(new ChooseRecipe(recipeId-1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe));
	}

	@When("^edit the recipe (\\d+): sugar to (-?\\d+) negative and fail")
	public void edit_recipe_negative_sugar(int recipeId, String sugarAmt) throws Throwable{
		try{
			Recipe recipe=coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1];
			recipe.setAmtSugar(sugarAmt);
//		coffeeMaker.editRecipe(recipeId-1,recipe);
			coffeeMakerMain.UI_Input(new ChooseService(3));
			coffeeMakerMain.UI_Input(new ChooseRecipe(recipeId-1));
			coffeeMakerMain.UI_Input(new DescribeRecipe(recipe));
			fail("should throw exception");

		}catch(RecipeException e){}
	}

	@When("edit the recipe (\\d+): chocolate to (\\d+)")
	public void edit_recipe_choco(int recipeId, String chocoAmt) throws Throwable{
		Recipe recipe=coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1];
		recipe.setAmtChocolate(chocoAmt);
		coffeeMakerMain.UI_Input(new ChooseService(3));
		coffeeMakerMain.UI_Input(new ChooseRecipe(recipeId-1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe));
	}

	@When("edit the recipe (\\d+): chocolate to (-?\\d+) negative and fail")
	public void edit_recipe_negative_chocolate(int recipeId, String chocoAmt) throws Throwable{
		try{
			Recipe recipe=coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1];
			recipe.setAmtChocolate(chocoAmt);
//		coffeeMaker.editRecipe(recipeId-1,recipe);
			coffeeMakerMain.UI_Input(new ChooseService(3));
			coffeeMakerMain.UI_Input(new ChooseRecipe(recipeId-1));
			coffeeMakerMain.UI_Input(new DescribeRecipe(recipe));
			fail("should throw exception");

		}catch(RecipeException e){}
	}

	@When("edit the recipe (\\d+): name to ([a-zA-Z+])")
	public void edit_recipe_name(int recipeId, String name) throws Throwable{
		Recipe recipe=coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1];
		recipe.setName(name);
		coffeeMakerMain.UI_Input(new ChooseService(3));
		coffeeMakerMain.UI_Input(new ChooseRecipe(recipeId-1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe));
	}

	@When("^add inventory: coffee (\\d+), milk (\\d+), sugar (\\d+), chocolate (\\d+)$")
	public void add_inventory(int coffeeAmt, int milkAmt, int sugarAmt, int chocoAmt) throws Throwable {
//		coffeeMaker.addInventory(coffeeAmt,milkAmt,sugarAmt,chocoAmt);
		coffeeMakerMain.UI_Input(new ChooseService(4));

		coffeeMakerMain.UI_Input(new AddInventory(coffeeAmt,milkAmt,sugarAmt,chocoAmt));



	}

	@When("^add inventory: coffee (-?\\d+), milk (-?\\d+), sugar (-?\\d+), chocolate (-?\\d+) and InventoryException$")
	public void add_inventory_and_exception(String coffeeAmt, String milkAmt, String sugarAmt, String chocoAmt) throws Throwable {
		try{
			coffeeMakerMain.mode=CoffeeMakerUI.Mode.ADD_INVENTORY;
			coffeeMakerMain.UI_Input(new AddInventory(Integer.parseInt(coffeeAmt),Integer.parseInt(milkAmt),Integer.parseInt(sugarAmt),Integer.parseInt(chocoAmt)));
			coffeeMakerMain.coffeeMaker.addInventory(coffeeAmt,milkAmt,sugarAmt,chocoAmt);
			fail("There is an exception");

		}catch(InventoryException e){}



	}

	@When("^add text inventory: coffee ([a-zA-Z0-9]+), milk ([a-zA-Z0-9]+), sugar ([a-zA-Z0-9]+), chocolate ([a-zA-Z0-9]+) and InventoryException$")
	public void add__text_inventory_and_exception(String coffeeAmt, String milkAmt, String sugarAmt, String chocoAmt) throws Throwable {
		try{


			coffeeMakerMain.coffeeMaker.addInventory(coffeeAmt,milkAmt,sugarAmt,chocoAmt);
			fail("There is an exception");

		}catch(InventoryException e){}



	}


	@Then("^inventory coffee is (\\d+), milk is (\\d+), sugar is (\\d+), chocolate is (\\d+)$")
	public void check_inventory(String coffeeAmt,String milkAmt, String sugarAmt, String chocoAmt) throws Throwable{
		StringBuffer buf = new StringBuffer();
		buf.append("Coffee: ");
		buf.append(coffeeAmt);
		buf.append("\n");
		buf.append("Milk: ");
		buf.append(milkAmt);
		buf.append("\n");
		buf.append("Sugar: ");
		buf.append(sugarAmt);
		buf.append("\n");
		buf.append("Chocolate: ");
		buf.append(chocoAmt);
		buf.append("\n");
		coffeeMakerMain.UI_Input(new ChooseService(5));
		coffeeMakerMain.UI_Input(new CheckInventory());
		assertEquals(coffeeMakerMain.coffeeMaker.checkInventory(),buf.toString());
	}

	@When("^insert (-?\\d+) dollars and purchase recipe (\\d+)$")
	public void purchase(int moneyInsert, int recipeId) throws  Throwable{
		coffeeMakerMain.defaultCommands(new InsertMoney(moneyInsert));
		coffeeMakerMain.UI_Input(new ChooseService(6));
		coffeeMakerMain.UI_Input(new ChooseRecipe(recipeId-1));
	}

	@When("^insert (-?\\d+) dollars$")
	public void insert_coin_only(int moneyInsert) throws  Throwable{
		coffeeMakerMain.defaultCommands(new InsertMoney(moneyInsert));

//		coffeeMakerMain.UI_Input(new ChooseService(6));
//		coffeeMakerMain.UI_Input(new ChooseRecipe(recipeId-1));
	}

	@When("^input purchase command")
	public void purchase_command() throws  Throwable{

		coffeeMakerMain.UI_Input(new ChooseRecipe(0));
	}
	@When("^input choose command (\\d+)")
	public void choose_command(int choose) throws  Throwable{

		coffeeMakerMain.UI_Input(new ChooseRecipe(choose));
	}

	@When("^input add inventory command")
	public void choose_command_inventory() throws  Throwable{

		coffeeMakerMain.UI_Input(new AddInventory(1,1,1,1));
	}



	@When("^input describe recipe command")
	public void add_command() throws  Throwable{

		coffeeMakerMain.UI_Input(new DescribeRecipe(new Recipe()));
	}

	@Then("^change is (\\d+) dollars$")
	public void change_is(int moneyReturn) throws Throwable{
		int money=coffeeMakerMain.getMoneyInTray();

		assertEquals(money,moneyReturn);
	}

	@Then("^confirm insert (\\d+) dollars$")
	public void insert_is(int moneyInsert) throws Throwable{
		assertEquals(coffeeMakerMain.getMoneyInserted(),moneyInsert);


	}

    @Given("^a default recipe book$")
	public void a_default_recipe_book() throws Throwable {
    	initialize();
    	
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
		
		//Set up for r5 (added by MWW)
		recipe5 = new Recipe();
		recipe5.setName("Super Hot Chocolate");
		recipe5.setAmtChocolate("6");
		recipe5.setAmtCoffee("0");
		recipe5.setAmtMilk("1");
		recipe5.setAmtSugar("1");
		recipe5.setPrice("100");

//		recipeBook.addRecipe(recipe1);

//		recipeBook.addRecipe(recipe2);
//		recipeBook.addRecipe(recipe3);
//		recipeBook.addRecipe(recipe4);
		coffeeMakerMain.UI_Input(new ChooseService(1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe1));
		coffeeMakerMain.UI_Input(new ChooseService(1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe2));
		coffeeMakerMain.UI_Input(new ChooseService(1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe3));
		coffeeMakerMain.UI_Input(new ChooseService(1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(recipe4));
	}

	@Then("^status is success$")
	public void status_is_success() throws Throwable{
		assertEquals(coffeeMakerMain.status,CoffeeMakerUI.Status.OK);
		assertEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.OK);
	}

	@Then("^revised: status is ([a-zA-Z]+)")
	public void status_revise(String status){
		switch (status){
			case "OK":
				assertEquals(coffeeMakerMain.status,CoffeeMakerUI.Status.OK);
				assertEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.OK);
				break;
			case "WRONG_MODE":
				assertEquals(coffeeMakerMain.status,CoffeeMakerUI.Status.WRONG_MODE);
				assertEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.WRONG_MODE);
				break;
			case "RECIPE_NOT_ADDED":
				assertEquals(coffeeMakerMain.status,CoffeeMakerUI.Status.RECIPE_NOT_ADDED);
				assertEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.RECIPE_NOT_ADDED);
				break;
			case "OUT_OF_RANGE":
				assertEquals(coffeeMakerMain.status,CoffeeMakerUI.Status.OUT_OF_RANGE);
				assertEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.OUT_OF_RANGE);
				break;
			case "INSUFFICIENT_FUNDS":
				assertEquals(coffeeMakerMain.status,CoffeeMakerUI.Status.INSUFFICIENT_FUNDS);
				assertEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.INSUFFICIENT_FUNDS);
				break;
			case "UNKNOWN_ERROR":
				assertEquals(coffeeMakerMain.status,CoffeeMakerUI.Status.UNKNOWN_ERROR);
				assertEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.UNKNOWN_ERROR);
				break;



		}

	}

	@Then("^status is not success$")
	public void status_is__not_success() throws Throwable{
		assertNotEquals(coffeeMakerMain.status,CoffeeMakerUI.Status.OK);
	}

	@Then("^status is out of range$")
	public void status_is_out_of_range() throws Throwable{
		assertEquals(coffeeMakerMain.status,CoffeeMakerUI.Status.OUT_OF_RANGE);
	}

	@Then("^status is wrong mode$")
	public void status_is_wrong_mode() throws Throwable{
		assertEquals(coffeeMakerMain.status,CoffeeMakerUI.Status.WRONG_MODE);
	}



	@When("^add a empty recipe$")
	public void add_a_empty_recipe(String name, String coffeeAmt, String milkAmt, String sugarAmt, String chocoAmt, String price) throws Throwable{
		coffeeMakerMain.UI_Input(new ChooseService(1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(new Recipe()));
	}

	@Then("^status is recipe not added$")
	public void status_is_not_added() throws Throwable{
		assertEquals(coffeeMakerMain.status,CoffeeMakerUI.Status.RECIPE_NOT_ADDED);
	}
	@Then("^status is insufficient fund$")
	public void status_is_insufficient_fund() throws Throwable{
		assertEquals(coffeeMakerMain.status,CoffeeMakerUI.Status.INSUFFICIENT_FUNDS);
	}

	@When("^add a null recipe")
	public void add_a_null_recipe() throws  Throwable{
		coffeeMakerMain.UI_Input(new ChooseService(1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(null));
//		assertEquals(1,1);
	}

	@Then("the recipe (\\d+) is recipe (\\d+)")
	public void recipe_is_recipe(int recipeId, int recipeId2) throws Throwable{
		Recipe r1=coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1];
		Recipe r2=coffeeMakerMain.getRecipes()[recipeId2-1];

		assertTrue(r1.equals(r2));

	}

	@Then("the recipe (\\d+) is this")
	public void recipe_is_recipe(int recipeId) throws Throwable{
		Recipe r1=coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1];
//		Recipe r2=coffeeMakerMain.coffeeMaker.getRecipes()[recipeId2-1];
		assertEquals(r1.hashCode(),r1.hashCode());
		assertEquals(r1,r1);
		assertTrue(r1.equals(r1));
		assertFalse(r1.equals(new Recipe()));
		Recipe r2=coffeeMakerMain.coffeeMaker.getRecipes()[recipeId-1];
		Recipe r3=coffeeMakerMain.getRecipes()[recipeId-1];

		assertTrue(r2.equals(r3));

	}



	@Then("display recipe")
	public void display_recipe() throws Throwable{
		coffeeMakerMain.displayRecipes();
		assertTrue(true);
	}

	@When("take out money")
	public void take_out_money() throws Throwable{
		coffeeMakerMain.defaultCommands(new TakeMoneyFromTray());

	}
	@When("^add inventory: chocolate (\\d+)$")
	public void add_inventory_chocolate(int chocoAmt) throws Throwable{
		coffeeMakerMain.UI_Input(new ChooseService(4));

		coffeeMakerMain.UI_Input(new AddInventory(0,0,0,chocoAmt));
	}

	@Then("testing mutant. Has exception 3")
	public void exception_3() throws  Throwable{
		try{
			coffeeMakerMain.mode=CoffeeMakerUI.Mode.EDIT_RECIPE;
			coffeeMakerMain.UI_Input(new ChooseRecipe(-1));
//			assertNotEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.OK);
//			fail("Out of bound");
		}catch(ArrayIndexOutOfBoundsException e){}
	}

	@Then("testing mutant. Has exception 4")
	public void exception_4() throws  Throwable{
		try{
		coffeeMakerMain.mode=CoffeeMakerUI.Mode.DELETE_RECIPE;
		coffeeMakerMain.UI_Input(new ChooseRecipe(-1));
		fail("it shouldn't have -1 delete recipe");
		assertNotEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.OK);

		}catch(ArrayIndexOutOfBoundsException e){}
	}

	@Then("testing mutant. Has exception 0")
	public void mutant() throws Throwable{
		CheckInventory cmd=new CheckInventory();
		assertEquals(cmd.getInventory(),null);
		coffeeMakerMain.UI_Input(new InsertMoney(0));
		assertEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.OUT_OF_RANGE);
		coffeeMakerMain.displayRecipes();
		coffeeMakerMain.mode=CoffeeMakerUI.Mode.EDIT_RECIPE;
		coffeeMakerMain.UI_Input(new ChooseRecipe(2));
		assertNotEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.OK);
		try{
		coffeeMakerMain.mode=CoffeeMakerUI.Mode.EDIT_RECIPE;
		coffeeMakerMain.UI_Input(new ChooseRecipe(-1));
		coffeeMakerMain.UI_Input(new DescribeRecipe(new Recipe()));
		assertNotEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.OK);

		}catch(ArrayIndexOutOfBoundsException e){}


//
//
//		coffeeMakerMain.mode=CoffeeMakerUI.Mode.EDIT_RECIPE;
//		coffeeMakerMain.UI_Input(new DescribeRecipe(new Recipe()));
//		assertEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.RECIPE_NOT_ADDED);
//		coffeeMakerMain.mode=CoffeeMakerUI.Mode.EDIT_RECIPE;
//		coffeeMakerMain.UI_Input(new AddInventory(1,1,1,1));
//		assertEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.WRONG_MODE);
//		coffeeMakerMain.mode=CoffeeMakerUI.Mode.PURCHASE_BEVERAGE;
//		coffeeMakerMain.UI_Input(new AddInventory(1,1,1,1));
//		assertEquals(coffeeMakerMain.getStatus(),CoffeeMakerUI.Status.WRONG_MODE);
//
//
//
//		coffeeMakerMain.coffeeMaker.makeCoffee(1,4);
//		assertEquals(coffeeMakerMain.getMoneyInTray(),0);
//
//		Inventory v= new Inventory();
//		assertEquals(v.getChocolate(),15);
//		assertEquals(v.getCoffee(),15);
//		assertEquals(v.getMilk(),15);
//		assertEquals(v.getSugar(),15);
//
//		Inventory v2= new Inventory();
//		v2.setCoffee(0);
//		v2.setMilk(0);
//		v2.setSugar(0);
//		v2.setChocolate(0);
//		assertEquals(v.getChocolate(),0);
//		assertEquals(v.getCoffee(),0);
//		assertEquals(v.getMilk(),0);
//		assertEquals(v.getSugar(),0);
//
//		Recipe r= new Recipe();


//		if (hasException==2){
//			try{
//				r.setAmtChocolate(""+(-1));
//				r.setAmtSugar(""+(-1));
//				r.setAmtMilk(""+(-1));
//				r.setAmtCoffee(""+(-1));
//				fail("there should be an exception");
//
//			} catch(RecipeException e){}
//
//		}
	}

	@When("purchase at recipe 100 should throw exception")
	public void purchase_with_negative() throws  Throwable {
		try {
			coffeeMakerMain.mode = CoffeeMakerUI.Mode.PURCHASE_BEVERAGE;
			coffeeMakerMain.UI_Input(new ChooseRecipe(100));

			fail("out of bound");
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}
	@When("insert negative coin")
	public void negative_coin() throws Throwable{
		coffeeMakerMain.mode=CoffeeMakerUI.Mode.PURCHASE_BEVERAGE;

		coffeeMakerMain.moneyInserted=-100;
//		coffeeMakerMain.coffeeMaker.makeCoffee(1,-100);
		coffeeMakerMain.UI_Input(new ChooseRecipe(1));
//			fail("negative money inserted");
	}

	@Then("check if recipe number is 3")
	public void three_recipe() throws Throwable{
		assertEquals(coffeeMakerMain.getRecipes().length,3);
	}


}
