package CoffeeVendingMachine;

import CoffeeVendingMachine.Coffee.Cappuccino;
import CoffeeVendingMachine.Coffee.Coffee;
import CoffeeVendingMachine.Coffee.Espresso;
import CoffeeVendingMachine.Coffee.Latte;
import CoffeeVendingMachine.enums.CoffeeType;

public class CoffeeFactory {
    
    public static Coffee createCoffee(CoffeeType coffeeType) {
        switch(coffeeType) {
            case ESPRESSO:
                return new Espresso();
            case CAPPUCCINO:
                return new Cappuccino();
            case LATTE:
                return new Latte();
            default:
                throw new IllegalArgumentException("Unsupported coffee type");
        }
    }
}
