package hw4;

import java.io.*;
import java.util.*;

class Graph
{
    String testFile = "hw4\\SCC_Test.txt";
    ArrayList<Edge> edges = new ArrayList<Edge>();
    ArrayList<Node> nodes = new ArrayList<Node>();

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

    // TODO implement create Nodes
    public void createNodes()
    {
        for (Edge edge : edges) 
        {
            if(nodes.get(edge.point1) == null)
            {

            }

            if(nodes.get(edge.point2) == null)
            {
                
            }            

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



}