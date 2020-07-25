//DirectedEdge objects represent the edges in graph
public class DirectedEdge {
    private  final int source;
    private final int destination;
    private  final double cost;

    public DirectedEdge(int source, int destination, double cost) {
        this.source = source;
        this.destination = destination;
        this.cost = cost;
    }

    public int from() {
        return source;
    }

    public int to () {
        return destination;
    }

    public double cost() {
        return cost;
    }

}
