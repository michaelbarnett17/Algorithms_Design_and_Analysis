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
        int minCut = 10000; //Hax

        for (int i = 0; i < 1000; i++)
        {
            Graph graph = new Graph();
            graph.nodes = graph.getNodesFromFile();
            graph.createEdges(graph.nodes);
            graph.contractGraph();
    
            if (graph.edges.size() < minCut)
            {
                minCut = graph.edges.size();
            }
        }
        System.out.println("The mincut is: " + minCut);
    }
}