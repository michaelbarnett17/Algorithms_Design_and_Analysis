package hw4;

import java.io.*;
import java.util.*;

class Graph
{
    String testFile = "hw4\\SCC_Test.txt";
    ArrayList<Edge> edges = new ArrayList<Edge>();
    ArrayList<Edge> edgesRev = new ArrayList<Edge>();
    HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();
    HashMap<Integer, Node> nodesRev = new HashMap<Integer, Node>();
    // HAX
    int nodeQty = 9;

    public void getEdges(String direction, ArrayList<Edge> edges)
    {
        try
        {   
            BufferedReader br = new BufferedReader(new FileReader(testFile));
            String line;

            while ((line = br.readLine()) != null)
            {
                String[] st = line.trim().split(" ");
                Edge edge = new Edge();

                if (direction == "normal")
                {
                    edge.point1 = Integer.parseInt(st[0]);
                    edge.point2 = Integer.parseInt(st[1]);
                }
                else if (direction == "reversed")
                {
                    edge.point1 = Integer.parseInt(st[1]);
                    edge.point2 = Integer.parseInt(st[0]);                    
                }
                edges.add(edge);
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    } 

    public void createNodes(ArrayList<Edge> edges, HashMap<Integer, Node> nodes)
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

    public void dfsLoop(HashMap<Integer, Node> nodes)
    {
        Integer t = 0;
        Integer s = null;

        for (int i = nodeQty; i > 0; i--) {
 
            if (nodes.get(i).explored == false)
            {
                s = i;
                depthFirstSearch(nodes, nodes.get(i));

            }
            
            System.out.print("Key = " + i);
            nodes.get(i).printArcs();
            System.out.println(" ");


        }
    }

    public void depthFirstSearch(HashMap<Integer, Node> nodesRev, Node node)
    {

    }

    public void printEdges(ArrayList<Edge> edges)
    {
        System.out.println("The Edges: ");
        for (Edge edge : edges)
        {
            System.out.println(edge.point1 + " " + edge.point2);
        }
        System.out.println(" ");
    }

    public void printNodes(HashMap<Integer, Node> nodes)
    {
        System.out.println("The Nodes");
        for (Integer nodeId: nodes.keySet())
        {
            Node node = nodes.get(nodeId);
            System.out.print(nodeId + " arcs: ");
            for (Integer arc : node.arcs)
            {
                System.out.print(arc + " ");
            }
            System.out.println(" ");
        }

    }



}