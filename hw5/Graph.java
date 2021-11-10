package hw5;

import java.io.*;
import java.util.*;

class Graph
{
    String fileName;
    ArrayList<Edge> edges;
    HashMap<Integer, Node> nodes;

    public Graph(String fileName) 
    {
        this.fileName = fileName;
        this.edges = new ArrayList<Edge>();
        this.nodes = new HashMap<Integer, Node>();
    }

    public void getEdges()
    {
        try
        {   
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null)
            {
                String[] strings = line.trim().split("\\s+");
                processInputLine(strings);
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void processInputLine(String[] strings)
    {
        // System.out.println(Arrays.toString(strings));
        Node node = new Node();

        for (int i = 0; i < strings.length; i++)
        {
            if (i != 0)
            {
                Edge edge = new Edge();
                edge.point1 = Integer.parseInt(strings[0]);            
                String[] tuple = strings[i].split(",");
                edge.point2 = Integer.parseInt(tuple[0]);
                edge.length = Integer.parseInt(tuple[1]);
                edges.add(edge);
                node.edges.add(edge);
            }
        }

        nodes.put(Integer.parseInt(strings[0]), node);
    }

    public void printNodes() 
    {
        System.out.println("These are the nodes---------------");
        for (Map.Entry<Integer, Node> entry : nodes.entrySet())
        {
            System.out.print("Location in Map = " + entry.getKey());
            System.out.print(" Has : ");
            for (Edge edge : entry.getValue().edges)
            {
                System.out.print(edge.point1 + "," + edge.point2 + " length-" + edge.length +"; ");
            }
            System.out.println("");
        }
    }

    public void printEdges() 
    {
        System.out.println("These are the edges---------------");
        for (Edge edge : edges)
        {
            System.out.println(edge.point1 + "," + edge.point2);
        }
    }


}