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

            // DEBUG
            printEdges();
            System.out.println("Contracted Edge " + edgeIndex);
            System.out.println("firstVertex " + firstVertex);
            System.out.println("secondVertex " +   secondVertex);
            System.out.println("edge list length " + edges.size());

            edges.remove(edgeIndex);

            // TODO need to update where other edges are pointing?????


            ArrayList<Integer> newNode = new ArrayList<Integer>();

            // The id will be the last spot in the list
            int newNodeId = nodes.size();
            // The id is the first element
            newNode.add(newNodeId);
            nodes.add(newNode);

            ArrayList<Integer> firstNodeToRemove = null;
            ArrayList<Integer> secondNodeToRemove = null;

            // Merge all points from first vertex (don't merge the 0 index)
            for (ArrayList<Integer> A : nodes) 
            {
                if (A.get(0) == firstVertex)
                {
                    System.out.println("Sublist A " + A.subList(1, A.size()));
                    newNode.addAll(A.subList(1, A.size()));
                    firstNodeToRemove = A;
                }
            }

            // Merge all points from second vertex (don't merge the 0 index)
            for (ArrayList<Integer> B : nodes) 
            {
                if (B.get(0) == secondVertex)
                {
                    System.out.println("Sublist B " + B.subList(1, B.size()));
                    newNode.addAll(B.subList(1, B.size()));
                    secondNodeToRemove = B;
                }
            }

            nodes.remove(firstNodeToRemove);
            nodes.remove(secondNodeToRemove);

            System.out.println(" ");

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
            System.out.print(edge.point1 + " " + edge.point2 + ", ");
        }
        System.out.println(" ");
    }
}