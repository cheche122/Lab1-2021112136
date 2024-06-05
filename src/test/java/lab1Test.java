import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


public class lab1Test {
    @Test
    public void lab1Test1() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {   Class test_lab1=lab1.class;
        Constructor constructor1 = test_lab1.getConstructor();
        lab1 lab1_one = (lab1) constructor1.newInstance();

        Method method1 = test_lab1.getDeclaredMethod("second",String.class,String.class );
        //List<List<String>> test_paths1 = lab1.second("to", "travel");
        Object test_paths1 = method1.invoke(lab1_one,new Object[]{"to","travel"});

        Class test_graph = graph.class;
        Constructor constructor = test_graph.getConstructor();
        graph graph_one = (graph) constructor.newInstance();
        Method method = test_graph.getDeclaredMethod("mission5", List.class);
        Object result = method.invoke(graph_one, test_paths1);
        System.out.println("---------------------");
        System.out.println("---------------------");
        System.out.println(result.toString());
        // assert "[to, discover, uncharted, lands, and]"==result;
        Assert.assertEquals("[to, travel]",result.toString());
        System.out.println("---------------------");
        System.out.println("---------------------");

    }

    @Test
    public void lab1Test2() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {

            List<List<String>> test_paths2 = lab1.second("to", "to");
            Class test_graph = graph.class;
            Constructor constructor = test_graph.getConstructor();
            graph graph_one = (graph) constructor.newInstance();
            Method method = test_graph.getDeclaredMethod("mission5", List.class);
            Object result = method.invoke(graph_one, test_paths2);
            System.out.println("---------------------");
            System.out.println("---------------------");
            System.out.println(result.toString());
           // assert "[to, discover, uncharted, lands, and]"==result;
            Assert.assertEquals("[to]",result.toString());
            System.out.println("---------------------");
            System.out.println("---------------------");

    }
    @Test
    public void lab1Test3() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {

        List<List<String>> test_paths3 = lab1.second("22", "11");
        Class test_graph = graph.class;
        Constructor constructor = test_graph.getConstructor();
        graph graph_one = (graph) constructor.newInstance();
        Method method = test_graph.getDeclaredMethod("mission5", List.class);
        Object result = method.invoke(graph_one, test_paths3);
        System.out.println("---------------------");
        System.out.println("---------------------");
        System.out.println(result.toString());
        Assert.assertEquals("[]",result.toString());
        System.out.println("---------------------");
        System.out.println("---------------------");

    }
    @Test
    public void lab1Test4() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {

        List<List<String>> test_paths4 = lab1.second("to", "22");
        Class test_graph = graph.class;
        Constructor constructor = test_graph.getConstructor();
        graph graph_one = (graph) constructor.newInstance();
        Method method = test_graph.getDeclaredMethod("mission5", List.class);
        Object result = method.invoke(graph_one, test_paths4);
        System.out.println("---------------------");
        System.out.println("---------------------");
        System.out.println(result.toString());
        // assert "[to, discover, uncharted, lands, and]"==result;
        Assert.assertEquals("[]",result.toString());
        System.out.println("---------------------");
        System.out.println("---------------------");
    }
    @Test
    public void lab1Test5() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {

        List<List<String>> test_paths5 = lab1.second("and", "the");
        Class test_graph = graph.class;
        Constructor constructor = test_graph.getConstructor();
        graph graph_one = (graph) constructor.newInstance();
        Method method = test_graph.getDeclaredMethod("mission5", List.class);
        Object result = method.invoke(graph_one, test_paths5);
        System.out.println("---------------------");
        System.out.println("---------------------");
        System.out.println(result.toString());
        // assert "[to, discover, uncharted, lands, and]"==result;
        Assert.assertEquals("[and, seas, to, travel, beyond, the]",result.toString());
        System.out.println("---------------------");
        System.out.println("---------------------");

    }
    @Test
    public void lab1Test6() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {

        List<List<String>> test_paths6 = lab1.second("to", "");
        Class test_graph = graph.class;
        Constructor constructor = test_graph.getConstructor();
        graph graph_one = (graph) constructor.newInstance();
        Method method = test_graph.getDeclaredMethod("mission5", List.class);
        Object result = method.invoke(graph_one, test_paths6);
        System.out.println("---------------------");
        System.out.println("---------------------");
        System.out.println(result.toString());
        // assert "[to, discover, uncharted, lands, and]"==result;
        Assert.assertEquals("[]",result.toString());
        System.out.println("---------------------");
        System.out.println("---------------------");


    }
    @Test
    public void lab1Testw1() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {
        List<List<String>> test_paths_1 = lab1.second("to", "new");
        System.out.println(test_paths_1);
        Assert.assertEquals("[[to, discover, uncharted, lands, and, new], [to, meet, new], [to, explore, strange, new], [to, seek, out, new]]",test_paths_1.toString());
    }
    @Test
    public void lab1Testw2() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {
        List<List<String>> test_paths_2 = lab1.second("faces", "and");
        System.out.println(test_paths_2);
        Assert.assertEquals("[[faces, and]]",test_paths_2.toString());
    }

    @Test
    public void lab1Testw3() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {
        List<List<String>> test_paths_3 = lab1.second("abc", "and");
        System.out.println(test_paths_3);
        Assert.assertEquals(null,test_paths_3);
    }
    @Test
    public void lab1Testw4() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InstantiationException
    {
        List<List<String>> test_paths_4 = lab1.second("and", "faces");
        System.out.println(test_paths_4);
     Assert.assertEquals("[[and, seas, to, meet, new, faces], [and, seas, to, explore, strange, new, faces], [and, seas, to, seek, out, new, faces], [and, hear, untold, stories, to, meet, new, faces], [and, hear, untold, stories, to, explore, strange, new, faces], [and, hear, untold, stories, to, seek, out, new, faces], [and, new, faces]]",test_paths_4.toString());
    }

}
