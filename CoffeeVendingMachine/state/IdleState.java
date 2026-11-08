package CoffeeVendingMachine.state;

import CoffeeVendingMachine.CoffeeVendingMachine;
import CoffeeVendingMachine.Coffee.Coffee;

public class IdleState implements CoffeeVendingState {
    CoffeeVendingMachine coffeeVendingMachine;

    public IdleState(CoffeeVendingMachine coffeeVendingMachine) {
        this.coffeeVendingMachine = coffeeVendingMachine;
    }

    @Override
    public void selectCoffee(Coffee coffee) {
        this.coffeeVendingMachine.setCoffee(coffee);
        this.coffeeVendingMachine.updateState(new CoffeeSelectedState(coffeeVendingMachine));

        System.out.println(coffee.getCoffeeType() + " selected. Price " + coffee.getPrice());
    }

    @Override
    public void makePayment(int amount) {
        System.out.println("Please select coffee type first");
    }

    @Override
    public void dispenseCoffee() {
        System.out.println("Please select coffee type first");
    }

    @Override
    public void cancel() {
        this.coffeeVendingMachine.reset();
        System.out.println("Transaction cancelled");
    }
}
