import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import javax.swing.*;
import java.util.Scanner;
//这是一个炫酷的代码
public class lab1 {
    public lab1(){

    }
    public List<String> stringset(String inputstr){
        List<String> finals = new ArrayList<>();
        for (String tmp : inputstr.toLowerCase().replaceAll("[^a-z]", " ").split(" "))
        {
            if (!tmp.isEmpty())
            {
                finals.add(tmp.replace(" ", ""));
            }
        }
        return finals;
    }

    public List<String> tokens(List<String> finals)
    {
        List<String> tokens = new ArrayList<>(finals);
        for (int i = 0; i < tokens.size(); i++) {
            for (int j = i; j < tokens.size(); j++) {
                if(tokens.get(i).equals(tokens.get(j))&&i!=j){
                    tokens.remove(j);
                }
            }
        }

        return tokens;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入想要的起点: ");
        int num1 = scanner.nextInt();
        System.out.print("请输入想要的终点: ");
        int num2 = scanner.nextInt();

        lab1 obj = new lab1();
        List<String> preGraph;
        List<String> set;
        graph g = new graph();
        try {
            String str = Files.readString(Paths.get("C:\\Users\\22918\\Desktop\\test1\\src\\teststr"));
//            System.out.print("到这里了");

            preGraph = obj.stringset(str);
            set = obj.tokens(preGraph);
            System.out.println(preGraph.toString());
            System.out.println(set.toString());
            for (String s : set) {
                graphNode node = new graphNode(s);
                g.addNode(node);
            }

            for (int i = 0; i < preGraph.size(); i++) {
                if (i == 0) {
                    for(graphNode m : g.nodes)
                    {
                        if (Objects.equals(m.node, preGraph.get(i)))
                        {
                            for(graphNode m1 : g.nodes)
                            {
                                if (Objects.equals(m1.node, preGraph.get(i+1)))
                                {
                                    m.addRight(m1);
                                }
                            }

                        }
                    }
                }
                else if (i == preGraph.size() - 1) {

                }
                else {
                    for(graphNode m : g.nodes)
                    {
                        if (Objects.equals(m.node, preGraph.get(i)))
                        {
                            for(graphNode m1 : g.nodes)
                            {
                                if (Objects.equals(m1.node, preGraph.get(i+1)))
                                {
                                    m.addRight(m1);
                                }
                            }

                        }
                    }
                }
            }
//            for (graphNode m : g.nodes) {
//                System.out.println(m.toString());
//                System.out.println(m.right.toString());
//            }
            List<String> path = new ArrayList<>();
            List<String> result = new ArrayList<>();
            List<List<String>> paths = new ArrayList<>(); // 两点间的所有路径
            path.add(g.nodes.get(num1).node);
            graphNode nn = new graphNode("111"); // 不可达点测试
            g.nodes.add(nn);
            g.FindPaths(g.nodes.get(num1), g.nodes.get(num2), path, paths); // 举例： 0是起点， 3是终点
            System.out.println("---------");
            System.out.println(paths.toString());
            System.out.println("---------");

            if (!paths.isEmpty())
            {
                for (List<String> path1 : paths) {
                    if (path1.subList(1, path1.size() - 1).size() > 0)
                    {
                        System.out.println("下列是" + path1.get(0) + "和" + path1.get(path1.size() - 1) + "的bridge words:");
                        System.out.println(path1.subList(1, path1.size() - 1).toString());
                    }
                    else{
                        System.out.println("No bridge words from word1 to " + "word2!");
                    }

                }
            }
            else{
                System.out.println("不可达！");
                System.exit(1);
            }
            // 2
            g.displayGraph();

            // 5
            //假设此处权值均为1
            System.out.println("\n第五项任务:");
            g.mission5(paths);


            // 4
            String str11 = "Seek to explore new and " +
                    "exciting synergies";
            List<String> newBridge = obj.stringset(str11);
            List<String> newResult = new ArrayList<>();
            System.out.println(newBridge);
            newResult = g.newBridgeWords(newBridge);
            System.out.println(newResult);

            // 6
            System.out.println("执行随机游走：");
            int randomIndex = (int) (Math.random() * g.nodes.size());
//            graphNode start = g.nodes.get(randomIndex);
//            List<List<String>> path6 = new ArrayList<>();
//            g.mission6(start, path6);
//            System.out.println(path6.toString());

            for (int i = 0; i < 100; i++) {
                graphNode start = g.nodes.get(0);
                List<List<String>> path6 = new ArrayList<>();
                g.mission6(start, path6);
                System.out.println(path6.toString());
            }
        }
        catch (IOException e) {
            System.out.print("Exception");
        }

    }
}
