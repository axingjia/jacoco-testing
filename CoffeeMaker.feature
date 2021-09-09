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

Scenario: Choose RESET
           Given a default recipe book
              When choose RESET command service
              Then it is at WAITING mode


#wrong mode
Scenario: Choose add and purchse
Given a default recipe book
  When choose ADD_RECIPE command service
  And insert 5 dollars and purchase recipe 1
  Then status is wrong mode


# Scenario: Choose edit and purchase
#Given an empty recipe book
#  When choose EDIT_RECIPE command service
#    And input describe recipe command
#    Then status is wrong mode

# Scenario: Choose edit and add inventory
#Given an empty recipe book
#  When choose EDIT_RECIPE command service
#    And input add inventory command
#    Then status is not success

#Scenario: Choose delete and describe recipe should work
#Given an empty recipe book
#  When choose DELETE_RECIPE command service
#    And input describe recipe command
#    Then status is success

Scenario: Choose inventory and purchase
Given a default recipe book
  When choose ADD_INVENTORY command service
    And insert 5 dollars and purchase recipe 1
    Then status is wrong mode

Scenario: Choose check and purchase
Given a default recipe book
  When choose CHECK_INVENTORY command service
    And insert 5 dollars and purchase recipe 1
    Then status is wrong mode





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

   Scenario: Delete an empty Recipe
      Given an empty recipe book
      And delete recipe 1
      Then status is not success
      And recipe 1 is empty

#Scenario: Delete an empty Recipe out of bound
#      Given an empty recipe book
#      And delete recipe out of bound
#      Then status is not success




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

Scenario: Edit a Recipe that doesn't exist
   Given an empty recipe book
   When edit the recipe 1 only
   Then it is at WAITING mode
   And status is out of range

#Scenario: Edit a Recipe when its null
#   Given an empty recipe book
#      When add a recipe with name of Chacha, 3 units of coffee, 2 units of milk, and 2 units of sugar, and 0 units of chocolate, and price is 4 dollars
#   When edit the recipe 1 coffee 5
#   Then it is at WAITING mode
#   And status is out of range

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



   Scenario: Add Inventory
    Given a default recipe book
   When add inventory: coffee 5, milk 4, sugar 5, chocolate 5
   And add inventory: chocolate 5
   Then inventory coffee is 20, milk is 19, sugar is 20, chocolate is 25
    And it is at WAITING mode

Scenario: Take out money
Given a default recipe book
When take out money
Then it is at WAITING mode

Scenario: test set inventory
Given an empty recipe book with 10
When take out money
Then it is at WAITING mode

Scenario: test set inventory
Given an empty recipe book with negative
When take out money
Then it is at WAITING mode



Scenario: Purchase Beverage
   Given an empty recipe book
   When add a recipe with name of Chacha, 3 units of coffee, 2 units of milk, and 2 units of sugar, and 0 units of chocolate, and price is 4 dollars
      And insert 5 dollars and purchase recipe 1
      Then change is 1 dollars
      And inventory coffee is 12, milk is 13, sugar is 13, chocolate is 15

Scenario: Purchase Beverage
   Given an empty recipe book
   When add a recipe with name of Chacha, 3 units of coffee, 2 units of milk, and 2 units of sugar, and 0 units of chocolate, and price is 4 dollars
      And insert -5 dollars and purchase recipe 1
      Then status is not success



# Add scenarios from the Use Cases here.  These can be Cucumber versions of the unit
# tests that were required for course 1, or can be more direct expressions of the use
# case tests found in the Requirements-coffeemaker.pdf file.

 Scenario: Add Recipe and Status is Success
Given an empty recipe book
   When add a recipe with name of Chacha, 3 units of coffee, 2 units of milk, and 2 units of sugar, and 0 units of chocolate, and price is 4 dollars
   Then status is success

 Scenario: Add 2 Recipe and Status is Success
 Given an empty recipe book
   When add a recipe with name of Chacha, 3 units of coffee, 2 units of milk, and 2 units of sugar, and 0 units of chocolate, and price is 4 dollars
   When add a recipe with name of Chacha, 3 units of coffee, 2 units of milk, and 2 units of sugar, and 0 units of chocolate, and price is 4 dollars
   Then status is recipe not added

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


