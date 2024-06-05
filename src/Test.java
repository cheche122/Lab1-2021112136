import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {
    private Map<Integer, List<Integer>> graph;

    public Test() {
        this.graph = new HashMap<>();
    }

    public void addVertex(int vertex) {
        if (!graph.containsKey(vertex)) {
            graph.put(vertex, new ArrayList<>());
        }
    }

    public void addEdge(int from, int to) {
        if (!graph.containsKey(from)) {
            addVertex(from);
        }
        if (!graph.containsKey(to)) {
            addVertex(to);
        }
        graph.get(from).add(to);
    }

    public void printGraph() {
        for (int vertex : graph.keySet()) {
            System.out.print(vertex + " -> ");
            for (int neighbor : graph.get(vertex)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Test graph = new Test();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 1);

        graph.printGraph();
    }
}