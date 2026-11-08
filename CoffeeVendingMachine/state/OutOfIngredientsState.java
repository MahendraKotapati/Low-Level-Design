package CoffeeVendingMachine.state;

import CoffeeVendingMachine.CoffeeVendingMachine;
import CoffeeVendingMachine.Coffee.Coffee;

public class OutOfIngredientsState implements CoffeeVendingState {
    CoffeeVendingMachine coffeeVendingMachine;

    OutOfIngredientsState(CoffeeVendingMachine coffeeVendingMachine) {
        this.coffeeVendingMachine = coffeeVendingMachine;
    }


    public void selectCoffee(Coffee coffee) {
        System.out.println("Invalid option");
    }

    public void makePayment(int amount) {
        System.out.println("Invalid option");
    }

    public void dispenseCoffee() {
        System.out.println("out of ingredients");
    }

    public void cancel() {
        this.coffeeVendingMachine.refund();
        this.coffeeVendingMachine.reset();
        System.out.println("transaction cancelled, you will receive your refund soon.");
    }
}
