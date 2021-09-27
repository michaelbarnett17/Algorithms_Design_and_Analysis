package hw4;

import java.util.*;

class Homework4 
{
    public static void main(String args[])
    {
        // NOTE: need to increase Java stack size to run this
        // java -Xss16M hw4/Homework4

        String fileName = "hw4\\SCC_Test.txt";    
        // String fileName = "hw4\\SCC.txt";       

        Graph graph = new Graph(fileName);

        // Normal Graph
        graph.getEdges("normal", graph.edges);
        graph.createNodes(graph.edges, graph.nodes);

        // Reversed Graph
        graph.getEdges("reversed", graph.edgesRev);
        graph.createNodes(graph.edgesRev, graph.nodesRev);

        // Compute strongly connected components using Kosaraju's Two-Pass Algorithm (see lecture notes)
        graph.dfsLoop(graph.nodesRev);
        graph.createNewForwardGraph();
        graph.dfsLoop(graph.nodesNew);
        graph.populateLeaders();
        graph.findFiveLargestSCCs();
    }
}