package VendingMachine;

import VendingMachine.state.IdleState;
import VendingMachine.state.VendingMachineState;

/*

Requirements
- The vending machine should support multiple products with different prices and quantities.
- The machine should accept coins and notes of different denominations.
- The machine should dispense the selected product and return change if necessary.
- The machine should keep track of the available products and their quantities.
- The machine should handle multiple transactions concurrently and ensure data consistency.
- The machine should provide an interface for restocking products and collecting money.
- The machine should handle exceptional scenarios, such as insufficient funds or out-of-stock products.




flow : first select product then insert money then dispence product
*/


public class VendingMachineController {
    private final static VendingMachineController INSTANCE = new VendingMachineController();
    private final Inventory inventory = new Inventory();
    private VendingMachineState currentVendingState;
    private int totalMoney;
    private int currentAmount;
    private String selectedProductId;

    public VendingMachineController() {
        this.totalMoney = 0;
        this.currentAmount = 0;
        this.currentVendingState = new IdleState(this);
    }

    public static VendingMachineController getInstance() {
        return INSTANCE;
    }

    /*  public facing methods start */
    public void insertCoin(Coin coin) {
        this.currentVendingState.insertCoin(coin);
    }

    public void selectProduct(String productId) {
        this.currentVendingState.selectProduct(productId);
    }

    public void dispenseProduct() {
        this.currentVendingState.dispenseProduct();
    }

    /*  public facing methods end */

    public void updateVendingState(VendingMachineState vendingState) {
        this.currentVendingState = vendingState;
    }

    public void addCurrentAmount(int amount) {
        this.currentAmount += amount;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setSelectedProductId(String productId) {
        this.selectedProductId = productId;
    }

    public void dispense() {
        if (getCurrentAmount() >= getSelectedItem().getPrice()) {
                this.inventory.reduceQuantity(selectedProductId);
                this.currentAmount = currentAmount - getSelectedItem().getPrice();
                this.totalMoney += getSelectedItem().getPrice(); // total money in vending machine over all the transactions happened till now
                if (currentAmount > 0) {
                    System.out.println("Returning balance: " + currentAmount);
                }
                this.reset();
        }
    }

    public void reset() {
        this.currentAmount = 0;
        this.selectedProductId = null;
        this.updateVendingState(new IdleState(this));
    }

    public void refund() {
        System.out.println("Refunding amount: " + currentAmount);
        this.reset();
    }


    // admin side methods
    public void restockQuantity(String productId, int quantity) {
        inventory.updateProductQuantity(productId, quantity);
    }

    public int collectMoney() {
        System.out.println("Collecting all money in machine: " + totalMoney);
        return totalMoney;
    }


    // helper methods
    public boolean isProductAvailable(String productId) {
        return inventory.isProductAvailable(productId);
    }


    // inventory related methods
    public void addProduct(String id, String name, int price, int quantity) {
        inventory.addItem(id, name, price, quantity);
    }

    public Item getSelectedItem() {
        return inventory.getItem(selectedProductId);
    }
}