Scenario: Add Inventory negative choco
    Given a default recipe book
   When add inventory: coffee 5, milk 4, sugar 5, chocolate -5 and InventoryException

 Scenario: Add Inventory negative milk
    Given a default recipe book
   When add inventory: coffee 5, milk -4, sugar 5, chocolate 5 and InventoryException

Scenario: Add Inventory negative coffee
    Given a default recipe book
   When add inventory: coffee -5, milk 4, sugar 5, chocolate 5 and InventoryException

Scenario: Add Inventory negative sugar
    Given a default recipe book
   When add inventory: coffee 5, milk 4, sugar -5, chocolate 5 and InventoryException

Scenario: Add Inventory text choco
    Given a default recipe book
   When add text inventory: coffee 5, milk 4, sugar 5, chocolate asdf and InventoryException

 Scenario: Add Inventory text milk
    Given a default recipe book
   When add text inventory: coffee 5, milk asdf, sugar 5, chocolate 5 and InventoryException

Scenario: Add Inventory text coffee
    Given a default recipe book
   When add text inventory: coffee asdf, milk 4, sugar 5, chocolate 5 and InventoryException

Scenario: Add Inventory text sugar
    Given a default recipe book
   When add text inventory: coffee 5, milk 4, sugar asdf, chocolate asdf and InventoryException


#Scenario: Add Inventory float coffee
#    Given a default recipe book
#   When add inventory: coffee 1.1, milk 4, sugar 5, chocolate 5 and InventoryException

#Scenario: Add Inventory float milk
#    Given a default recipe book
#   When add inventory: coffee 5, milk 1.1, sugar 5, chocolate 5 and InventoryException

#Scenario: Add Inventory float sugar
#    Given a default recipe book
#  When add inventory: coffee 5, milk 4, sugar 1.1, chocolate 5 and InventoryException

#Scenario: Add Inventory float choco
#    Given a default recipe book
#   When add inventory: coffee 5, milk 4, sugar 5, chocolate 1.1 and InventoryException



Scenario: Purchase Beverage until not enough ingredient
   Given an empty recipe book
   When add a recipe with name of Chacha, 20 units of coffee, 20 units of milk, and 20 units of sugar, and 20 units of chocolate, and price is 4 dollars
      And insert 5 dollars and purchase recipe 1
      Then it is at WAITING mode

Scenario: add recipe with negative coffee
   Given an empty recipe book
   When add a recipe with exception with name of Chacha, -20 units of coffee, 20 units of milk, and 20 units of sugar, and 20 units of chocolate, and price is 4 dollars

Scenario: add recipe with negative milk
   Given an empty recipe book
   When add a recipe with exception with name of Chacha, 20 units of coffee, -20 units of milk, and 20 units of sugar, and 20 units of chocolate, and price is 4 dollars

Scenario: add recipe with negative sugar
   Given an empty recipe book
   When add a recipe with exception with name of Chacha, 20 units of coffee, 20 units of milk, and -20 units of sugar, and 20 units of chocolate, and price is 4 dollars


Scenario: add recipe with negative chocolate
   Given an empty recipe book
   When add a recipe with exception with name of Chacha, 20 units of coffee, 20 units of milk, and 20 units of sugar, and -20 units of chocolate, and price is 4 dollars

Scenario: add recipe with negative price
   Given an empty recipe book
   When add a recipe with exception with name of Chacha, 20 units of coffee, 20 units of milk, and 20 units of sugar, and 20 units of chocolate, and price is -4 dollars

Scenario: add recipe with text coffee
   Given an empty recipe book
   When add a recipe with exception with name of Chacha, asdf units of coffee, 20 units of milk, and 20 units of sugar, and 20 units of chocolate, and price is 4 dollars

