import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class lab1 {
    public  static List<List<String>> paths = new ArrayList<>(); // 两点间的所有路径
    public lab1() {}
    public  static String start1 ;
    public  static String end1;


    public static graph g=new graph();
    public  static graphNode start_;
    public graphNode get11node;

    public static  List<List<String>> path16 = new ArrayList<>();




    public static List<List<String>> second(String start1, String end1)
    {

        lab1 obj = new lab1();
        List<String> preGraph;
        List<String> set;
        //graph g = new graph();
        try {
            String str = Files.readString(Paths.get("C:\\Users\\22918\\Desktop\\Lab1\\src\\main\\java\\teststr"));

            preGraph = obj.stringset(str);
            set = obj.tokens(preGraph);

            // System.out.println(preGraph.toString());
            System.out.print("下列是图中存在的所有点: ");
            System.out.println(set.toString());
            Scanner scanner = new Scanner(System.in);


            int num1 = 0;
            int num2 = 0;
            int num3 = 0; // flag
            for (int i = 0; i < set.size(); i++) {
                if (Objects.equals(set.get(i), start1)) {
                    num1 = i;
                    num3 += 1;
                } else if (Objects.equals(set.get(i), end1)) {
                    num2 = i;
                    num3 += 1;
                }
            }
            if (num3 != 2) {
                System.out.println("No " + start1 + " or " + end1 + " in the graph!");
            return null;
            }
            for (String s : set) {
                graphNode node = new graphNode(s);
                g.addNode(node);
            }

            for (int i = 0; i < preGraph.size(); i++) {
                if (i == 0) {
                    for (graphNode m : g.nodes) {
                        if (Objects.equals(m.node, preGraph.get(i))) {
                            for (graphNode m1 : g.nodes) {
                                if (Objects.equals(m1.node, preGraph.get(i + 1))) {
                                    m.addRight(m1);
                                }
                            }

                        }
                    }
                } else if (i == preGraph.size() - 1) {

                } else {
                    for (graphNode m : g.nodes) {
                        if (Objects.equals(m.node, preGraph.get(i))) {
                            for (graphNode m1 : g.nodes) {
                                if (Objects.equals(m1.node, preGraph.get(i + 1))) {
                                    m.addRight(m1);
                                }
                            }

                        }
                    }
                }
            }
//
            List<String> path = new ArrayList<>();
       //     List<List<String>> paths = new ArrayList<>(); // 两点间的所有路径


            List<String> result = new ArrayList<>();
          //  List<List<String>> paths = new ArrayList<>(); // 两点间的所有路径

            path.add(g.nodes.get(num1).node);
//            graphNode nn = new graphNode("111"); // 不可达点测试
//            g.nodes.add(nn);
            g.FindPaths(g.nodes.get(num1), g.nodes.get(num2), path, paths); // 举例： 0是起点， 3是终点
            System.out.println(paths.toString());
            System.out.println("_____________________-");
            List<List<List<String>>> wordAllpaths = new ArrayList<>(); // 按照结尾单词分开所有的路径
            for (graphNode m : g.nodes) {
                List<String> Mission5path = new ArrayList<>();
                List<List<String>> Mission5Allpaths = new ArrayList<>();
                Mission5path.add(g.nodes.get(num1).node);
                if (!Objects.equals(m.node, g.nodes.get(num1).node)) {
                    g.FindPaths(g.nodes.get(num1), m, Mission5path, Mission5Allpaths);
                    wordAllpaths.add(Mission5Allpaths);
                }

            }

            System.out.println("---------");
            System.out.println(paths.toString());
            System.out.println("---------");
            if (!paths.isEmpty()) {
                List<String> bridgeWordsList = new ArrayList<>();
                int firstPrint = 1;
                for (List<String> path1 : paths) {
                    if (path1.subList(1, path1.size() - 1).size() > 0) {
                        if (firstPrint == 1) {
                            System.out.println("The bridge words from " + path1.get(0) + " to " + path1.get(path1.size() - 1) + " are: ");
                            firstPrint = 0;
                        }
                        System.out.println(path1.subList(1, path1.size() - 1).toString());
                    } else {


                        System.out.println("No bridge words from word1 to " + "word2!");


                    }

                }
            } else {
                System.out.println("No bridge words from " + start1 + " to " + end1 + "!");
            }

            // 2
            g.displayGraph();

            // 5
            //假设此处权值均为1
            System.out.println("\n第五项任务:");
            g.mission5(paths);
            System.out.println("\n可选功能");
            try {
                for (List<List<String>> path5 : wordAllpaths) {
                    System.out.println("到达 " + path5.get(0).get(path5.get(0).size() - 1));
                    // g.mission5(path5);
                }
            } catch (Exception _) {
//忽视下划线
            }


            // 4
            System.out.println("\n第四项任务:");
            Scanner scanner1 = new Scanner(System.in);
            System.out.print("请输入句子: ");
//            String str11 = scanner1.nextLine();
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
            graphNode start = g.nodes.get(randomIndex);

            List<List<String>> path6 = new ArrayList<>();
            g.mission6(start, path6);
            g.displayGraph5(path6);
            System.out.println(path6.toString());

        } catch (IOException _) {
            System.out.print("Exception");
        }
        List<List<String>> a= new ArrayList<>();
        a=paths;
     paths=new ArrayList<>();

        return a;
    }

    public static void main(String[] args) {
        lab1 obj = new lab1();
        List<String> preGraph;
        List<String> set;
        graph g = new graph();
        try {
            String str = Files.readString(Paths.get("C:\\Users\\22918\\Desktop\\Lab1\\src\\main\\java\\teststr"));
            preGraph = obj.stringset(str);
            set = obj.tokens(preGraph);
            // System.out.println(preGraph.toString());
            System.out.print("下列是图中存在的所有点: ");
            System.out.println(set.toString());
            Scanner scanner = new Scanner(System.in);
            System.out.print("请输入想要的起点: ");
            start1 = scanner.nextLine();
            System.out.print("请输入想要的终点: ");
            end1 = scanner.nextLine();

            second(start1, end1);
        } catch (IOException _) {
            System.out.print("Exception");
        }

    }

    public List<String> stringset(String inputstr) {
        List<String> finals = new ArrayList<>();
        for (String tmp : inputstr.toLowerCase().replaceAll("[^a-z]", " ").split(" ")) {
            if (!tmp.isEmpty()) {
                finals.add(tmp.replace(" ", ""));
            }
        }
        return finals;
    }

    public List<String> tokens(List<String> finals) {
        List<String> tokens = new ArrayList<>(finals);
        for (int i = 0; i < tokens.size(); i++) {
            for (int j = i; j < tokens.size(); j++) {
                if (tokens.get(i).equals(tokens.get(j)) && i != j) {
                    tokens.remove(j);
                }
            }
        }

        return tokens;
    }


}
