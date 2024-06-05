import java.util.ArrayList;
import java.util.List;

public class graphNode {
    String node;
    List<graphNode> right = new ArrayList<graphNode>();
    public graphNode(String n) {
        this.node = n;
    }
    public void addRight(graphNode s) {
        this.right.add(s);
    }
}
