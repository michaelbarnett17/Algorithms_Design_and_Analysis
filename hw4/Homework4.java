package hw4;

class Homework4 
{
    public static void main(String args[])
    {
        Graph graph = new Graph();

        // Normal Graph
        graph.getEdges("normal", graph.edges);
        graph.printEdges(graph.edges);
        graph.createNodes(graph.edges, graph.nodes);
        graph.printNodes(graph.nodes);

        // Reversed Graph
        graph.getEdges("reversed", graph.edgesRev);
        graph.printEdges(graph.edgesRev);
        graph.createNodes(graph.edgesRev, graph.nodesRev);
        graph.printNodes(graph.nodesRev);
    }
}