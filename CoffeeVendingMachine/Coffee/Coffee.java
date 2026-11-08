package CoffeeVendingMachine.Coffee;

import java.util.Map;

import CoffeeVendingMachine.enums.Ingrediant;

public interface Coffee {

    String getCoffeeType();
    int getPrice();
    Map<Ingrediant, Integer> getIngrediants();

}
