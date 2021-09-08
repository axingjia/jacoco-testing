Feature: CoffeeMakerFeature





    Scenario: Waiting State
    Given a default recipe book
       Then it is at WAITING mode

   Scenario: Choose ADD RECIPE
       Given a default recipe book
          When choose ADD_RECIPE command service
          Then it is at ADD_RECIPE mode

    Scenario: Choose EDIT RECIPE
       Given a default recipe book
          When choose EDIT_RECIPE command service
          Then it is at EDIT_RECIPE mode

    Scenario: Choose DELETE_RECIPE
       Given a default recipe book
          When choose DELETE_RECIPE command service
          Then it is at DELETE_RECIPE mode

    Scenario: Choose ADD_INVENTORY
       Given a default recipe book
          When choose ADD_INVENTORY command service
          Then it is at ADD_INVENTORY mode

    Scenario: Choose CHECK_INVENTORY
       Given a default recipe book
          When choose CHECK_INVENTORY command service
          Then it is at CHECK_INVENTORY mode


    Scenario: Choose PURCHASE_BEVERAGE
           Given a default recipe book
              When choose PURCHASE_BEVERAGE command service
              Then it is at PURCHASE_BEVERAGE mode

   Scenario: Add a Recipe
   Given an empty recipe book
   When add a recipe with name of Chacha, 3 units of coffee, 2 units of milk, and 2 units of sugar, and 0 units of chocolate, and price is 4 dollars
   Then the recipe 1 uses 3 units of coffee
   And the recipe 1 uses 2 units of milk
   And the recipe 1 uses 2 units of sugar
   And the recipe 1 uses 0 units of chocolate
   And the recipe 1 need 4 dollars
   And the recipe 1 has name Chacha
   And it is at WAITING mode
   

      #Priority: 2 Story Points: 1

      #A recipe may be deleted from the CoffeeMaker if it exists in the list of recipes in the
      #CoffeeMaker. The recipes are listed by their name. Upon completion, a status message is
      #printed and the Coffee Maker is returned to the waiting state.

   Scenario: Delete a Recipe
   Given an empty recipe book
   When add a recipe with name of Chacha, 3 units of coffee, 2 units of milk, and 2 units of sugar, and 0 units of chocolate, and price is 4 dollars
   And delete recipe 1
   Then it is at WAITING mode
   And recipe 1 is empty



#   Scenario: Delete a Recipe 2
#      Given a default recipe book
#      When delete recipe 2
 #     And delete recipe 3
  #    Then recipe 2 is empty
   #   And recipe 3 is empty
    #  And it is at WAITING mode

#Scenario: Edit a Recipe
      #Priority: 2 Story Points: 1

      #A recipe may be edited in the CoffeeMaker if it exists in the list of recipes in the
      #CoffeeMaker. The recipes are listed by their name. After selecting a recipe to edit, the user
      #will then enter the new recipe information. A recipe name may not be changed. Upon
      #completion, a status message is printed and the Coffee Maker is returned to the waiting
      #state.

   Scenario: Edit a Recipe
   Given a default recipe book
   When edit the recipe 1: coffee to 1
   Then the recipe 1 uses 1 units of coffee
   And it is at WAITING mode





   Scenario: Edit a Recipe 2
      Given a default recipe book
      When edit the recipe 1: coffee to 2
      And edit the recipe 1: sugar to 2
      And edit the recipe 1: milk to 3
      And edit the recipe 1: chocolate to 4
      Then the recipe 1 uses 2 units of coffee
      And the recipe 1 uses 2 units of sugar
      And the recipe 3 uses 3 units of milk
      And the recipe 1 uses 4 units of chocolate
      And it is at WAITING mode


#Scenario: Edit a Recipe coffee to negative
#      Given a default recipe book
#      When edit the recipe 1: coffee to -1 negative and fail

#    Scenario: edit a Recipe sugar to negative
#    Given a default recipe book
#    When edit the recipe 1: sugar to -2 negative and fail

#    Scenario: edit a Recipe milk to negative
#        Given a default recipe book
#        When edit the recipe 1: milk to -2 negative and fail

