package hw4;

import java.util.*;

class Node
{
    public Set<Integer> arcs = new HashSet<Integer>();
    public boolean explored = false;

    public void printArcs()
    {
        System.out.print(" arcs: ");
        for(Integer arcId : arcs)
        {
            System.out.print(arcId + " ");
        }
    }
}