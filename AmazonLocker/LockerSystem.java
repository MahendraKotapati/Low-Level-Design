package AmazonLocker;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;



// https://www.hellointerview.com/learn/low-level-design/problem-breakdowns/amazon-locker#complete-code-implementation

/*
    Design a locker system like Amazon Locker where delivery drivers can deposit packages and customers can pick them up using a code.
*/

public class LockerSystem {
    private static final LockerSystem INSTANCE = new LockerSystem();
    List<Compartment> compartmentList = new ArrayList<>();
    HashMap<String, AccessToken> accessTokenMap = new HashMap<>();

    private LockerSystem() {
        
    }

    static LockerSystem getInstance() {
        return INSTANCE;
    }

    public void depositPackage(Size packageSize) {
        Compartment compartment = null;
        try {
            compartment = getFirstAvailableCompartment(packageSize);
        } catch(Exception e) {
            System.out.println("Compartment not found for " +  packageSize + " size");
        }

        compartment.markOcuupied();
        AccessToken accessToken = generateAccessToken(compartment.compartmentId);   
        accessTokenMap.put(accessToken.getTokenCode(), accessToken);
        sendAcessTokenToCustomer(accessToken);
    }

    public void pickupPackage(String tokenCode) throws Exception { 
        if (tokenCode == null || tokenCode.isEmpty()) {
            throw new Exception("Invalid access token code");
        }

        AccessToken at = accessTokenMap.get(tokenCode);
        if (at == null) {
            throw new Exception("Invalid access token code");
        }

        if (at.isExpired()) {
            throw new Exception("Access token has expired");
        }

        Compartment compartment = getCompartment(at.compartmentId);
        
        // clear compartment
        compartment.markFree();
        accessTokenMap.remove(at.tokenCode);
    }



    private Compartment getFirstAvailableCompartment(Size packageSize) {
        Optional<Compartment> compartment = compartmentList.stream().filter(c -> c.isAvailable && c.size==packageSize).findFirst();
        return compartment.orElseThrow();
    }

    private AccessToken generateAccessToken(String compartmentId) {
        AccessToken accessToken = new AccessToken(compartmentId, Instant.now().plus(7, ChronoUnit.DAYS));
        return accessToken;
    }

    private Compartment getCompartment(String cId) {
        Optional<Compartment> compartment = compartmentList.stream().filter(c -> c.compartmentId == cId).findFirst();
        return compartment.get();
    }

    private void sendAcessTokenToCustomer(AccessToken accessToken) {
        // send email notification
    }
}


