
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import javafx.util.Pair;

public class Phace2 {

    //static int vertices;
    static class Edge {

        int source;
        int destination;
        public int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class HeapNode {

        int vertex;
        int key;
    } //for Prime minheab

    static class ResultSet {

        int parent;
        int weight;
    } //for two Prime

    static class Graph {
        //(here instaed of making two Graph class, one for Kruskal and one for Prime
        //we take all the varibles and put them in one Graph class)
        static int total_min_weight = 0; //for minimum spaning
        static LinkedList<Edge>[] adjacencylist;//for Prime. List of adjacent nodes of a given vertex
        static ArrayList<Edge> allEdges = new ArrayList<>();//for Kruskal
        static int edges, v1, v2;
        static int vertices;
        
        // method to add an edge between two vertices
        //(here too, put each way of adding edges for Keuskal and Prinm in one method)
        public static void addEgde(int source, int destination, int weight) {
            Edge edge = new Edge(source, destination, weight);
            allEdges.add(edge); //add to total edges. for Krus
            adjacencylist[source].addFirst(edge);
            edge = new Edge(destination, source, weight);
            adjacencylist[destination].addFirst(edge); //for undirected graph. For Prime
        }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //KRUSKAL
        public static void kruskalMST() {
           long startk = System.currentTimeMillis();
            PriorityQueue<Edge> pq = new PriorityQueue<>(allEdges.size(), Comparator.comparingInt(o -> o.weight));

            //add all the edges to priority queue, //sort the edges on weights
            for (int i = 0; i < allEdges.size(); i++) {
                pq.add(allEdges.get(i));
            }

            //create a parent []
            int[] parent = new int[vertices];

            //makeset
            makeSet(parent);

            ArrayList<Edge> mst = new ArrayList<>();

            //process vertices - 1 edges
            int index = 0;
            while (index < vertices-1 ) {
                Edge edge = pq.remove();
                //check if adding this edge creates a cycle
                int x_set = find(parent, edge.source);
                int y_set = find(parent, edge.destination);

                if (x_set == y_set) {
                    //ignore, will create cycle
                } else {
                    //add it to our final result
                    mst.add(edge);
                    index++;
                    union(parent, x_set, y_set);
                }
            }
            //(after print the total minimum spaning the time will end
            //to calculate how long did it take)
            printGraph(mst);
            long end = System.currentTimeMillis();
            long elapsedTime = end - startk;
            System.out.println("total time is: " + elapsedTime);
            System.out.println("-----------------------*********-----------------------");
            System.out.println();
        }

        public static void makeSet(int[] parent) {
            //Make set- creating a new element with a parent pointer to itself.
            for (int i = 0; i < vertices; i++) {
                parent[i] = i;
            }
        } //for Krua

        public static int find(int[] parent, int vertex) {
            //chain of parent pointers from x upwards through the tree
            // until an element is reached whose parent is itself
            if (parent[vertex] != vertex) {
                return find(parent, parent[vertex]);
            };
            return vertex;
        } //for Krua

        public static void union(int[] parent, int x, int y) {
            int x_set_parent = find(parent, x);
            int y_set_parent = find(parent, y);
            //make x as parent of y
            parent[y_set_parent] = x_set_parent;
        } //for Krua

        public static void printGraph(ArrayList<Edge> edgeList) {
            int total_min_weight = 0;
            for (int i = 0; i < edgeList.size(); i++) {
                Edge edge = edgeList.get(i);
                //system.out.println("Edge-" + i + " source: " + edge.source
                   //     + " destination: " + edge.destination
                   //   + " weight: " + edge.weight);
                
                //(the orginal code didn't calculated the total of minimum spaning
                //so we calculate it).
                total_min_weight += edgeList.get(i).weight;
            }
            System.out.println("-----------------------****KRUSKAL****-----------------------");
            System.out.println("Total minimum key for KRUSKAL: " + total_min_weight);
        } //for Krus
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //PRIME PriorityQueue
        // method used to find the mst
        public static void primMST() {
            long startpq = System.currentTimeMillis();
            boolean[] mst = new boolean[vertices]; // check a vertex is in PriorityQueue or not
            ResultSet[] resultSet = new ResultSet[vertices];
            int[] key = new int[vertices];  //keys used to store the key to know whether priority queue update is required

            //Initialize all the keys to infinity and
            //initialize resultSet for all the vertices
            for (int i = 0; i < vertices; i++) {
                key[i] = Integer.MAX_VALUE;
                resultSet[i] = new ResultSet();
            }

            //Initialize priority queue
            //override the comparator to do the sorting based keys
            PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(vertices, new Comparator<Pair<Integer, Integer>>() {
                @Override
                public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
                    //sort using key values
                    int key1 = p1.getKey();
                    int key2 = p2.getKey();
                    return key1 - key2;
                }
            });
            //create the pair for for the first index, 0 key 0 index
            key[0] = 0;
            Pair<Integer, Integer> p0 = new Pair<>(key[0], 0);
            //add it to pq
            pq.offer(p0);

