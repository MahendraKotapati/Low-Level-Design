package CoffeeVendingMachine.decorator;

import java.util.HashMap;
import java.util.Map;

import CoffeeVendingMachine.Coffee.Coffee;
import CoffeeVendingMachine.enums.Ingrediant;

public class CaramelSyrupDecorator extends CoffeeDecorator {
    private final int COST = 30;
    private final Map<Ingrediant, Integer> RECEIPE_ADDITION = Map.of(Ingrediant.CARAMEL_SYRUP, 10);
    
    public CaramelSyrupDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getCoffeeType() {
        return this.decoratCoffee.getCoffeeType() + ", Caramel Syrup";
    }

    @Override
    public int getPrice() {
        return this.decoratCoffee.getPrice() + COST;
    }

    @Override
    public Map<Ingrediant, Integer> getIngrediants() {
        Map<Ingrediant, Integer> newRecipe = new HashMap<>(this.decoratCoffee.getIngrediants());
        RECEIPE_ADDITION.forEach((ingredient, qty) -> newRecipe.merge(ingredient, qty, Integer::sum));

        return newRecipe;
    }
}
