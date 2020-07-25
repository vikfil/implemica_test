import java.util.*;

public class Graph {
    private  final int MAX_TOPS;
    private final double INFINITY = 200000.0;
    private Map<Integer, City> cities;
    //Adjacency List for store nodes and their edges
    private List<DirectedEdge>[] adj;


    public Graph(int vertexes) {
        this.MAX_TOPS = vertexes + 1;
        this.cities = new HashMap<>();
        this.adj = new ArrayList[MAX_TOPS];
        for (int i = 0; i < MAX_TOPS; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public void addEdge(DirectedEdge directedEdge) {
        adj[directedEdge.from()].add(directedEdge);

    }
    public void addCity(Integer number, City city) {
        cities.put(number, city);
    }

    private void resetCitiesVisited() {
        for (City city : cities.values()) {
            city.setVisited(false);
        }
    }

    public void path(String from, String to) {
        City[] pathCities = convertCityNameToObject(from, to);
        City source = pathCities[0];
        City destination = pathCities[1];
        resetCitiesVisited();
        addEdgesToCity();

        //Keeps track of the shortest path we've found so far for every city
        HashMap<City, Double> shortestPath = new HashMap<>();

        //Setting every city's shortest path cost to positive infinity to start
        // except the starting city, whose shortest path cost is 0
        for (City city : cities.values()) {
            if (city.equals(source)) {
                shortestPath.put(source, 0.0);
            }else {
                shortestPath.put(city, INFINITY);
            }
        }
        // Now we go through all the city's we can go to from the starting city
        for (DirectedEdge directedEdge : source.getEdges()) {
            shortestPath.put(cities.get(directedEdge.to()), directedEdge.cost());
        }
        source.setVisited(true);
        /*
        This loop runs as long as there is an unvisited city that we can
         reach from any of the cities we could till then
         */
        while (true) {
            City currentCity = clossestReachableUnvisited(shortestPath);
            /*
             If we haven't reached the end city yet, and there isn't another
             reachable city the path between source and destination doesn't exist
              (they aren't connected)
             */
            if (currentCity == null) {
                System.out.println("There isn't a path between " + source.getCityName() + " and " + destination.getCityName());
                return;
            }
            //If the closest non-visited city is our destination, we print the path cost
            if (currentCity.equals(destination)) {
               System.out.println(source.getCityName() + " and " + destination.getCityName());
               System.out.println("The path cost: " + shortestPath.get(destination) + "\n");
                return;
            }
            currentCity.setVisited(true);

            /*
            Now we go through all the unvisited cities our current city has an edge to
            and check whether its shortest path value is better when going through our
            current city than whatever we had before
             */
            for (DirectedEdge edge : currentCity.getEdges()) {
                for (City city : cities.values()) {
                    if (city.isVisited()) {
                        continue;
                    }
                    if (shortestPath.get(currentCity) + edge.cost() < shortestPath.get(cities.get(edge.to()))) {
                        shortestPath.put(cities.get(edge.to()), shortestPath.get(currentCity) + edge.cost());
                    }
                }
            }
        }
    }

    //Method that evaluates which is the closest city that we can reach
    //and haven't visited before
    private City clossestReachableUnvisited(HashMap<City, Double> shortestPath) {
        double minCost = INFINITY;
        City closestReachableCity = null;
        for (City city : cities.values()) {
            if (city.isVisited()) {
                continue;
            }
            double currentCost = shortestPath.get(city);
            if (currentCost == INFINITY) {
                continue;
            }
            if (currentCost < minCost) {
                minCost = currentCost;
                closestReachableCity = city;
            }
        }
        return closestReachableCity;
    }

    private void addEdgesToCity(){
        for (City city : cities.values()) {
            city.setEdges(adj[city.getCityIndex()]);
        }
    }

    private City[] convertCityNameToObject(String cityFrom, String cityTo) {
        City[] arr = new City[2];
        for (Map.Entry<Integer, City> entry : cities.entrySet()) {
            City city = entry.getValue();
            if (city.getCityName().equals(cityFrom)) {
                arr[0] = city;
            }else if (city.getCityName().equals(cityTo)) {
                arr[1] = city;
            }
        }
        return arr;
    }
}

















































