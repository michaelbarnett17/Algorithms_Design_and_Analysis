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

    int[][] nodes = new int[testFileSize][];
    ArrayList<Edge> edges = new ArrayList<Edge>();

    public void contractGraph()
    {
        while (edges.size() > 2)
        {

            Random rand = new Random();
            int edgeIndex = rand.nextInt(edges.size());
            int firstVertex = edges.get(edgeIndex).point1;
            int secondVertex = edges.get(edgeIndex).point2;
            edges.remove(edgeIndex);

            // DEBUG
            System.out.println("edge " + edgeIndex);
            System.out.println("firstVertex " + firstVertex);
            System.out.println("nsecondVertex " + secondVertex);
            System.out.println("edge list length " + edges.size());
            System.out.println("");
        }
    }

    public int[][] getNodesFromFile()
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
                nodes[i] = B;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return nodes;
    }

    public void updateEdges(int[][] nodes)
    {
        for (int i = 0; i < nodes.length; i++)
        {
            int[] a = nodes[i];
            // Start index at 1 because the first point in the array is the one that is connected to all other points
            for (int j = 1; j < a.length; j++)
            {
                // this check prevents same edge from being added twice ie. edge [1, 2] will get added but edge [2, 1] won't get added.
                if (nodes[i][0] < nodes[i][j])
                {
                    Edge edge = new Edge();
                    edge.point1 = nodes[i][0];
                    edge.point2 = nodes[i][j];
                    edges.add(edge);
                }
            }
        }
    }

    public void printNodes(int[][] nodes)
    {
        System.out.println("The Nodes");
        for (int i = 0; i < nodes.length; i++)
        {
            int[] a = nodes[i];
            for (int j = 0; j < a.length; j++)
            {
                int b = nodes[i][j];
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