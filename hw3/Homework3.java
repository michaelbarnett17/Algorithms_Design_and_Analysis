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

        graph.printGraph(graph.getGraph());
        graph.updateEdges(graph.getGraph());
        graph.printEdges();

    }

}