package ATM.entities;

public class Card {
    private String cardNumber;
    private String PIN;

    public Card(String cardNumber, String PIN) {
        this.cardNumber = cardNumber;
        this.PIN = PIN;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public boolean validatePIN(String PIN) {
        return this.PIN.compareTo(PIN) == 0;
    }
}

