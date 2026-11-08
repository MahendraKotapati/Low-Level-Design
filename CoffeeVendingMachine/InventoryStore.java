package CoffeeVendingMachine;

import java.util.HashMap;
import java.util.Map;

import CoffeeVendingMachine.enums.Ingrediant;

public class InventoryStore {
    private Map<Ingrediant, Integer> invetoryMap = new HashMap<>(
        Map.of(Ingrediant.COFFEE_BEANS, 50, Ingrediant.WATER, 500, Ingrediant.MILK, 200, Ingrediant.SUGAR, 100, Ingrediant.CARAMEL_SYRUP, 50)
    );

    public void addItem(Ingrediant inventoryId, int quantity) {
        this.invetoryMap.put(inventoryId, quantity);
    }

    public int getItem(Ingrediant inventoryId) {
        return invetoryMap.get(inventoryId);
    }

    public void reduceQuantity(Map<Ingrediant, Integer> ingrediantMap) {
         for (Map.Entry<Ingrediant, Integer> entry : ingrediantMap.entrySet()) {
            this.invetoryMap.put(entry.getKey(), invetoryMap.get(entry.getKey())- entry.getValue());
        }

        boolean lowStock = this.checkForLowStock();
        if (lowStock) {
            // implement notification service using strategy pattern
            // this.notificationService.sendNotification(email)
        }
    }


    public void updateProductQuantity(Ingrediant inventoryId, int quantity) {
        this.invetoryMap.put(inventoryId, invetoryMap.get(inventoryId)+quantity);
    }
   
    public boolean isAllIngrediantsAvailable(Map<Ingrediant, Integer> ingrediantMap) {

        for (Map.Entry<Ingrediant, Integer> entry : ingrediantMap.entrySet()) {
            if (invetoryMap.getOrDefault(entry.getKey(), 0) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }

    public void printInventory() {
        invetoryMap.forEach((ingrediant, qty) -> {
            System.out.println(ingrediant + ": " + qty);
        });
    }

    public boolean checkForLowStock() {
        return false;
    }

}

