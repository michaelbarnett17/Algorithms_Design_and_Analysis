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
        System.out.println(Arrays.toString(strings));


        // strings[0] is first point of edge
        // For each reamining strings in strings (each reamining is a tuple)
            // new edge
            // edge.point1 = strings[0]
            // split string based on comma
            // edge.point2 = first half of tuple (before the comma)
            // edge.length = second half of tuple (after the comma)
            // add new edge to edges list

        // OLD CODE - STILL MAY BE USEFUL
        // Edge edge = new Edge();
        // edge.point1 = Integer.parseInt(st[0]);
        // edge.point2 = Integer.parseInt(st[1]);
        // edge.length = Integer.parse????


    }

}