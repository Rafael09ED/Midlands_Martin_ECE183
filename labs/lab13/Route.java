package labs.lab13;

import java.util.LinkedList;
import java.util.List;

/**
 * EGR 283 B01
 * Route.java
 * Purpose:
 *
 * @author Rafael
 * @version 1.0 4/20/2017
 */
public class Route {
    private int distanceTraveled;
    protected final List<City> route;
    protected final List<City> targetCities;

    public List<Route> generateExtendedRoutes() {
        List<Route> list = new LinkedList<>();
        for (City targetCity : targetCities) {
            List<City> targetList = new LinkedList<>(targetCities),
                    routeList = new LinkedList<>(route);
            targetList.remove(targetCity);
            routeList.add(targetCity);
            list.add(new Route(routeList, targetList));
        }
        return list;
    }

    public Route(List<City> route, List<City> targetCities) {
        this.route = route;

        City prevCity = null;
        for (City city : route) {
            if (prevCity != null)
                distanceTraveled += prevCity.getDistanceTo(city);
            prevCity = city;
        }

        this.targetCities = targetCities;
    }

    public int getDistance() {
        return distanceTraveled;
    }

}