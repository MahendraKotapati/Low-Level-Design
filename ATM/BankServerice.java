package ATM;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ATM.entities.BankAccount;
import ATM.entities.Card;

public class BankServerice {
    private Map<String, BankAccount> bankAccounts;
    private Map<String, Card> cards;
    private Map<Card, BankAccount> cardAccountMap;


    public BankServerice() {
        this.bankAccounts = new ConcurrentHashMap<>();
        this.cards = new ConcurrentHashMap<>();
        this.cardAccountMap = new ConcurrentHashMap<>();

         // Create sample accounts and cards
        BankAccount account1 = createAccount("1234567890", 1000.0);
        Card card1 = createCard("1234-5678-9012-3456", "1234");
        linkCardToAccount(card1, account1);

        BankAccount account2 = createAccount("9876543210", 500.0);
        Card card2 = createCard("9876-5432-1098-7654", "4321");
        linkCardToAccount(card2, account2);
    }

    public BankAccount createAccount(String accountNumber, double initialBalance) {
        BankAccount account = new BankAccount(accountNumber, initialBalance);
        bankAccounts.put(accountNumber, account);
        return account;
    }

    public Card createCard(String cardNumber, String pin) {
        Card card = new Card(cardNumber, pin);
        cards.put(cardNumber, card);
        return card;
    }

    public Card getCard(String cardNumber) {
        return cards.getOrDefault(cardNumber, null);
    }

    public double getBalance(Card card) {
        return this.cardAccountMap.get(card).getBalance();
    }

    public boolean withdrawAmount(Card card, int withdrawAmount) {
        return this.cardAccountMap.get(card).withdraw(withdrawAmount);
    }

    public void depositAmount(Card card, int depositAmount) {
        this.cardAccountMap.get(card).deposit(depositAmount);
    }

    public void linkCardToAccount(Card card, BankAccount account) {
        account.getCards().put(card.getCardNumber(), card);
        cardAccountMap.put(card, account);
    }
}