#Scenario: edit a Recipe chocolate to negative
#    Given a default recipe book
#    When edit the recipe 1: chocolate to -2 negative and fail

   Scenario: Add Inventory
    Given a default recipe book
   When add inventory: coffee 5, milk 4, sugar 5, chocolate 5
   Then inventory coffee is 20, milk is 19, sugar is 20, chocolate is 20
    And it is at WAITING mode





      

Scenario: Purchase Beverage
   Given an empty recipe book
   When add a recipe with name of Chacha, 3 units of coffee, 2 units of milk, and 2 units of sugar, and 0 units of chocolate, and price is 4 dollars
      And insert 5 dollars and purchase recipe 1
      Then change is 1 dollars
      And inventory coffee is 12, milk is 13, sugar is 13, chocolate is 15




# Add scenarios from the Use Cases here.  These can be Cucumber versions of the unit 
# tests that were required for course 1, or can be more direct expressions of the use
# case tests found in the Requirements-coffeemaker.pdf file.

 Scenario: Add Recipe and Status is Success
Given an empty recipe book
   When add a recipe with name of Chacha, 3 units of coffee, 2 units of milk, and 2 units of sugar, and 0 units of chocolate, and price is 4 dollars
   Then status is success

 Scenario: Add Recipe and Status is not added
Given a default recipe book
   When add a recipe with name of Chacha, 3 units of coffee, 2 units of milk, and 2 units of sugar, and 0 units of chocolate, and price is 4 dollars
   Then status is recipe not added

 Scenario: Insufficient Fund
Given a default recipe book
      When insert 25 dollars and purchase recipe 1
      Then it is at WAITING mode
      #Then status is insufficient fund


 Scenario: Insufficient Fund 2
Given an empty recipe book
   When add a recipe with name of Chacha, 3 units of coffee, 2 units of milk, and 2 units of sugar, and 0 units of chocolate, and price is 4 dollars
      And insert 8 dollars and purchase recipe 1
      Then status is success


#Scenario: Purchase Beverage until not enough coffee
#   Given an empty recipe book
#   When add a recipe with name of Chacha, 100 units of coffee, 2 units of milk, and 2 units of sugar, and 0 units of chocolate, and price is 4 dollars
#      And insert 100 dollars and purchase recipe 1
#      Then it is at WAITING mode

#Scenario: Add Inventory negative coffee
#    Given a default recipe book
#   When add inventory: coffee -5, milk 4, sugar 5, chocolate 5 and InventoryException

#Scenario: Add Inventory negative milk
#    Given a default recipe book
#   When add inventory: coffee 5, milk -4, sugar 5, chocolate 5 and InventoryException

# Scenario: Add Inventory negative sugar
#    Given a default recipe book
#   When add inventory: coffee 5, milk 4, sugar -5, chocolate 5 and InventoryException

#Scenario: Add Inventory negative choco
#    Given a default recipe book
#   When add inventory: coffee 5, milk 4, sugar 5, chocolate -5 and InventoryException

#Scenario: Add Inventory text coffee
#    Given a default recipe book
#   When add inventory: coffee 1.1, milk 4, sugar 5, chocolate 5 and InventoryException

#Scenario: Add Inventory text milk
#    Given a default recipe book
#   When add inventory: coffee 5, milk 1.1, sugar 5, chocolate 5 and InventoryException

#Scenario: Add Inventory text sugar
#    Given a default recipe book
#   When add inventory: coffee 5, milk 4, sugar 1.1, chocolate 5 and InventoryException

#Scenario: Add Inventory text choco
#    Given a default recipe book
#   When add inventory: coffee 5, milk 4, sugar 5, chocolate 1.1 and InventoryException



#Scenario: Purchase Beverage until not enough ingredient
#   Given an empty recipe book
#   When add a recipe with name of Chacha, 20 units of coffee, 20 units of milk, and 20 units of sugar, and 20 units of chocolate, and price is 4 dollars
#      And insert 5 dollars and purchase recipe 1
#      Then it is at WAITING mode