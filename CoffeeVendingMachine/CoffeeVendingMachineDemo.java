package CoffeeVendingMachine;

import java.util.List;

import CoffeeVendingMachine.enums.CoffeeType;
import CoffeeVendingMachine.enums.ToppingType;

/*
Requirements
- The coffee vending machine should support different types of coffee, such as espresso, cappuccino, and latte.
- Each type of coffee should have a specific price and recipe (ingredients and their quantities).
- The machine should have a menu to display the available coffee options and their prices.
- Users should be able to select a coffee type and make a payment.
- The machine should dispense the selected coffee and provide change if necessary.
- The machine should track the inventory of ingredients and notify when they are running low.
- The machine should handle multiple user requests concurrently and ensure thread safety.


special features:
- add extra sugar and caramelSyrup Decorators
*/



public class CoffeeVendingMachineDemo {

     public static void main(String[] args) {

        CoffeeVendingMachine machine = CoffeeVendingMachine.getInstance();

    // --- Scenario 1: Successful Purchase of a Latte ---
        System.out.println("\n--- SCENARIO 1: Buy a Latte (Success) ---");
        machine.selectCoffee(CoffeeType.LATTE, List.of());
        machine.makePayment(200);
        machine.makePayment(50); // Total 250, price is 220
        machine.dispenseCoffee();
        machine.invetoryStore.printInventory();

        // --- Scenario 2: Purchase with Insufficient Funds & Cancellation ---
        System.out.println("\n--- SCENARIO 2: Buy Espresso (Insufficient Funds & Cancel) ---");
        machine.selectCoffee(CoffeeType.ESPRESSO, List.of());
        machine.makePayment(100); // Price is 150
        machine.dispenseCoffee(); // Should fail
        machine.cancel(); // Should refund 100
        machine.invetoryStore.printInventory(); // Should be unchanged

        // --- Scenario 3: Attempt to Buy with Insufficient Ingredients ---
        System.out.println("\n--- SCENARIO 3: Buy Cappuccino (Out of Milk) ---");
        machine.invetoryStore.printInventory();
        machine.selectCoffee(CoffeeType.CAPPUCCINO, List.of(ToppingType.CARAMEL_SYRUP, ToppingType.EXTRA_SUGAR));
        machine.makePayment(300);
        machine.dispenseCoffee(); // Should fail and refund
        machine.invetoryStore.printInventory();
    }
    
}


