// LLD for Parking Lot , verify for correctness
import java.util.Arrays;

enum VEHICLE_TYPE {
    CAR, MOTORCYCLE, TRUCK
}

class Vehicle {
    String id;
    VEHICLE_TYPE vehicleType;

    public Vehicle(String id, VEHICLE_TYPE vehicleType) {
        this.id = id;
        this.vehicleType = vehicleType;
    }
}

class ParkingSpot {
    int id;
    VEHICLE_TYPE vehicleType;
    int level;
    boolean isAvailable;

    public ParkingSpot(int id, VEHICLE_TYPE vehicleType, int level, boolean isAvailable) {

    }
}


class ParkingLotManager {
    ParkingSpot[] parkingSpots;

    ParkingLotManager(ParkingSpot[] parkingSpots) {
        this.parkingSpots = parkingSpots;
    }


    public ParkingSpot getParkingSpot(String level, VEHICLE_TYPE vehicleType) {
        return Arrays.stream(parkingSpots).filter(p -> p.isAvailable && p.level==level && p.vehicleType == vehicleType).findFirst();
    }

    public void parkVehicle(Vehicle vehicle, ParkingSpot parkingSpot) {
        parkingSpot.isAvailable = false;
    }

    public void makeEntry(int level, Vehicle vehicle) {
        ParkingSpot parkingSpot = getParkingSpot(level, vehicle.vehicleType);
        if (parkingSpot == null)
            throw new Error("Unable get parking spot");  
        parkVehicle(vehicle, parkingSpot);
    }

    public void exit() {

    }

    public void getNoOfAvailableParkingSpots() {
        return  Arrays.stream(parkingSpots).filter(p -> p.isAvailable).length();
    }

}

public class ParkingLot {
    public static int main() {

        ParkingSpot[] parkingSpots = new ParkingSpot[2];
        parkingSpots[0] = new ParkingSpot();
        parkingSpots[1] = new ParkingSpot();
         

        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingSpots);
        Vehicle vehicle1= new Vehicle("123", VEHICLE_TYPE.CAR);
        parkingLotManager.makeEntry(1, vehicle1);

        Vehicle vehicle2= new Vehicle("456", VEHICLE_TYPE.TRUCK);
        parkingLotManager.makeEntry(1, vehicle2);

    }
}

 {
    
}
