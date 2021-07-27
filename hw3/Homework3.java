package hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Homework3 
{
    public static void main(String args[])
    {
        Graph graph = new Graph();

        graph.nodes = graph.getNodesFromFile();
        graph.printNodes(graph.nodes);
        graph.updateEdges(graph.nodes);

        graph.contractGraph();
    }
}