package hw4;

import java.io.*;
import java.util.*;

class Graph
{
    String testFile = "hw4\\SCC_Test.txt";
    ArrayList<Edge> edges = new ArrayList<Edge>();
    HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

    public void getEdgesFromFile()
    {
        try
        {   
            BufferedReader br = new BufferedReader(new FileReader(testFile));
            String line;

            while ((line = br.readLine()) != null)
            {
                String[] st = line.trim().split(" ");
                Edge edge = new Edge();

                edge.point1 = Integer.parseInt(st[0]);
                edge.point2 = Integer.parseInt(st[1]);
                
                edges.add(edge);
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void createNodes()
    {
        for (Edge edge : edges) 
        {
            // The nodeId is the Key
            if(!nodes.containsKey(edge.point1))
            {
                Node node = new Node();
                nodes.put(edge.point1, node);
            }

            if(nodes.get(edge.point2) == null)
            {
                Node node = new Node();
                nodes.put(edge.point2, node);               
            }

            // Only add arc from point1 to point2
            nodes.get(edge.point1).arcs.add(edge.point2);    
        }
    }

    public void printEdges()
    {
        System.out.println("The Edges: ");
        for (Edge edge : edges)
        {
            System.out.println(edge.point1 + " " + edge.point2);
        }
    }

    public void printNodes()
    {
        System.out.println("The Nodes");
        for (Integer nodeId: nodes.keySet())
        {
            Node node = nodes.get(nodeId);
            System.out.print(nodeId + " ");
            for (Integer arc : node.arcs)
            {
                System.out.print(arc + " ");
            }
            System.out.println(" ");
        }

    }



}