Scenario: add recipe with text milk
   Given an empty recipe book
   When add a recipe with exception with name of Chacha, 20 units of coffee, asdf units of milk, and 20 units of sugar, and 20 units of chocolate, and price is 4 dollars

Scenario: add recipe with text sugar
   Given an empty recipe book
   When add a recipe with exception with name of Chacha, 20 units of coffee, 20 units of milk, and asdf units of sugar, and 20 units of chocolate, and price is 4 dollars


Scenario: add recipe with text chocolate
   Given an empty recipe book
   When add a recipe with exception with name of Chacha, 20 units of coffee, 20 units of milk, and 20 units of sugar, and asdf units of chocolate, and price is 4 dollars

Scenario: add recipe with text price
   Given an empty recipe book
   When add a recipe with exception with name of Chacha, 20 units of coffee, 20 units of milk, and 20 units of sugar, and 20 units of chocolate, and price is asdf dollars



Scenario: Purchase an empty recipe
Given an empty recipe book
When insert 5 dollars and purchase recipe 1
Then it is at WAITING mode

Scenario: two recipe is this
Given a default recipe book
Then the recipe 1 is this


Scenario: display recipe
Given a default recipe book
Then display recipe

Scenario: display emptyrecipe
Given an empty recipe book
Then display recipe

Scenario: Insert negative coin
   Given an empty recipe book
   When add a recipe with name of Chacha, 20 units of coffee, 20 units of milk, and 20 units of sugar, and 20 units of chocolate, and price is 4 dollars
      And insert -5 dollars
      Then status is out of range

Scenario: Insert negative coin
   Given an empty recipe book
   When add a recipe with name of Chacha, 20 units of coffee, 20 units of milk, and 20 units of sugar, and 20 units of chocolate, and price is 4 dollars
      And insert 5 dollars
      Then confirm insert 5 dollars


Scenario Outline: Change mode
Given an empty recipe book
When choose <mode> command service
Then revised: status is <status>
And it is at <resultMode> mode

Examples:
| mode | status | resultMode |
| ADD_RECIPE | OK | ADD_RECIPE |
| WAITING | OK | WAITING |
| DELETE_RECIPE | OK | DELETE_RECIPE |
| EDIT_RECIPE | OK | EDIT_RECIPE |
| ADD_INVENTORY | OK | ADD_INVENTORY |
| CHECK_INVENTORY | OK |  CHECK_INVENTORY |
| PURCHASE_BEVERAGE | OK |  PURCHASE_BEVERAGE |


Scenario: delete and choose out of range
Given an empty recipe book
When choose DELETE_RECIPE command service
And input choose command 0
Then revised: status is OUT_OF_RANGE

Scenario: sequence add and choose: wrong mode
Given an empty recipe book
When choose ADD_RECIPE command service
And input choose command 0
Then revised: status is WRONG_MODE

#Scenario: Mutant Testing
#Given an empty recipe book
#When add a recipe with name of Chacha, 20 units of coffee, 20 units of milk, and 20 units of sugar, and 20 units of chocolate, and price is 4 dollars
#Then testing mutant. Has exception 0

# Scenario: Mutant Testing has exception
#Given an empty recipe book
#When add a recipe with name of Chacha, 20 units of coffee, 20 units of milk, and 20 units of sugar, and 20 units of chocolate, and price is 4 dollars
#Then testing mutant. Has exception 2

# Scenario: Mutant Testing has exception out of bound
#Given an empty recipe book
#When add a recipe with name of Chacha, 20 units of coffee, 20 units of milk, and 20 units of sugar, and 20 units of chocolate, and price is 4 dollars
#Then testing mutant. Has exception 3



# Scenario: purchase with out of bound
#Given an empty recipe book
#When add a recipe with name of Chacha, 20 units of coffee, 20 units of milk, and 20 units of sugar, and 20 units of chocolate, and price is 4 dollars
#And purchase at recipe 100 should throw exception






