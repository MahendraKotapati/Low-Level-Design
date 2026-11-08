package ATM.ChainOfResponsibility;

abstract public class NoteDispenser implements DispenseChain {
    private DispenseChain next;
    private final int noteValue;
    private int numNotes;

    public NoteDispenser(int noteValue, int numNotes) {
        this.noteValue = noteValue;
        this.numNotes = numNotes;
    }

    @Override
    public void setNext(DispenseChain next) {
        this.next = next;
    }

    @Override
    public void dispense(int amount) {
        if (amount >=noteValue) {
            int numOfNotesToDispense = Math.min(amount/noteValue, numNotes);
            int remainingAmount = amount - numOfNotesToDispense * noteValue;

            if (remainingAmount > 0 && this.next != null) {
                next.dispense(remainingAmount);
            }
        } else if (next != null) {
            this.next.dispense(amount);
        }
    }

    @Override
    public boolean canDispense(int amount) {
        if (amount < 0 )
            return false;

        if (amount == 0) 
            return true;

        if (amount >=noteValue) {
            int numOfNotesToDispense = Math.min(amount/noteValue, numNotes);
            int remainingAmount = amount - numOfNotesToDispense * noteValue;

            if (remainingAmount == 0) 
                return true;

            if (this.next != null) {
                return next.canDispense(remainingAmount);
            }
        } else if (next != null) {
            return this.next.canDispense(amount);
        }

        return false;
    }
}
