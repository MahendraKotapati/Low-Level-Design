package CoffeeVendingMachine.state;

import CoffeeVendingMachine.CoffeeVendingMachine;
import CoffeeVendingMachine.Coffee.Coffee;

public class CoffeeSelectedState implements CoffeeVendingState {
    CoffeeVendingMachine coffeeVendingMachine;

    CoffeeSelectedState(CoffeeVendingMachine coffeeVendingMachine) {
        this.coffeeVendingMachine = coffeeVendingMachine;
    }


    public void selectCoffee(Coffee coffee) {
        System.out.println("Coffee already selected. Please press cancel if you want to choose another coffee type");
    }

    public void makePayment(int amount) {
        this.coffeeVendingMachine.setMoneyInserted(this.coffeeVendingMachine.getMoneyInserted()+amount);
        System.out.println("total money so far: " + this.coffeeVendingMachine.getMoneyInserted());

        if (this.coffeeVendingMachine.getMoneyInserted() >= this.coffeeVendingMachine.getCoffee().getPrice()) {
            this.coffeeVendingMachine.updateState(new PaidState(coffeeVendingMachine));
        }
    }

    public void dispenseCoffee() {
        System.out.println("Please make payment to dispence the coffee");
    }

    public void cancel() {
        this.coffeeVendingMachine.reset();
        System.out.println("Transaction cancelled");
    }
}
