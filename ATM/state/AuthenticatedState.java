package ATM.state;

import ATM.ATMSystem;
import ATM.enums.OperationType;

public class AuthenticatedState implements ATMState {

    @Override
    public void insertCard(ATMSystem atm, String CardNumber) {
        System.out.println("Error: Card already inserted.");
    }

    @Override
    public void enterPin(ATMSystem atm, String PIN) {
        System.out.println("Error: Already entered the PIN, please proceed to select operation type");
    }

    @Override
    public void selectOperationType(ATMSystem atm, OperationType operationType, int... args) {
        switch(operationType) {
            case CHECK_BALANCE:
                atm.getBalance();
                break;
            case DEPOSIT_AMOUNT:
                if (args.length == 0 || args[0]<= 0) {
                    System.out.println("Error: Invalid witdrawl ampunt");
                    break;
                }
                int depositAmount = args[0];
                System.out.println("Processing deposit for $" + depositAmount);
                atm.depositAmount(depositAmount);
                break;
            case WITHDRAW_CASH:
                if (args.length == 0 || args[0] <= 0) {
                    System.out.println("ErrorL Invalid amount");
                    break;
                }
                double accountBalance = atm.getBalance();
                if (accountBalance < args[0]) {
                    System.out.println("Error: Insufficient Balance");
                    break;
                }
                System.out.println("Processing withdraw request for $" + args[0]);
                atm.withdrawAmount(args[0]);
                break;
        }
        ejectCard(atm);
    }

    @Override
    public void ejectCard(ATMSystem atm) {
        System.out.println("Ejected card successfully. Thank you");
        atm.ejectCard();
    }
}
