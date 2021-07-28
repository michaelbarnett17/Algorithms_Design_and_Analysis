package hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;

class Graph
{
    int testFileSize = 200;
    int initialNewId = testFileSize;
    String testFileLocation = "hw3\\kargerMinCut.txt";
    ArrayList<ArrayList<Integer>> nodes = new ArrayList<ArrayList<Integer>>();
    ArrayList<Edge> edges = new ArrayList<Edge>();

    public void contractGraph()
    {
        while (nodes.size() > 2)
        {
            Random rand = new Random();
            int edgeIndex = rand.nextInt(edges.size());
            int firstVertex = edges.get(edgeIndex).point1;
            int secondVertex = edges.get(edgeIndex).point2;
            int newNodeId = ++initialNewId;

            // System.out.println("START NEW CONTRACTION \n\nBefore Contraction\n");
            // printNodes(nodes);
            // printEdges();
            // System.out.println("Edge index to contract " + edgeIndex);
            // System.out.println("First Vertex " + firstVertex);
            // System.out.println("Second Vertex " +  secondVertex);
            // System.out.println("Id of new Vertex " +  initialNewId);
            // System.out.println("Edge list length " + edges.size());
            // System.out.println(" ");

            edges.remove(edgeIndex);

            ArrayList<Integer> newNode = new ArrayList<Integer>();      
            ArrayList<Integer> firstNodeToRemove = null;
            ArrayList<Integer> secondNodeToRemove = null;

            newNode.add(newNodeId);
            // Merge all points from first vertex (don't merge the 0 index)
            for (ArrayList<Integer> A : nodes) 
            {
                if (A.get(0) == firstVertex)
                {
                    newNode.addAll(A.subList(1, A.size()));
                    firstNodeToRemove = A;
                }
            }

            // Merge all points from second vertex (don't merge the 0 index)
            for (ArrayList<Integer> B : nodes) 
            {
                if (B.get(0) == secondVertex)
                {
                    newNode.addAll(B.subList(1, B.size()));
                    secondNodeToRemove = B;
                }
            }

            nodes.remove(firstNodeToRemove);
            nodes.remove(secondNodeToRemove);
            updateNodes(firstVertex, secondVertex, newNodeId);

            newNode.removeAll(Collections.singleton(firstVertex));
            newNode.removeAll(Collections.singleton(secondVertex));

            nodes.add(newNode);
            edges.clear();
            createEdges(nodes);
        }
    }

    public void updateNodes(int firstVertex, int secondVertex, int newNodeId)
    {
        for (int i = 0; i < nodes.size(); i++)
        {
            ArrayList<Integer> a = nodes.get(i);
            for (int j = 1; j < a.size(); j++)
            {
                if (a.get(j) == firstVertex || a.get(j) == secondVertex)
                {
                    a.set(j, newNodeId);
                    nodes.set(i, a); 
                }
            }
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
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return nodes;
    }

    public void createEdges(ArrayList<ArrayList<Integer>> nodes)
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
        System.out.println("The Nodes: ");
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
        System.out.println("The Edges: ");
        for (Edge edge : edges)
        {
            System.out.print(edge.point1 + " " + edge.point2 + ", ");
        }
        System.out.println(" ");
        System.out.println(" ");
    }
}