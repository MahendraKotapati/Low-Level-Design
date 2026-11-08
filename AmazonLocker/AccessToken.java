package AmazonLocker;

import java.time.Instant; // java.time.Instant represents a single, immutable, and thread-safe point on the timeline in UTC.
import java.util.Random;


// Need separate AccessToken class because each access token has a expiry. 

public class AccessToken {
    String compartmentId;
    Instant expiryAt;
    String tokenCode;

    AccessToken(String compartmentId, Instant expiryAt) {
        this.compartmentId = compartmentId;
        this.expiryAt = expiryAt;
        this.tokenCode =  String.format("%06d", new Random().nextInt(1000000));
    }   

    public String getTokenCode() {
        return tokenCode;
    }

    public boolean isExpired() {
        return (Instant.now()).isAfter(expiryAt); 
    }

}