package hw5;

import java.util.*;

class Homework5 
{
    public static void main(String args[])
    {
        String fileName = "hw5\\dijkstraData_test.txt";
        // String fileName = "hw5\\dijkstraData.txt";

        Graph graph = new Graph(fileName);
        graph.getEdges();
        // graph.printNodes();
        // graph.printEdges();
        graph.calculateShortestPaths();
    }
}