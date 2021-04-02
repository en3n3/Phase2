/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dijkstra;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author kja
 */
public class Dijkstra {
public static void main(String arg[])   { 
        //the number of vertices
        int V = 12; 
        //we start from source
        int source = 0; 
        // adjacency list representation of graph
        List<List<Node> > adj_list = new ArrayList<List<Node> >(); 
        // Initialize adjacency list for every node in the graph 
        for (int i = 0; i < V; i++) { 
            List<Node> item = new ArrayList<Node>(); 
            adj_list.add(item); 
        } 
 
   
        // Input graph edges 
        // get (Jeddah).add(to other city ,by this distence)
        adj_list.get(0).add(new Node(0, 0));
        adj_list.get(0).add(new Node(1, 79));
        adj_list.get(0).add(new Node(2, 420));
        adj_list.get(0).add(new Node(3, 949));
        adj_list.get(0).add(new Node(4, 1343));
        adj_list.get(0).add(new Node(5, 167));
        adj_list.get(0).add(new Node(6, 625));
        adj_list.get(0).add(new Node(7, 1024));
        adj_list.get(0).add(new Node(8, 863));
        adj_list.get(0).add(new Node(9, 777));
        adj_list.get(0).add(new Node(10, 710));
        adj_list.get(0).add(new Node(11, 905));
        
        
        // get (Makkah).add(to other city ,by this distence)
        adj_list.get(1).add(new Node(0, 79));
        adj_list.get(1).add(new Node(1, 0));
        adj_list.get(1).add(new Node(2, 358));
        adj_list.get(1).add(new Node(3, 870));
        adj_list.get(1).add(new Node(4, 1265));
        adj_list.get(1).add(new Node(5, 88));
        adj_list.get(1).add(new Node(6, 627));
        adj_list.get(1).add(new Node(7, 1037));
        adj_list.get(1).add(new Node(8, 876));
        adj_list.get(1).add(new Node(9, 790));
        adj_list.get(1).add(new Node(10, 785));
        adj_list.get(1).add(new Node(11, 912));
        
        // get (Madinah).add(to other city ,by this distence)
        adj_list.get(2).add(new Node(0, 420));
        adj_list.get(2).add(new Node(1, 358));
        adj_list.get(2).add(new Node(2, 0));
        adj_list.get(2).add(new Node(3, 848));
        adj_list.get(2).add(new Node(4, 1343));
        adj_list.get(2).add(new Node(5, 446));
        adj_list.get(2).add(new Node(6, 985));
        adj_list.get(2).add(new Node(7, 679));
        adj_list.get(2).add(new Node(8, 518));
        adj_list.get(2).add(new Node(9, 432));
        adj_list.get(2).add(new Node(10, 1043));
        adj_list.get(2).add(new Node(11, 1270));
        
        
        // get (Riyadh).add(to other city ,by this distence)
        adj_list.get(3).add(new Node(0, 949));
        adj_list.get(3).add(new Node(1, 870));
        adj_list.get(3).add(new Node(2, 848));
        adj_list.get(3).add(new Node(3, 0));
        adj_list.get(3).add(new Node(4, 395));
        adj_list.get(3).add(new Node(5, 782));
        adj_list.get(3).add(new Node(6, 1064));
        adj_list.get(3).add(new Node(7, 1304));
        adj_list.get(3).add(new Node(8, 330));
        adj_list.get(3).add(new Node(9, 640));
        adj_list.get(3).add(new Node(10, 1272));
        adj_list.get(3).add(new Node(11, 950));
        
        // get (Dammam).add(to other city ,by this distence)
        adj_list.get(4).add(new Node(0, 1343));
        adj_list.get(4).add(new Node(1, 1265));
        adj_list.get(4).add(new Node(2, 1343));
        adj_list.get(4).add(new Node(3, 395));
        adj_list.get(4).add(new Node(4, 0));
        adj_list.get(4).add(new Node(5, 1177));
        adj_list.get(4).add(new Node(6, 1495));
        adj_list.get(4).add(new Node(7, 1729));
        adj_list.get(4).add(new Node(8, 725));
        adj_list.get(4).add(new Node(9, 1035));
        adj_list.get(4).add(new Node(10, 1667));
        adj_list.get(4).add(new Node(11, 1345));
        
        
        // get (Taif).add(to other city ,by this distence)
        adj_list.get(5).add(new Node(0, 167));
        adj_list.get(5).add(new Node(1, 88));
        adj_list.get(5).add(new Node(2, 446));
        adj_list.get(5).add(new Node(3, 782));
        adj_list.get(5).add(new Node(4, 1177));
        adj_list.get(5).add(new Node(5, 0));
        adj_list.get(5).add(new Node(6, 561));
        adj_list.get(5).add(new Node(7, 1204));
        adj_list.get(5).add(new Node(8, 936));
        adj_list.get(5).add(new Node(9, 957));
        adj_list.get(5).add(new Node(10, 763));
        adj_list.get(5).add(new Node(11, 864));
        
        
        // get (Abha).add(to other city ,by this distence)
        adj_list.get(6).add(new Node(0, 625));
        adj_list.get(6).add(new Node(1, 627));
        adj_list.get(6).add(new Node(2, 985));
        adj_list.get(6).add(new Node(3, 1064));
        adj_list.get(6).add(new Node(4, 1459));
        adj_list.get(6).add(new Node(5, 561));
        adj_list.get(6).add(new Node(6, 0));
        adj_list.get(6).add(new Node(7, 1649));
        adj_list.get(6).add(new Node(8, 1488));
        adj_list.get(6).add(new Node(9, 1402));
        adj_list.get(6).add(new Node(10, 202));
        adj_list.get(6).add(new Node(11, 280));
        
        
        // get (Tabuk).add(to other city ,by this distence)
        adj_list.get(7).add(new Node(0, 1024));
        adj_list.get(7).add(new Node(1, 1037));
        adj_list.get(7).add(new Node(2, 679));
        adj_list.get(7).add(new Node(3, 1304));
        adj_list.get(7).add(new Node(4, 1729));
        adj_list.get(7).add(new Node(5, 1204));
        adj_list.get(7).add(new Node(6, 1649));
        adj_list.get(7).add(new Node(7, 0));
        adj_list.get(7).add(new Node(8, 974));
        adj_list.get(7).add(new Node(9, 664));
        adj_list.get(7).add(new Node(10, 1722));
        adj_list.get(7).add(new Node(11, 1929));
        
        // get (Qasim).add(to other city ,by this distence)
        adj_list.get(8).add(new Node(0, 863));
        adj_list.get(8).add(new Node(1, 876));
        adj_list.get(8).add(new Node(2, 518));
        adj_list.get(8).add(new Node(3, 330));
        adj_list.get(8).add(new Node(4, 725));
        adj_list.get(8).add(new Node(5, 936));
        adj_list.get(8).add(new Node(6, 1488));
        adj_list.get(8).add(new Node(7, 974));
        adj_list.get(8).add(new Node(8, 0));
        adj_list.get(8).add(new Node(9, 310));
        adj_list.get(8).add(new Node(10, 1561));
        adj_list.get(8).add(new Node(11, 1280));
        
        // get (Hail).add(to other city ,by this distence)
        adj_list.get(9).add(new Node(0, 777));
        adj_list.get(9).add(new Node(1, 790));
        adj_list.get(9).add(new Node(2, 432));
        adj_list.get(9).add(new Node(3, 640));
        adj_list.get(9).add(new Node(4, 1035));
        adj_list.get(9).add(new Node(5, 957));
        adj_list.get(9).add(new Node(6, 1402));
        adj_list.get(9).add(new Node(7, 664));
        adj_list.get(9).add(new Node(8, 974));
        adj_list.get(9).add(new Node(9, 0));
        adj_list.get(9).add(new Node(10, 1475));
        adj_list.get(9).add(new Node(11, 1590));
        
        // get (Jizan).add(to other city ,by this distence)
        adj_list.get(10).add(new Node(0, 710));
        adj_list.get(10).add(new Node(1, 685));
        adj_list.get(10).add(new Node(2, 1043));
        adj_list.get(10).add(new Node(3, 1272));
        adj_list.get(10).add(new Node(4, 1667));
        adj_list.get(10).add(new Node(5, 763));
        adj_list.get(10).add(new Node(6, 202));
        adj_list.get(10).add(new Node(7, 1722));
        adj_list.get(10).add(new Node(8, 1561));
        adj_list.get(10).add(new Node(9, 1475));
        adj_list.get(10).add(new Node(10, 0));
        adj_list.get(10).add(new Node(11, 482));
        
        // get (Najran).add(to other city ,by this distence)
        adj_list.get(11).add(new Node(0, 905));
        adj_list.get(11).add(new Node(1, 912));
        adj_list.get(11).add(new Node(2, 1270));
        adj_list.get(11).add(new Node(3, 950));
        adj_list.get(11).add(new Node(4, 1345));
        adj_list.get(11).add(new Node(5, 864));
        adj_list.get(11).add(new Node(6, 280));
        adj_list.get(11).add(new Node(7, 1929));
        adj_list.get(11).add(new Node(8, 1280));
        adj_list.get(11).add(new Node(9, 1590));
        adj_list.get(11).add(new Node(10, 482));
        adj_list.get(11).add(new Node(11, 0));
        
        // call Dijkstra's algo method  
        Graph_pq dpq = new Graph_pq(V); 
        dpq.algo_dijkstra(adj_list, source); 
   
        // Print the shortest path from source node to all the nodes 
        System.out.println("The shorted path from source node to other nodes:"); 
        System.out.println("Source\t\t" + "Node#\t\t" + "Distance");
        
        // to print all shortest path from jeddah to all cities
      
        for (int i = 0; i < dpq.dist.length; i++) {
              if (source==0&& i==0)
                System.out.println("Jeddah"+" \t\t"+"Jeddah"+" \t \t"+ dpq.dist[i]); //Jeddah 		Jeddah 	 	0

              else  if (source==0&&i==1)
                System.out.println("Jeddah"+" \t\t" +"Makkah"+ " \t\t"+ dpq.dist[i]);//Jeddah 		Makkah 		79
                
              else  if (source==0&&i==2)
                System.out.println("Jeddah"+" \t\t"+"Madinah"+ "\t\t"+dpq.dist[i]);//Jeddah 		Madinah		420
        
                
               else if (source==0&&i==3)
                System.out.println("Jeddah"+" \t\t"+"Riyadh"+ " \t\t"+dpq.dist[i]);//Jeddah 		Riyadh 		949
                
               else if (source==0&&i==4)
                System.out.println("Jeddah"+" \t\t"+"Dammam"+" \t\t"+ dpq.dist[i]);//Jeddah 		Dammam 		1343
                else if (source==0&&i==5)
                System.out.println("Jeddah"+" \t\t"+"Taif"+" \t\t"+ dpq.dist[i]);//Jeddah 		Taif 		167
               else if (source==0&& i==6)
                System.out.println("Jeddah"+" \t\t"+"Abha"+ " \t\t"+dpq.dist[i]);//Jeddah 		Abha 		625
                
               else if (source==0&&i==7)
                System.out.println("Jeddah"+" \t\t"+"Tabuk"+" \t\t"+ dpq.dist[i]);//Jeddah 		Tabuk 		1024
        
               else if (source==0&&i==8)
                System.out.println("Jeddah"+" \t\t"+"Qasim"+ " \t\t"+dpq.dist[i]);//Jeddah 		Qasim 		863
                
              else  if (source==0&&i==9)
                System.out.println("Jeddah"+" \t\t"+"Hail"+" \t\t"+ dpq.dist[i]);//Jeddah 		Hail 		777
                
               else if (source==0&&i==10)
                System.out.println("Jeddah"+" \t\t"+"Jizan"+ " \t\t"+dpq.dist[i]);//Jeddah 		Jizan 		710
                
               else if (source==0&&i==11)
                System.out.println("Jeddah"+" \t\t"+"Najran"+" \t\t"+ dpq.dist[i]);//Jeddah 		Najran 		905

        } 
        
    
    } 
} 

