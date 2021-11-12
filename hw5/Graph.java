package hw5;

import java.io.*;
import java.util.*;

class Graph
{
    String fileName;
    ArrayList<Edge> edges;
    HashMap<Integer, Node> nodes;
    Node[] processedNodes; 
    ArrayList<Integer> distances;

    public Graph(String fileName) 
    {
        this.fileName = fileName;
        this.edges = new ArrayList<Edge>();
        this.nodes = new HashMap<Integer, Node>();
        this.processedNodes = new Node[5];

        // placeholder since nodes start at 1
        this.processedNodes[0] = new Node();
        this.processedNodes[0].pathLength = 0;

    }

    public void calculateShortestPaths()
    {

        nodes.get(1).pathLength = 0;
        processedNodes[1] = nodes.get(1);
        nodes.remove(1);
        

        while(nodes.size() != 0) 
        {
            ArrayList<Edge> candidateEdges = getEdgesWithOneNodeProcessed();

            Edge edge = getGreedyEdge(candidateEdges);

            // System.out.print("This is the greedy edge: " + edge.point1 + "," +edge.point2 + " length-" + edge.length);
            // System.out.print(" Greedy Length-" + edge.greedyLength);

            processedNodes[edge.point2] = nodes.get(edge.point2);
            processedNodes[edge.point2].pathLength = edge.greedyLength;

            nodes.remove(edge.point2);
        }
    }

    public Edge getGreedyEdge(ArrayList<Edge> candidateEdges)
    {   
        Edge currentMinimumEdge = new Edge();
        
        // MAY HAVE TO CHANGE THIS
        int currentMinGreedyLength = 10000000;
        for(Edge edge : candidateEdges)
        {
            // System.out.print(edge.point1 + "," +edge.point2 + " length-" + edge.length);
            int greedyLength = processedNodes[edge.point1].pathLength + edge.length;
            // System.out.println(" greedyLength-" + greedyLength);

            if (greedyLength < currentMinGreedyLength)
            {
                currentMinimumEdge = edge;
                currentMinGreedyLength = greedyLength;
                currentMinimumEdge.greedyLength = greedyLength;
            }            
        }
        
        return currentMinimumEdge;
    }

    public ArrayList<Edge> getEdgesWithOneNodeProcessed()
    {
        ArrayList<Edge> candidateEdges = new ArrayList<Edge>();
        for(Edge edge : edges)
        {
            if (!Objects.isNull(processedNodes[edge.point1])  && Objects.isNull(processedNodes[edge.point2]))
            {
                candidateEdges.add(edge);
            }
        }
        return candidateEdges;       
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

    public void printAnswer()
    {
        for (Node node : processedNodes)
        {
            System.out.println(node.pathLength);
        }
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