package VendingMachine;

public class VendingMachineDemo {
    
    public static void main(String[] args) {
        VendingMachineController vendingMachine = VendingMachineController.getInstance();

        // Add products to the inventory
        vendingMachine.addProduct("A1", "Coke", 25, 3);
        vendingMachine.addProduct("A2", "Pepsi", 25, 2);
        vendingMachine.addProduct("B1", "Water", 10, 5);

        // Select a product
        System.out.println("\n--- Step 1: Select an item ---");
        vendingMachine.selectProduct("A1");

        // Insert coins
        System.out.println("\n--- Step 2: Insert coins ---");
        vendingMachine.insertCoin(Coin.DIME); // 10
        vendingMachine.insertCoin(Coin.DIME); // 10
        vendingMachine.insertCoin(Coin.NICKEL); // 5

        // Dispense the product
        System.out.println("\n--- Step 3: Dispense item ---");
        vendingMachine.dispenseProduct(); // Should dispense Coke

        // Select another item
        System.out.println("\n--- Step 4: Select another item ---");
        vendingMachine.selectProduct("B1");

        // Insert more amount
        System.out.println("\n--- Step 5: Insert more than needed ---");
        vendingMachine.insertCoin(Coin.QUARTER); // 25

        // Try to dispense the product
        System.out.println("\n--- Step 6: Dispense and return change ---");
        vendingMachine.dispenseProduct();

        
    }
}
