package CoffeeVendingMachine.state;

import java.util.Map;

import CoffeeVendingMachine.CoffeeVendingMachine;
import CoffeeVendingMachine.InventoryStore;
import CoffeeVendingMachine.Coffee.Coffee;
import CoffeeVendingMachine.enums.Ingrediant;


public class PaidState implements CoffeeVendingState {
    CoffeeVendingMachine coffeeVendingMachine;

    PaidState(CoffeeVendingMachine coffeeVendingMachine) {
        this.coffeeVendingMachine = coffeeVendingMachine;
    }


    public void selectCoffee(Coffee coffee) {
        System.out.println("Coffee already selected. Please press cancel if you want to choose another coffee type");
    }

    public void makePayment(int amount) {
        System.out.println("Payment already received");
    }

    public void dispenseCoffee() {
        Map<Ingrediant, Integer> ingrediants = this.coffeeVendingMachine.getCoffee().getIngrediants();
        InventoryStore invetoryStore = coffeeVendingMachine.invetoryStore;
        if (!invetoryStore.isAllIngrediantsAvailable(ingrediants)) {
            this.coffeeVendingMachine.updateState(new OutOfIngredientsState(this.coffeeVendingMachine));
            System.out.println("Sorry, Out of Ingrediants");
            this.cancel();
            return ;
        }
        invetoryStore.reduceQuantity(ingrediants);
        System.out.println("preparing coffee"); // replace with coffee.prepare();

        int change = this.coffeeVendingMachine.getMoneyInserted() - this.coffeeVendingMachine.getCoffee().getPrice();
        if (change > 0) {
            this.coffeeVendingMachine.refund(change);
        }
        
        this.coffeeVendingMachine.reset();
        this.coffeeVendingMachine.updateState(new IdleState(this.coffeeVendingMachine));
    }

    public void cancel() {
        this.coffeeVendingMachine.refund();
        this.coffeeVendingMachine.reset();
        System.out.println("Transaction cancelled you will get your refund soon");
    }
}
