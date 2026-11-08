package ATM.state;

import ATM.ATMSystem;
import ATM.entities.Card;
import ATM.enums.OperationType;

public class IdleState implements ATMState {

    @Override
    public void insertCard(ATMSystem atm, String cardNumber) {
        Card card = atm.getCard(cardNumber);
        if (card == null) {
            System.out.println("Invalid card");
            return ;
        }
        atm.setCurrentCard(card);
        atm.updateState(new HasCardState());
    }

    @Override
    public void enterPin(ATMSystem atm, String PIN) {
        System.out.println("Error: Please insert card first");
    }

    @Override
    public void selectOperationType(ATMSystem atm, OperationType operationType, int... args) {
        System.out.println("Error: Please insert card first");
    }

    @Override
    public void ejectCard(ATMSystem atm) {
        System.out.println("Error: Please insert card first");
        atm.ejectCard();
    }
}