            resultSet[0] = new ResultSet();
            resultSet[0].parent = -1;

            //while priority queue is not empty
            while (!pq.isEmpty()) {
                //extract the min
                Pair<Integer, Integer> extractedPair = pq.poll();

                //extracted vertex
                int extractedVertex = extractedPair.getValue();
                mst[extractedVertex] = true;

                //iterate through all the adjacent vertices and update the keys
                LinkedList<Edge> list = adjacencylist[extractedVertex];
                for (int i = 0; i < list.size(); i++) {
                    Edge edge = list.get(i);
                    //only if edge destination is not present in mst
                    if (mst[edge.destination] == false) {
                        int destination = edge.destination;
                        int newKey = edge.weight;
                        //check if updated key < existing key, if yes, update if
                        if (key[destination] > newKey) {
                            //add it to the priority queue
                            Pair<Integer, Integer> p = new Pair<>(newKey, destination);
                            pq.offer(p);
                            //update the resultSet for destination vertex
                            resultSet[destination].parent = extractedVertex;
                            resultSet[destination].weight = newKey;
                            //update the key[]
                            key[destination] = newKey;
                        }
                    }
                }
            }
            //print mst
            //(after print the total minimum spaning the time will end
            //to calculate how long did it take)
            printMST(resultSet);
            long end = System.currentTimeMillis();
            long elapsedTimepq = end - startpq;
            System.out.println("total time for Prime PriorityQueue is :" + elapsedTimepq);
            System.out.println("-----------------------*********-----------------------");
            System.out.println();
        }
        public static void printMST(ResultSet[] resultSet) {
            int total_min_weight = 0;
            for (int i = 1; i < vertices; i++) {
                //System.out.println("Edge: " + i + " - " + resultSet[i].parent
                //        + " weight: " + resultSet[i].weight);
                total_min_weight += resultSet[i].weight;
            }
            System.out.println("-----------------------**Prime PriorityQueue**-----------------------");
            System.out.println("Total minimum key for Prime PriorityQueue is: " + total_min_weight);
        } //print for two Primes
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //PRIME MINHEAP
        public static void primMin() {
            long start = System.currentTimeMillis();
            boolean[] inHeap = new boolean[vertices];
            ResultSet[] resultSet = new ResultSet[vertices];
            //keys[] used to store the key to know whether min hea update is required
            int[] key = new int[vertices];
//          //create heapNode for all the vertices
            HeapNode[] heapNodes = new HeapNode[vertices];
            for (int i = 0; i < vertices; i++) {
                heapNodes[i] = new HeapNode();
                heapNodes[i].vertex = i;
                heapNodes[i].key = Integer.MAX_VALUE;
                resultSet[i] = new ResultSet();
                resultSet[i].parent = -1;
                inHeap[i] = true;
                key[i] = Integer.MAX_VALUE;
            }

            //decrease the key for the first index
            heapNodes[0].key = 0;

            //add all the vertices to the MinHeap
            MinHeap minHeap = new MinHeap(vertices);
            //add all the vertices to priority queue
            for (int i = 0; i < vertices; i++) {
                minHeap.insert(heapNodes[i]);
            }

            //while minHeap is not empty
            while (!minHeap.isEmpty()) {
                //extract the min
                HeapNode extractedNode = minHeap.extractMin();

                //extracted vertex
                int extractedVertex = extractedNode.vertex;
                inHeap[extractedVertex] = false;

                //iterate through all the adjacent vertices
                LinkedList<Edge> list = adjacencylist[extractedVertex];
                for (int i = 0; i < list.size(); i++) {
                    Edge edge = list.get(i);
                    //only if edge destination is present in heap
                    if (inHeap[edge.destination]) {
                        int destination = edge.destination;
                        int newKey = edge.weight;
                        //check if updated key < existing key, if yes, update if
                        if (key[destination] > newKey) {
                            decreaseKey(minHeap, newKey, destination);
                            //update the parent node for destination
                            resultSet[destination].parent = extractedVertex;
                            resultSet[destination].weight = newKey;
                            key[destination] = newKey;
                        }
                    }
                }
            }
            //print mst
            //(after print the total minimum spaning the time will end
            //to calculate how long did it take)
            printPMIN(resultSet);
            long end = System.currentTimeMillis();
            long elapsedTimepq = end - start;
            System.out.println("total time for Prime minheap is :" + elapsedTimepq);
            System.out.println("-----------------------*********-----------------------");
            System.out.println();
        }
        public static void decreaseKey(MinHeap minHeap, int newKey, int vertex) {

            //get the index which key's needs a decrease;
            int index = minHeap.indexes[vertex];

            //get the node and update its value
            HeapNode node = minHeap.mH[index];
            node.key = newKey;
            minHeap.bubbleUp(index);
        }
        public static void printPMIN(ResultSet[] resultSet) {
            int total_min_weight = 0;
            for (int i = 1; i < vertices; i++) {
                //System.out.println("Edge: " + i + " - " + resultSet[i].parent
                //        + " weight: " + resultSet[i].weight);
                total_min_weight += resultSet[i].weight;
            }
            System.out.println("-----------------------***Prime minheap***-----------------------");
            System.out.println("Total minimum key for minheap is: " + total_min_weight);
        } //print for two Primes
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////      
        public static void makeGraph(int vertices, int edge) {
            //(adjacency list was initializing in Graph class
            //we put it here so makeGraph method can do its work).
            // An adjacency list to represent a graph
            adjacencylist = new LinkedList[vertices];
            //initialize adjacency lists for all the vertices
            for (int i = 0; i < vertices; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
            // A Random instance to generate random values
            Random rand = new Random();
            // A for loop to randomly generate edges
            for (int i = 0; i < edge; i++) {
                // get two vertices check wheather they are already connected
                // or not. If connected then create another pair and check again
                v1 = rand.nextInt(vertices);
                v2 = rand.nextInt(vertices);
                if (v1 == v2) {
                    continue;
                }
                // generate a weight
                int weight = rand.nextInt(5) + 1;
                // add edges
                addEgde(v1, v2, weight);
            }
            System.out.println("Completed ..... ");
        }
/////////////////////////////////////////////////////////////////////////////////////////////////
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            System.out.print("enter the number of vertex: ");
            vertices = sc.nextInt(); // no of nodes in a graph
            System.out.print("enter the no of edges: ");
            int edges = sc.nextInt(); // no of edges in a graph
            
            //calling methods
            makeGraph(vertices, edges);
            primMST();
            primMin();
            kruskalMST();
            

        }
    }

