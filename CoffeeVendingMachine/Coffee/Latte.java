package CoffeeVendingMachine.Coffee;

import java.util.Map;

import CoffeeVendingMachine.enums.Ingrediant;

public class Latte implements Coffee {

    @Override
    public String getCoffeeType() {
        return "Latte";
    }

    @Override
    public int getPrice() {
        return 220;
    }

    @Override
    public Map<Ingrediant, Integer> getIngrediants() {
        return Map.of(Ingrediant.COFFEE_BEANS, 7, Ingrediant.WATER, 30, Ingrediant.MILK, 150);
    }
    
}
