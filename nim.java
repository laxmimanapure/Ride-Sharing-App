import java.util.*;

// Custom Exception
class InvalidRideTypeException extends Exception {
    public InvalidRideTypeException(String message) {
        super(message);
    }
}

// Abstract class
abstract class Ride {
    private String driverName;
    private String vehicleNumber;
    protected double distance;

    public Ride(String driverName, String vehicleNumber, double distance) {
        this.driverName = driverName;
        this.vehicleNumber = vehicleNumber;
        this.distance = distance;
    }

    // Getters
    public String getDriverName() {
        return driverName;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public double getDistance() {
        return distance;
    }

    // Abstract method
    public abstract double calculateFare();
}

// BikeRide subclass
class BikeRide extends Ride {
    public BikeRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return distance * 10; // ₹10 per km
    }
}

// CarRide subclass
class CarRide extends Ride {
    public CarRide(String driverName, String vehicleNumber, double distance) {
        super(driverName, vehicleNumber, distance);
    }

    @Override
    public double calculateFare() {
        return distance * 20; // ₹20 per km
    }
}

// RideSharingApp
public class RideSharingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Available drivers and vehicles
        String[][] bikeDrivers = {
            {"Ramesh", "MH12AB1234"},
            {"Anil", "MH13XY5678"},
            {"Vikram", "MH14CD9876"}
        };

        String[][] carDrivers = {
            {"Suresh", "MH15EF4567"},
            {"Rajesh", "MH16GH1234"},
            {"Amit", "MH17IJ7890"}
        };

        try {
            // Input
            String rideType = sc.nextLine().trim().toLowerCase();
            double distance = sc.nextDouble();

            if (distance <= 0) {
                System.out.println("Invalid distance. Must be > 0.");
                return;
            }

            Ride ride;
            Random rand = new Random();

            // Assign random driver from pool
            if (rideType.equals("bike")) {
                String[] chosen = bikeDrivers[rand.nextInt(bikeDrivers.length)];
                ride = new BikeRide(chosen[0], chosen[1], distance);
            } else if (rideType.equals("car")) {
                String[] chosen = carDrivers[rand.nextInt(carDrivers.length)];
                ride = new CarRide(chosen[0], chosen[1], distance);
            } else {
                throw new InvalidRideTypeException("Invalid ride type: " + rideType);
            }

            // Output
            System.out.println("Driver: " + ride.getDriverName());
            System.out.println("Vehicle No: " + ride.getVehicleNumber());
            System.out.println("Distance: " + ride.getDistance() + " km");
            System.out.println("Fare: ₹" + ride.calculateFare());

        } catch (InvalidRideTypeException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Invalid input.");
        }
    }
}
