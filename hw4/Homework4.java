package hw4;

import java.util.*;

class Homework4 
{
    public static void main(String args[])
    {
        //NOTE: need to increase Java stack size to run this
        // java -Xss16M hw4/Homework4
        Graph graph = new Graph();

        // Normal Graph
        graph.getEdges("normal", graph.edges);
        graph.createNodes(graph.edges, graph.nodes);
        // graph.printEdges(graph.edges);
        // graph.printNodes(graph.nodes);

        // Reversed Graph
        graph.getEdges("reversed", graph.edgesRev);
        graph.createNodes(graph.edgesRev, graph.nodesRev);
        // graph.printEdges(graph.edgesRev);
        // graph.printNodes(graph.nodesRev);

        graph.dfsLoop(graph.nodesRev);
        // graph.printReversedFinishTimes();

        graph.createNewForwardGraph();

        graph.dfsLoop(graph.nodesNew);

        // graph.printNodes(graph.nodesNew);

        graph.populateLeaders();

        // System.out.println(graph.leaders);
        graph.findFiveLargestSCCs();
    }
}