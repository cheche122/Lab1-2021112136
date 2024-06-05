import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javax.swing.*;

public class DirectedGraphDemo {

    public static void main(String[] args) {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            Object v1 = graph.insertVertex(parent, null, "Node 1", 20, 20, 80, 30);
            Object v2 = graph.insertVertex(parent, null, "Node 2", 240, 150, 80, 30);
            Object v3 = graph.insertVertex(parent, null, "Node 3", 140, 20, 80, 30);

            graph.insertEdge(parent, null, "Edge 1", v1, v2);
            graph.insertEdge(parent, null, "Edge 2", v2, v3);
            graph.insertEdge(parent, null, "Edge 3", v1, v3);
        } finally {
            graph.getModel().endUpdate();
        }

        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        JFrame frame = new JFrame("Directed Graph Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(graphComponent);
        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}