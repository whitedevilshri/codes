import java.util.*;

/**
 * Vehicle Classification System
 * Reimplemented from Prolog: classifies vehicles using facts and rules.
 * Categories: land/water/air, passenger/goods, two/four/heavy-wheeler, petrol/diesel, public_transport
 */
public class VehicleClassification {

    // Facts: properties of each vehicle
    static final Map<String, Integer> WHEELS  = new HashMap<>();
    static final Map<String, String>  ENGINE  = new HashMap<>();
    static final Map<String, String>  MEDIUM  = new HashMap<>();
    static final Map<String, String>  USAGE   = new HashMap<>();

    static {
        WHEELS.put("car", 4);   WHEELS.put("bus", 6);
        WHEELS.put("bike", 2);  WHEELS.put("truck", 8);

        ENGINE.put("car", "petrol");  ENGINE.put("bus", "diesel");
        ENGINE.put("bike", "petrol"); ENGINE.put("truck", "diesel");
        ENGINE.put("boat", "diesel"); ENGINE.put("airplane", "jet");

        MEDIUM.put("car", "land");  MEDIUM.put("bus", "land");
        MEDIUM.put("bike", "land"); MEDIUM.put("truck", "land");
        MEDIUM.put("boat", "water"); MEDIUM.put("airplane", "air");

        USAGE.put("car", "passenger");  USAGE.put("bus", "passenger");
        USAGE.put("bike", "passenger"); USAGE.put("truck", "goods");
        USAGE.put("boat", "goods");     USAGE.put("airplane", "passenger");
    }

    // --- Classification rules (mirror Prolog rules) ---

    static boolean isLandVehicle(String x)      { return "land".equals(MEDIUM.get(x)); }
    static boolean isWaterVehicle(String x)     { return "water".equals(MEDIUM.get(x)); }
    static boolean isAirVehicle(String x)       { return "air".equals(MEDIUM.get(x)); }
    static boolean isPassengerVehicle(String x) { return "passenger".equals(USAGE.get(x)); }
    static boolean isGoodsVehicle(String x)     { return "goods".equals(USAGE.get(x)); }
    static boolean isTwoWheeler(String x)       { return Integer.valueOf(2).equals(WHEELS.get(x)); }
    static boolean isFourWheeler(String x)      { return Integer.valueOf(4).equals(WHEELS.get(x)); }
    static boolean isHeavyVehicle(String x)     { return WHEELS.containsKey(x) && WHEELS.get(x) >= 6; }
    static boolean isPetrolVehicle(String x)    { return "petrol".equals(ENGINE.get(x)); }
    static boolean isDieselVehicle(String x)    { return "diesel".equals(ENGINE.get(x)); }
    /** Public transport: heavy passenger vehicle */
    static boolean isPublicTransport(String x)  { return isPassengerVehicle(x) && isHeavyVehicle(x); }

    static void classify(String vehicle) {
        System.out.println("\nClassification for: " + vehicle);
        System.out.println("  Medium:           " + MEDIUM.getOrDefault(vehicle, "unknown"));
        System.out.println("  Engine:           " + ENGINE.getOrDefault(vehicle, "unknown"));
        System.out.println("  Usage:            " + USAGE.getOrDefault(vehicle, "unknown"));
        System.out.println("  Land vehicle:     " + isLandVehicle(vehicle));
        System.out.println("  Water vehicle:    " + isWaterVehicle(vehicle));
        System.out.println("  Air vehicle:      " + isAirVehicle(vehicle));
        System.out.println("  Passenger:        " + isPassengerVehicle(vehicle));
        System.out.println("  Goods:            " + isGoodsVehicle(vehicle));
        System.out.println("  Two-wheeler:      " + isTwoWheeler(vehicle));
        System.out.println("  Four-wheeler:     " + isFourWheeler(vehicle));
        System.out.println("  Heavy vehicle:    " + isHeavyVehicle(vehicle));
        System.out.println("  Public transport: " + isPublicTransport(vehicle));
    }

    public static void main(String[] args) {
        System.out.println("=== Vehicle Classification System ===");
        String[] vehicles = {"car", "bus", "bike", "truck", "boat", "airplane"};
        for (String v : vehicles) {
            classify(v);
        }
    }
}
