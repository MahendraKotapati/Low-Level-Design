package CoffeeVendingMachine.state;

import CoffeeVendingMachine.Coffee.Coffee;

public interface CoffeeVendingState {
    void selectCoffee(Coffee coffee);
    void makePayment(int amount);
    void dispenseCoffee();
    void cancel();
}
