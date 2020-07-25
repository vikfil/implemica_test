import java.util.ArrayList;
import java.util.List;

//City objects represent the actual nodes in graph
public class City {
    private int cityIndex;
    private String cityName;
    private boolean isVisited;
    private List<DirectedEdge> edges;

    public City(int cityIndex, String cityName) {
        this.cityIndex = cityIndex;
        this.cityName = cityName;
        this.edges = new ArrayList<>();
    }

    public int getCityIndex() {
        return cityIndex;
    }

    public String getCityName() {
        return cityName;
    }

    public List<DirectedEdge> getEdges() {
        return edges;
    }

    public void setEdges(List<DirectedEdge> edges) {
        this.edges = edges;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

}
