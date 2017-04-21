package labs.lab13;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * EGR 283 B01
 * CitiesRoute.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 4/20/2017
 */
public class CitiesRoute {

    public static void main(String[] args) throws FileNotFoundException {
        new CitiesRoute(22,4,15,6,25,21,7,17,20);
    }

    /**
     * Finds the shortest path between the cities
     * @param vals city numbers to visit
     * @throws FileNotFoundException
     */
    public CitiesRoute(int... vals) throws FileNotFoundException {
        List<City> cities = new ArrayList<>();
        for (int val : vals) {
            cities.add(new City(val));
        }
        for (City city : findPath(cities).route) {
            System.out.println(city);
        }
    }



    public Route findPath(final List<City> citiesToCreateRouteTo) {
        TreeSet<Route> routes = new TreeSet<>(Comparator.comparingInt(Route::getDistance));
        routes.add(new Route(new ArrayList<>(), citiesToCreateRouteTo));
        while (!routes.isEmpty()) {
            Route route = routes.pollFirst();
            if (route.targetCities.isEmpty())
                return route;
            routes.addAll(route.generateExtendedRoutes());
        }
        return null;
    }
}
