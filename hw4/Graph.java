package hw4;

import java.io.*;
import java.util.*;

class Graph
{
    int nodeQty = 9;
    // int nodeQty = 875714;

    int upperBoundsLeaderQty = 10;
    // int upperBoundsLeaderQty = 1000000;

    String fileName;

    // Forward Graph
    ArrayList<Edge> edges;
    HashMap<Integer, Node> nodes;

    // Reversed Graph
    ArrayList<Edge> edgesRev;
    HashMap<Integer, Node> nodesRev;

    // Renumbered Forward Graph
    HashMap<Integer, Node> nodesNew;
    
    // Order by finishing time
    List<Node> orderedNodes;

    // For First Pass
    Integer t;

    // For Second Pass
    Integer s;

    List<Integer> leaders;

    public Graph(String fileName)
    {
        this.fileName = fileName;
        this.edges = new ArrayList<Edge>();
        this.nodes = new HashMap<Integer, Node>();
        this.edgesRev = new ArrayList<Edge>();
        this.nodesRev = new HashMap<Integer, Node>();
        this.nodesNew = new HashMap<Integer, Node>();
        this.orderedNodes = new ArrayList<Node>();
        this.t = 0;
        this.s = null;
        this.leaders = new ArrayList<Integer>(Collections.nCopies(upperBoundsLeaderQty, 0));
    }

    public void getEdges(String direction, ArrayList<Edge> edges)
    {
        try
        {   
            BufferedReader br = new BufferedReader(new FileReader(fileName));
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

            Node newNode = new Node();
            int newKey = nodesRev.get(key).finishTime;
            newNode.arcs = renumberArcs(node.arcs);

            nodesNew.put(newKey, newNode);
        }      
    }

    public Set<Integer> renumberArcs(Set<Integer> arcs)
    {
        Set<Integer> newArcs = new HashSet<Integer>();
        for (Integer arc : arcs)
        {
            newArcs.add(nodesRev.get(arc).finishTime);
        }
        return newArcs;
    }

    public void populateLeaders()
    {
        for (Map.Entry<Integer, Node> entry : nodesNew.entrySet())
        {
            int leader = entry.getValue().leader;
            int newValue = leaders.get(leader) + 1;

            leaders.set(leader, newValue);
        }      
    }

    public void findFiveLargestSCCs() 
    {
        for(int i = 0; i < 5; i++)
        {
            Integer maxValue = Collections.max(leaders);
            System.out.println(maxValue);
            leaders.remove(leaders.indexOf(maxValue));
        }
    }

    public void printReversedFinishTimes()
    {
        for (Map.Entry<Integer, Node> entry : nodesRev.entrySet())
        {
            Integer key = entry.getKey();
            Node value = entry.getValue();
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