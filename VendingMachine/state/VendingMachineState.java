package VendingMachine.state;

import VendingMachine.Coin;

public interface VendingMachineState {

    void insertCoin(Coin coin);
    void selectProduct(String productId);
    void dispenseProduct();
    void refund();
}