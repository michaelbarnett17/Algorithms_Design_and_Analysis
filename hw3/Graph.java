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

    ArrayList<ArrayList<Integer>> nodes = new ArrayList<ArrayList<Integer>>();
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

    public ArrayList<ArrayList<Integer>> getNodesFromFile()
    {
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(testFileLocation));

            for (int i = 0; i < testFileSize; i++)
            {
                String[] st = br.readLine().trim().split("\t+");
                ArrayList<Integer> B = new ArrayList<Integer>();

                for (int j = 0; j < st.length; j++)
                {
                    B.add(Integer.parseInt(st[j]));
                }
                nodes.add(B);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return nodes;
    }

    public void updateEdges(ArrayList<ArrayList<Integer>> nodes)
    {
        for (int i = 0; i < nodes.size(); i++)
        {
            ArrayList<Integer> a = nodes.get(i);
            // Start index at 1 because the first point in the array is the one that is connected to all other points
            for (int j = 1; j < a.size(); j++)
            {
                // this check prevents same edge from being added twice ie. edge 1, 2 will get added but edge 2, 1 won't get added.
                if (nodes.get(i).get(0) < nodes.get(i).get(j))
                {
                    Edge edge = new Edge();
                    edge.point1 = nodes.get(i).get(0);
                    edge.point2 = nodes.get(i).get(j);
                    edges.add(edge);
                }
            }
        }
    }

    public void printNodes(ArrayList<ArrayList<Integer>> nodes)
    {
        System.out.println("The Nodes");
        for (int i = 0; i < nodes.size(); i++)
        {
            ArrayList<Integer> a = nodes.get(i);
            for (int j = 0; j < a.size(); j++)
            {
                int b = nodes.get(i).get(j);
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