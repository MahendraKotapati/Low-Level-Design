package ATM;

import ATM.ChainOfResponsibility.DispenseChain;
import ATM.ChainOfResponsibility.NoteDispenser100;
import ATM.ChainOfResponsibility.NoteDispenser20;
import ATM.ChainOfResponsibility.NoteDispenser50;
import ATM.entities.Card;
import ATM.enums.OperationType;
import ATM.state.ATMState;
import ATM.state.IdleState;

/* 

Requirements
- The ATM system should support basic operations such as balance inquiry, cash withdrawal, and cash deposit.
- Users should be able to authenticate themselves using a card and a PIN (Personal Identification Number).
- The system should interact with a bank's backend system to validate user accounts and perform transactions.
- The ATM should have a cash dispenser to dispense cash to users.
- The system should handle concurrent access and ensure data consistency.
- The ATM should have a user-friendly interface for users to interact with.

*/




public class ATMSystem {
    private final static ATMSystem instance = new ATMSystem();
    BankServerice bankServerice;
    ATMState atmState;
    Card currentCard;
    private DispenseChain c1, c2, c3;


    private ATMSystem() {
        this.bankServerice = new BankServerice();
        this.atmState = new IdleState();

        c1 = new NoteDispenser100(10); // 10 x $100 notes
        c2 = new NoteDispenser50(20); // 20 x $50 notes
        c3 = new NoteDispenser20(30); // 30 x $20 notes

        c1.setNext(c2);
        c2.setNext(c3);
    }

    static ATMSystem getInstance() {
        return instance;
    }

    public void insertCard(String cardNumber) {
        this.atmState.insertCard(this, cardNumber);
    }

    public void enterPin(String PIN) {
        this.atmState.enterPin(instance, PIN);
    }

    public void selectOperationType(OperationType operationType, int... args) {
        this.atmState.selectOperationType(this, operationType, args);
    }

    public void ejectCard() {
        this.setCurrentCard(null);
        this.updateState(new IdleState());
    }



    public boolean validatePin(String PIN) {
        return this.currentCard.validatePIN(PIN);
    }

    public double getBalance() {
        double balance = this.bankServerice.getBalance(this.currentCard);
        System.out.println("Balance: " + balance);
        return balance;
    }

    public void withdrawAmount(int withdrawAmount) {
        if (!c1.canDispense(withdrawAmount)) {
            // throw new Exception("Error: Insufficient cash");
            return;
        }

        this.bankServerice.withdrawAmount(this.currentCard, withdrawAmount);
        c1.dispense(withdrawAmount); 
    }

    public void depositAmount(int depositAmount) {
        this.bankServerice.depositAmount(this.currentCard, depositAmount);
    }

    // helper methods
    public Card getCard(String cardNumber) {
        return this.bankServerice.getCard(cardNumber);
    }

    public void updateState(ATMState atmState) {
        this.atmState = atmState;
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }

    public Card getCurrentCard() {
        return this.currentCard;
    }
}
