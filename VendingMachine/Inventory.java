package VendingMachine;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<String, Item> productMap = new HashMap<>();
    private Map<String, Integer> productQuantity = new HashMap<>();

    public void addItem(String id, String name, int price, int quantity) {
        this.productMap.put(id, new Item(id, name, price));
        this.productQuantity.put(id, quantity);
    }

    public Item getItem(String productId) {
        return productMap.get(productId);
    }

    public void reduceQuantity(String productId) {
        this.productQuantity.put(productId, productQuantity.get(productId)-1);
    }


    public void updateProductQuantity(String productId, int quantity) {
        this.productQuantity.put(productId, productQuantity.get(productId)+quantity);
    }
   
    public boolean isProductAvailable(String productId) {
        return productQuantity.getOrDefault(productId, 0) > 0;
    }

}
