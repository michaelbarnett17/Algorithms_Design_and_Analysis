package hw5;

import java.util.*;

class Node
{
    public Set<Edge> edges = new HashSet<Edge>();
    public int pathLength;

    public Node() {
        this.pathLength = 0;
    }

}