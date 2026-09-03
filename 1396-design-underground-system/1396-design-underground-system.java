import java.util.HashMap;
import java.util.Map;

class UndergroundSystem {

    // Stores customer ID -> check-in information
    private Map<Integer, CheckIn> checkIns;

    // Stores route -> total travel time and number of trips
    private Map<String, Route> routes;

    // Class to store check-in details
    class CheckIn {
        String station;
        int time;

        CheckIn(String station, int time) {
            this.station = station;
            this.time = time;
        }
    }

    // Class to store route statistics
    class Route {
        long totalTime;
        int count;

        Route() {
            totalTime = 0;
            count = 0;
        }
    }

    public UndergroundSystem() {
        checkIns = new HashMap<>();
        routes = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkIns.put(id, new CheckIn(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckIn checkIn = checkIns.get(id);

        String route = checkIn.station + "#" + stationName;
        int travelTime = t - checkIn.time;

        if (!routes.containsKey(route)) {
            routes.put(route, new Route());
        }

        Route r = routes.get(route);
        r.totalTime += travelTime;
        r.count++;

        // Customer is no longer inside the system
        checkIns.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String route = startStation + "#" + endStation;

        Route r = routes.get(route);

        return (double) r.totalTime / r.count;
    }
}