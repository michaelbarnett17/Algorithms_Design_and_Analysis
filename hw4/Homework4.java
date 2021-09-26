package hw4;

import java.util.*;

class Homework4 
{
    public static void main(String args[])
    {
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

        for (Map.Entry<Integer, Node> entry : graph.nodesRev.entrySet()) {
            Integer key = entry.getKey();
            Node value = entry.getValue();
            System.out.print("Node Index: " + key + " ");
            System.out.print("Finish Time: " + value.finishTime);
            System.out.println();
        }


    }
}