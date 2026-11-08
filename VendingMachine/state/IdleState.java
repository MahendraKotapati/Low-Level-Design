package VendingMachine.state;

import VendingMachine.Coin;
import VendingMachine.VendingMachineController;

public class IdleState implements VendingMachineState {
    VendingMachineController controller;

    public IdleState(VendingMachineController controller) {
        this.controller = controller;
    }

    public void insertCoin(Coin coin) {
        System.out.println("Please select product before inseting money");
    }

    public void selectProduct(String productId) {
        if (!controller.isProductAvailable(productId)) {
            System.out.println("Product not available, please choose another product");
        }

        controller.setSelectedProductId(productId);
        controller.updateVendingState(new ProductSelectedState(controller));

        System.out.println("Product selected: " + productId);
    }

    public void dispenseProduct() {
        System.out.println("No Product Selected");
    }

    public void refund() {
        controller.reset();
        System.out.println("No money to refund");
    }

}