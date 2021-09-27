package hw4;

import java.io.*;
import java.util.*;

class Graph
{
////HAX: UPDATE THIS NUMBER TO THE TOTAL NUMBER OF NODES FROM INPUT FILE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    int nodeQty = 9;

    String testFile = "hw4\\SCC_Test.txt";

    // Forward Graph
    ArrayList<Edge> edges = new ArrayList<Edge>();
    HashMap<Integer, Node> nodes = new HashMap<Integer, Node>();

    // Reversed Graph
    ArrayList<Edge> edgesRev = new ArrayList<Edge>();
    HashMap<Integer, Node> nodesRev = new HashMap<Integer, Node>();

    // Renumbered Forward Graph
    // List of edges no longer needed, map of nodes will contain all graph information
    HashMap<Integer, Node> nodesNew = new HashMap<Integer, Node>();
    
    // Order by finishing time
    List<Node> orderedNodes = new ArrayList<Node>();

    // For First Pass
    Integer t = 0;

    // For Second Pass
    Integer s = null;

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
        for (int i = nodeQty; i > 0; i--) {
 
            if (nodes.get(i).explored == false)
            {
                s = i;
                depthFirstSearch(nodes, nodes.get(i));
            }
            
            // System.out.print("Key = " + i);
            // nodes.get(i).printArcs();
            // System.out.println(" ");
        }
    }

    public void depthFirstSearch(HashMap<Integer, Node> nodes, Node node)
    {
        node.explored = true;
        node.leader = s;

        for (Integer outgoingArc : node.arcs) 
        {
            if (nodes.get(outgoingArc).explored == false)
            {
                // System.out.println(outgoingArc);
                depthFirstSearch(nodes, nodes.get(outgoingArc));
            }
        }
        t++;
        node.finishTime = t;
    }

    // New graph created based on finishing times of reversed graph
    public void createNewForwardGraph()
    {
        for (Map.Entry<Integer, Node> entry : nodes.entrySet())
        {
            Integer key = entry.getKey();
            Node node = entry.getValue();

            // System.out.print("Key: " + key  + " ");
            // System.out.print("Values: " + node.arcs);
            // System.out.println();


            Node newNode = new Node();
            int newKey = nodesRev.get(key).finishTime;
            newNode.arcs = renumberArcs(node.arcs);

            // System.out.print("New Key: " + newKey  + " ");
            // System.out.print("New Values: " + newNode.arcs);
            // System.out.println();
            nodesNew.put(newKey, newNode);
        }      
    }

    public Set<Integer> renumberArcs(Set<Integer> arcs)
    {
        Set<Integer> newArcs = new HashSet<Integer>();
        for (Integer arc : arcs)
        {
            newArcs.add(nodesRev.get(arc).finishTime);
            // System.out.println("NEW ARC " + nodesRev.get(arc).finishTime);
        }
        return newArcs;
    }

    public void printReversedFinishTimes()
    {
        for (Map.Entry<Integer, Node> entry : nodesRev.entrySet())
        {
            Integer key = entry.getKey();
            Node value = entry.getValue();

            // System.out.print("Node Index: " + key + " ");
            // System.out.print("Finish Time: " + value.finishTime);
            // System.out.println();
        }        
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
            System.out.print("       with leaders: " + node.leader);
            System.out.println(" ");
        }

    }

}