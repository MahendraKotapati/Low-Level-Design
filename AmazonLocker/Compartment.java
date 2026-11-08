package AmazonLocker;

import java.util.UUID;

public class Compartment {
    String compartmentId;
    Size size;
    boolean isAvailable;

    Compartment(Size size) {
        compartmentId = UUID.randomUUID().toString();
        this.size = size;
        this.isAvailable = true;
    }

    public void markOcuupied() {
        this.open();
        this.isAvailable = false;
    }

    public void markFree() {
        this.open();
        this.isAvailable = true;
    }

    public void open() {

    }
}
