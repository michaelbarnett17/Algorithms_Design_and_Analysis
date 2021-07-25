package hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class Graph
{
    int testFileSize = 5;
    String testFileLocation = "hw3\\testMinCut.txt";

    int[][] graph = new int[testFileSize][];
    ArrayList<Edge> edges = new ArrayList<Edge>();

    public void contractGraph()
    {
        while (graph.length > 2)
        {
            // TODO implement contraction
        }
    }

    public int[][] getGraph()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(testFileLocation));

            for (int i = 0; i < testFileSize; i++)
            {
                String[] st = br.readLine().trim().split("\t+");
                int [] B = new int[st.length];
                for (int j = 0; j < st.length; j++)
                {
                    B[j] = Integer.parseInt(st[j]);
                }
                graph[i] = B;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return graph;
    }

    public void updateEdges(int[][] graph)
    {
        for (int i = 0; i < graph.length; i++)
        {
            int[] a = graph[i];
            // Start index at 1 because the first point in the array is the one that is connected to all other points
            for (int j = 1; j < a.length; j++)
            {
                // this check prevents same edge from being added twice ie. edge [1, 2] will get added but edge [2, 1] won't get added.
                if (graph[i][0] < graph[i][j])
                {
                    Edge edge = new Edge();
                    edge.point1 = graph[i][0];
                    edge.point2 = graph[i][j];
                    edges.add(edge);
                }
            }
        }
    }

    public void printGraph(int[][] graph)
    {
        System.out.println("The Graph");
        for (int i = 0; i < graph.length; i++)
        {
            int[] a = graph[i];
            for (int j = 0; j < a.length; j++)
            {
                int b = graph[i][j];
                System.out.print(b + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printEdges()
    {
        for (Edge edge : edges)
        {
            System.out.println("Edge ");
            System.out.println(edge.point1 + " " + edge.point2);
        }
    }
}