import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.layout.mxOrganicLayout;
import javax.swing.*;
import com.mxgraph.model.mxCell;


public class graph {
    List<graphNode> nodes;
    public graph() {
        nodes = new ArrayList<graphNode>();
    }
    public void addNode(graphNode node) {
        nodes.add(node);
    }
    public void displayGraph(){
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            List<Object> vertexs = new ArrayList<>();

            for (graphNode node : nodes) {
                vertexs.add(graph.insertVertex(parent, null, node.node, 0, 0, 80, 30));
            }
            for (graphNode node : nodes) {
                Object vertex1 = null;
                for (Object vertex : vertexs)
                {
                    String LabelName = graph.getLabel(vertex);
                    if (Objects.equals(LabelName, node.node))
                    {
                        vertex1 = vertex;
                    }
                }
                for (graphNode right : node.right)
                {
                    for (Object vertex : vertexs)
                    {
                        String LabelName = graph.getLabel(vertex);
                        if (Objects.equals(LabelName, right.node))
                        {
                            graph.insertEdge(parent, null, "1", vertex1, vertex);
                        }
                    }
                }
            }
        } finally {
            graph.getModel().endUpdate();
        }
        mxIGraphLayout layout = new mxOrganicLayout(graph);
        layout.execute(parent);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        JFrame frame = new JFrame("Directed Graph Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(graphComponent);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    public void displayGraph5(List<List<String>> Pairs){
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            List<Object> vertexs = new ArrayList<>();
            for (graphNode node : nodes) {
                vertexs.add(graph.insertVertex(parent, null, node.node, 0, 0, 80, 30));
            }
            for (graphNode node : nodes) {
                Object vertex1 = null;
                for (Object vertex : vertexs)
                {
                    String LabelName = graph.getLabel(vertex);
                    if (Objects.equals(LabelName, node.node))
                    {
                        vertex1 = vertex;
                    }
                }
                for (graphNode right : node.right)
                {
                    int flag = 0;
                    List<String> tmp = new ArrayList<>();
                    tmp.add(node.node);
                    tmp.add(right.node);
                    for (List<String> pair : Pairs)
                    {
                        if (tmp.equals(pair))
                        {
                            flag = 1;
                        }
                    }
                    for (Object vertex : vertexs)
                    {
                        String LabelName = graph.getLabel(vertex);
                        if (Objects.equals(LabelName, right.node))
                        {
                            if (flag == 1)
                            {
                                Object edge = graph.insertEdge(parent, null, "1", vertex1, vertex);
                                mxCell cell = (mxCell) edge;
                                cell.setStyle("strokeColor=red");
                            }
                            else {
                                graph.insertEdge(parent, null, "1", vertex1, vertex);
                            }

                        }
                    }
                }
            }
        } finally {
            graph.getModel().endUpdate();
        }
        mxIGraphLayout layout = new mxOrganicLayout(graph);
        layout.execute(parent);
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        JFrame frame = new JFrame("Directed Graph Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(graphComponent);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    public List<String> FindPaths(graphNode node1, graphNode node2, List<String> path, List<List<String>> paths){ // 深度优先找寻路径
//        List<List<String>> paths = new ArrayList<>(); // 存储node1到node2的所有路径
        for(graphNode s : node1.right)
        {
            int flag = 1;
            for (String s1 : path) // 可能有问题
            {
                if (Objects.equals(s.node, s1)) {
                    flag = 0;
                    break;
                }
            }
            if (flag == 1)
            {
                List<String> path1 = new ArrayList<String>(path);
                path1.add(s.node);
                List<String> result = FindPaths(s, node2, path1, paths);
                if(result != null)
                {paths.add(result);
                }
            }
        }
        if(node1.equals(node2))
        {
            return path;
        }
        else return null;
    }


