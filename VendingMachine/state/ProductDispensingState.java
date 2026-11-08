package VendingMachine.state;

import VendingMachine.Coin;
import VendingMachine.VendingMachineController;

public class ProductDispensingState implements VendingMachineState {
    VendingMachineController controller;


    public ProductDispensingState(VendingMachineController controller) {
        this.controller = new VendingMachineController();
    }

    public void insertCoin(Coin coin) {
        System.out.println("Money already inserted");
    }

    public void selectProduct(String productId) {
        System.out.println("Item already selected");
    }

    public void dispenseProduct() {
        System.out.println("Product is already Dispencing");  
    }  
    
    public void refund() {
       System.out.println("Dispencing is in progress. Refund not allowed");  
    }
}