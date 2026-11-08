package CoffeeVendingMachine.decorator;

import java.util.Map;

import CoffeeVendingMachine.Coffee.Coffee;
import CoffeeVendingMachine.enums.Ingrediant;

public class CoffeeDecorator implements Coffee {
    Coffee decoratCoffee;

    CoffeeDecorator(Coffee coffee) {
        this.decoratCoffee = coffee;
    }

    @Override
    public String getCoffeeType() {
        return this.decoratCoffee.getCoffeeType();
    }

    @Override
    public int getPrice() {
        return this.decoratCoffee.getPrice();
    }

    @Override
    public Map<Ingrediant, Integer> getIngrediants() {
        return this.decoratCoffee.getIngrediants();
    }
}
