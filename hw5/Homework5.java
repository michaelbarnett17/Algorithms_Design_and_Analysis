package hw5;

import java.util.*;

class Homework5 
{
    public static void main(String args[])
    {
        // String fileName = "hw5\\dijkstraData_test.txt";  int graphSize = 4;
        String fileName = "hw5\\dijkstraData.txt";  int graphSize = 200;

        Graph graph = new Graph(fileName, graphSize);
        graph.getEdges();
        // graph.printNodes();
        // graph.printEdges();
        graph.calculateShortestPaths();
        graph.printAnswer();
    }
}