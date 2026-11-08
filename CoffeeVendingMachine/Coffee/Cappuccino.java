package CoffeeVendingMachine.Coffee;

import java.util.Map;

import CoffeeVendingMachine.enums.Ingrediant;

public class Cappuccino implements Coffee {

    @Override
    public String getCoffeeType() {
        return "Cappuccino";
    }

    @Override
    public int getPrice() {
        return 250;
    }

    @Override
    public Map<Ingrediant, Integer> getIngrediants() {
        return Map.of(Ingrediant.COFFEE_BEANS, 7, Ingrediant.WATER, 30, Ingrediant.MILK, 100);
    }
    
}
