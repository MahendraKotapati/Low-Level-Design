package VendingMachine.state;

import VendingMachine.Coin;
import VendingMachine.VendingMachineController;

public class HasMoneyState implements VendingMachineState {

    VendingMachineController controller;

    public HasMoneyState(VendingMachineController controller) {
        this.controller = controller;
    }


    public void insertCoin(Coin coin) {
        System.out.println("Money already inserted");
    }

    public void selectProduct(String productId) {
        System.out.println("Item already selected");
    }

    public void dispenseProduct() {
        controller.updateVendingState(new ProductDispensingState(controller));
        System.out.println("Dispencing the product"); 
        controller.dispense(); 
    }  
    
    public void refund() {
        controller.refund();
        controller.reset();
    }
}