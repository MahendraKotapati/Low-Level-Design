package ATM.ChainOfResponsibility;

public interface DispenseChain {
    void setNext(DispenseChain next);
    void dispense(int amount);
    boolean canDispense(int amount);
}