class Graph_pq { 
    int dist[]; 
    Set<Integer> visited; 
    PriorityQueue<Node> pqueue; 
    int V; // Number of vertices 
    List<List<Node> > adj_list; 
    //class constructor
    public Graph_pq(int V) { 
        this.V = V; 
        dist = new int[V]; 
        visited = new HashSet<Integer>(); 
        pqueue = new PriorityQueue<Node>(V, new Node()); 
    } 
   
    // Dijkstra's Algorithm implementation 
    public void algo_dijkstra(List<List<Node> > adj_list, int src_vertex) 
    { 
        this.adj_list = adj_list; 
   
        for (int i = 0; i < V; i++) 
            dist[i] = Integer.MAX_VALUE; 
   
        // first add source vertex to PriorityQueue 
        pqueue.add(new Node(src_vertex, 0)); 
   
        // Distance to the source from itself is 0 
        dist[src_vertex] = 0; 
        while (visited.size() != V) { 
 
   // u is removed from PriorityQueue and has min distance  
            int u = pqueue.remove().node; 
   
            // add node to finalized list (visited)
            visited.add(u); 
            graph_adjacentNodes(u); 
        } 
    } 
  // this methods processes all neighbours of the just visited node 
    private void graph_adjacentNodes(int u)   { 
        int edgeDistance = -1; 
        int newDistance = -1; 
   
        // process all neighbouring nodes of u 
        for (int i = 0; i < adj_list.get(u).size(); i++) { 
            Node v = adj_list.get(u).get(i); 
   
            //  proceed only if current node is not in 'visited'
            if (!visited.contains(v.node)) { 
                edgeDistance = v.cost; 
                newDistance = dist[u] + edgeDistance; 
   
                // compare distances 
                if (newDistance < dist[v.node]) 
                    dist[v.node] = newDistance; 
   
                // Add the current vertex to the PriorityQueue 
                pqueue.add(new Node(v.node, dist[v.node])); 
            } 
        } 
    } 
}

// Node class  
class Node implements Comparator<Node> { 
    public int node; 
    public int cost; // represented the path from source to other cities
    public Node() { } //empty constructor 
   
    public Node(int node, int cost) { // constructor 
        this.node = node; 
        this.cost = cost; 
    } 
    @Override
    public int compare(Node node1, Node node2)  //to compare the shortest path between node1 and node2
    { 
        if (node1.cost < node2.cost) 
            return -1; 
        if (node1.cost > node2.cost) 
            return 1; 
        return 0; 
    } 
}