package ATM.state;

import ATM.ATMSystem;
import ATM.enums.OperationType;

public class HasCardState implements ATMState {

    @Override
    public void insertCard(ATMSystem atm, String CardNumber) {
        System.out.println("Error: Card already inserted.");
    }

    @Override
    public void enterPin(ATMSystem atm, String PIN) {
        boolean isValidPin = atm.validatePin(PIN);
        if (isValidPin) {
            atm.updateState(new AuthenticatedState());
        } else {
            System.out.println("Error: Invalid PIN, Please try again");
            return ;
        }
    }

    @Override
    public void selectOperationType(ATMSystem atm, OperationType operationType, int... args) {
        System.out.println("Error: Please enter PIN first to select the transaction");
    }

    @Override
    public void ejectCard(ATMSystem atm) {
        atm.ejectCard();
        System.out.println("Card has been ejected. Thank you");
    }
}
