package ATM.state;

import ATM.ATMSystem;
import ATM.enums.OperationType;

public interface ATMState {
    void insertCard(ATMSystem atm, String CardNumber);
    void enterPin(ATMSystem atm, String PIN);
    void selectOperationType(ATMSystem atm, OperationType operationType, int... args);
    void ejectCard(ATMSystem atm);
}
