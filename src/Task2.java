import java.util.*;

public class Task2 {
    public static void main(String[] args) {
        String s = "E:\\implemica_test\\src\\text.txt";
        DocumentParser parser = new DocumentParser();
        parser.parseDocument(s);
        Graph graph = new Graph(parser.getCityAmount());
        Map<String, String> citiesToSearch = parser.getPairCityToSearch();
        Map<Integer, String> citiesIndex = parser.getCityNameWithIndex();
        List<HashMap<Integer, Integer>>[] edges = parser.getDirectEdges();

        //add city to the graph
        for (int i = 1; i <= citiesIndex.keySet().size(); i++) {
            graph.addCity(i, new City(i, citiesIndex.get(i)));
        }

        //add adges to the graph
        for (int j = 1; j < edges.length; j++) {
            for (int k = 0; k < edges[j].size(); k++) {
                HashMap<Integer, Integer> edgesMap = edges[j].get(k);
                Set<Integer> keySet = edges[j].get(k).keySet();
                for (Integer key : keySet) {
                    graph.addEdge(new DirectedEdge(j, key, edgesMap.get(key)));
                }
            }
        }

        //search path
        for (Map.Entry<String, String> entry : citiesToSearch.entrySet()) {
            graph.path(entry.getKey(), entry.getValue());
        }
    }
}