    static class MinHeap {

        int capacity;
        int currentSize;
        HeapNode[] mH;
        int[] indexes; //will be used to decrease the key

        public MinHeap(int capacity) {
            this.capacity = capacity;
            mH = new HeapNode[capacity + 1];
            indexes = new int[capacity];
            mH[0] = new HeapNode();
            mH[0].key = Integer.MIN_VALUE;
            mH[0].vertex = -1;
            currentSize = 0;
        }

        public void display() {
            for (int i = 0; i <= currentSize; i++) {
                System.out.println(" " + mH[i].vertex + "   key   " + mH[i].key);
            }
            System.out.println("________________________");
        }

        public void insert(HeapNode x) {
            currentSize++;
            int idx = currentSize;
            mH[idx] = x;
            indexes[x.vertex] = idx;
            bubbleUp(idx);
        }

        public void bubbleUp(int pos) {
            int parentIdx = pos / 2;
            int currentIdx = pos;
            while (currentIdx > 0 && mH[parentIdx].key > mH[currentIdx].key) {
                HeapNode currentNode = mH[currentIdx];
                HeapNode parentNode = mH[parentIdx];

                //swap the positions
                indexes[currentNode.vertex] = parentIdx;
                indexes[parentNode.vertex] = currentIdx;
                swap(currentIdx, parentIdx);
                currentIdx = parentIdx;
                parentIdx = parentIdx / 2;
            }
        }

        public HeapNode extractMin() {
            HeapNode min = mH[1];
            HeapNode lastNode = mH[currentSize];
//            update the indexes[] and move the last node to the top
            indexes[lastNode.vertex] = 1;
            mH[1] = lastNode;
            mH[currentSize] = null;
            sinkDown(1);
            currentSize--;
            return min;
        }

        public void sinkDown(int k) {
            int smallest = k;
            int leftChildIdx = 2 * k;
            int rightChildIdx = 2 * k + 1;
            if (leftChildIdx < heapSize() && mH[smallest].key > mH[leftChildIdx].key) {
                smallest = leftChildIdx;
            }
            if (rightChildIdx < heapSize() && mH[smallest].key > mH[rightChildIdx].key) {
                smallest = rightChildIdx;
            }
            if (smallest != k) {

                HeapNode smallestNode = mH[smallest];
                HeapNode kNode = mH[k];

                //swap the positions
                indexes[smallestNode.vertex] = k;
                indexes[kNode.vertex] = smallest;
                swap(k, smallest);
                sinkDown(smallest);
            }
        }

        public void swap(int a, int b) {
            HeapNode temp = mH[a];
            mH[a] = mH[b];
            mH[b] = temp;
        }

        public boolean isEmpty() {
            return currentSize == 0;
        }

        public int heapSize() {
            return currentSize;
        }
    } //for Prime minheap

}