    public List<String> newBridgeWords(List<String> inputWords){
        List<String> newInput = new ArrayList<String>(inputWords); // deepcopy
        int insCount = 0;
        for (int i = 1; i < inputWords.size(); i++) {
            int count = 0; //判断图中是否有该两个词的节点
            graphNode nodeN1 = null;
            graphNode nodeN2 = null;
            for(graphNode s : nodes)
            {
                List<List<String>> pathsTofill = new ArrayList<>(); // 对第四个问题新建paths list进行存储路径
                if (Objects.equals(s.node, inputWords.get(i - 1))) {
                    nodeN1 = s;
                    count++;
                }
                else if (Objects.equals(s.node, inputWords.get(i))) {
                    nodeN2 = s;
                    count++;
                }
                if (count == 2) {
                    List<String> pathN1 = new ArrayList<>();
                    List<String> result = new ArrayList<>();
                    pathN1.add(nodeN1.node);
                    FindPaths(nodeN1, nodeN2, pathN1, pathsTofill);
                    for(List<String> path : pathsTofill) {
                        try{
                            List<String> tmp = path.subList(1, path.size()-1);
                            // System.out.println(tmp.toString());
                            result.addAll(tmp);
                        } catch (Exception e) {

                        }
                    }

                    if(result.size() > 0)
                    {
                        if (result.size() > 1)
                        {
                            int randomIndex = (int) (Math.random() * result.size());
                            newInput.add(i+insCount, result.get(randomIndex));
                            insCount++;
                        }
                        else
                        {
                            newInput.add(i+insCount, result.get(0));
                            insCount++;
                        }
                    }
                    break;
                }
            }
        }
        return newInput;
    }

    public List<String> calcDijkstra(List<List<String>> paths){
        List<String> shortestSublist = null;
        int minLength = Integer.MAX_VALUE;

        for (List<String> path : paths)
        {
            if (path.size() < minLength) {
                shortestSublist = path;
                minLength = path.size();
            }
        }
        if (minLength == Integer.MAX_VALUE)
        {
            System.out.println("不可达！");
            return null;
        }
        else {
            System.out.println("最短路径长度" + Integer.toString(minLength));
        }
        return shortestSublist;
    }

    public List<List<String>> shortestPairs(List<String> shortestList) { // 生成最短路径的两两组合的节点
        List<List<String>> Pairs = new ArrayList<List<String>>();
        for (int i = 1; i < shortestList.size(); i++) {
            List<String> tmp = new ArrayList<>();
            tmp.add(shortestList.get(i - 1));
            tmp.add(shortestList.get(i));
            Pairs.add(tmp);
        }
        System.out.println(Pairs.toString());
        return Pairs;
    }

    public List<String> mission5(List<List<String>> paths){
        if (paths==null){
            List<String> a =new ArrayList<>() ;
            return a;
        }
        List<String> shortestList = calcDijkstra(paths);
        if (shortestList != null) {
            System.out.println("最短路径是：" + shortestList.toString());
            List<List<String>> Pairs = shortestPairs(shortestList);
            displayGraph5(Pairs);
        }
        return shortestList;

    }

    public List<List<String>> mission6(graphNode start, List<List<String>> path){

        if (!start.right.isEmpty())
        {
            int randomIndex = (int) (Math.random() * start.right.size());
            int flag = 1;
            for (List<String> s : path)
            {
                List<String> tmp = new ArrayList<>();
                tmp.add(start.node);
                tmp.add(start.right.get(randomIndex).node);
                if (s.equals(tmp)) {
                    flag = 0;
                    break;
                }
            }
            List<String> tmp1 = new ArrayList<>();
            tmp1.add(start.node);
            tmp1.add(start.right.get(randomIndex).node);
            path.add(tmp1);

            try {
                Thread.sleep(1000); // 休眠100毫秒，避免CPU空转
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();

            }
            System.out.println(path.toString());
            if (flag == 1)
            {
                mission6(start.right.get(randomIndex), path);
            }

        }
        return path;



    }
}
