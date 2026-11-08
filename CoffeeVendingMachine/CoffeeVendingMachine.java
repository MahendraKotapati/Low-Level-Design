package CoffeeVendingMachine;

import java.util.List;

import CoffeeVendingMachine.Coffee.Coffee;
import CoffeeVendingMachine.decorator.CaramelSyrupDecorator;
import CoffeeVendingMachine.decorator.ExtraSugarDecorator;
import CoffeeVendingMachine.enums.CoffeeType;
import CoffeeVendingMachine.enums.ToppingType;
import CoffeeVendingMachine.state.CoffeeVendingState;
import CoffeeVendingMachine.state.IdleState;

public class CoffeeVendingMachine {
    private static final CoffeeVendingMachine instance = new CoffeeVendingMachine();
    public InventoryStore invetoryStore; 
    CoffeeVendingState state;
    Coffee coffee;
    int moneyInserted;

    private CoffeeVendingMachine() {
        this.state = new IdleState(this);
        this.invetoryStore = new InventoryStore();
        this.coffee = null;
        this.moneyInserted = 0;
    }

    public static CoffeeVendingMachine getInstance() {
        return instance;
    }

    
    void displayMenu() {

    }

    void selectCoffee(CoffeeType coffeeType, List<ToppingType> toppins) {
        // base coffee type using factory pattern
        Coffee coffee = CoffeeFactory.createCoffee(coffeeType);

        // adding toppings using decorator pattern
        for(ToppingType topping: toppins) {
            switch (topping) {
                case CARAMEL_SYRUP: 
                    coffee = new CaramelSyrupDecorator(coffee);
                        break;
                case EXTRA_SUGAR:
                    coffee = new ExtraSugarDecorator(coffee);
                        break;
            }
        }

        this.state.selectCoffee(coffee);
    }

    void makePayment(int amount) {
        this.state.makePayment(amount);
    }

    void dispenseCoffee() {
        this.state.dispenseCoffee();
    }

    void cancel() {
        this.state.cancel();
    }


    // helper methods
    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public Coffee getCoffee() {
        return this.coffee;
    }

    public void updateState(CoffeeVendingState state) {
        this.state = state;
    }

    public void setMoneyInserted(int amount) {
        this.moneyInserted = amount;
    }

    public int getMoneyInserted() {
        return this.moneyInserted;
    }


    public void reset() {
        this.state = new IdleState(this);
        this.coffee = null;
        this.moneyInserted = 0;
    }

    public void refund() {
        System.out.println("Refunding total amount: " + this.moneyInserted);
    }

    public void refund(int refundAmount) {
        System.out.println("Refunding change amount: " + refundAmount);
    }
    
}
