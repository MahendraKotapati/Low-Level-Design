package VendingMachine.state;

import VendingMachine.Coin;
import VendingMachine.VendingMachineController;

public class ProductSelectedState implements VendingMachineState {
    VendingMachineController controller;

    public ProductSelectedState(VendingMachineController controller) {
        this.controller = controller;
    }

    public void insertCoin(Coin coin) {
        controller.addCurrentAmount(coin.getValue());

        if (controller.getSelectedItem().getPrice() > controller.getCurrentAmount()) {
            System.out.println("Amount Insufficient");
            return ;
        } else {
            controller.updateVendingState(new HasMoneyState(controller));
        }
    }

    public void selectProduct(String productId) {
        System.out.println("Item already selected");
    }

    public void dispenseProduct() {
        System.out.println("Please insert money to dispence the product");  
    }  
    
    public void refund() {
        controller.reset();
        System.out.println("No money to refund cancelling the transaction");
    }
}
