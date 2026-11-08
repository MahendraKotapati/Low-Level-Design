package CoffeeVendingMachine.Coffee;

import java.util.Map;

import CoffeeVendingMachine.enums.Ingrediant;

public class Espresso implements Coffee {

    @Override
    public String getCoffeeType() {
        return "Espresso";
    }

    @Override
    public int getPrice() {
        return 150;
    }

    @Override
    public Map<Ingrediant, Integer> getIngrediants() {
        return Map.of(Ingrediant.COFFEE_BEANS, 7, Ingrediant.WATER, 30);
    }
    
}
