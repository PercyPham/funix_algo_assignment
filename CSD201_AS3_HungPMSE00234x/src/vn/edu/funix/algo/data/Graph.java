package vn.edu.funix.algo.data;

import vn.edu.funix.algo.FileUtil;

public class Graph {
    // Adapt to requirement
    private static final int INFINITY = 9999;
    private static final int NO_PARENT = -1;

    private int vertexNum;
    private int[][] edges;

    private Graph(int[][] edges) {
        this.edges = edges;
    }

    public static Graph loadFromFile(String path) {
        DoublyLinkedList<String> lines = FileUtil.read(path);
        int vertexNum = Integer.parseInt(lines.get(0));

        int[][] edges = new int[vertexNum][];

        for (int i = 0; i < vertexNum; i++) {
            edges[i] = new int[vertexNum];
            String[] stringDistances = lines.get(i + 1).split(" ");
            for (int j = 0; j < vertexNum; j++) {
                edges[i][j] = Integer.parseInt(stringDistances[j]);
            }
        }

        return new Graph(edges);
    }

    public DoublyLinkedList<String> depthFirstSearchTraverse() {
        DoublyLinkedList<Integer> vertices = new DoublyLinkedList<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(0);

        dfsTraverse(vertices, stack);

        return convertVertexIntsToStrings(vertices);
    }

    private void dfsTraverse(DoublyLinkedList<Integer> vertices, Stack<Integer> stack) {
        Integer vertex = stack.pull();
        while (vertices.contains(vertex)) vertex = stack.pull(); // ignore visited vertex

        if (vertex == null) return; // stack is empty

        vertices.add(vertex);

        int[] edges = this.edges[vertex];
        for (int i = edges.length - 1; i >= 0; i--) {
            if (vertex == i) continue; // same vertex
            if (this.edges[vertex][i] == INFINITY) continue; // not connected

            stack.push(i);
        }

        dfsTraverse(vertices, stack);
    }

    private DoublyLinkedList<String> convertVertexIntsToStrings(DoublyLinkedList<Integer> ints) {
        DoublyLinkedList<String> vertexStrings = new DoublyLinkedList<>();
        for (int i = 0; i < ints.size(); i++) {
            vertexStrings.add(nameOf(ints.get(i)));
        }
        return vertexStrings;
    }

    /**
     * convert int to String name of vertex
     */
    private String nameOf(int vertex) {
        return Character.toString(vertex + 65);
    }

    public void printWeightedMatrix() {
        int vertexNum = this.edges.length;

        System.out.println("================================");
        for (int i = 0; i < vertexNum; i++) {
            String line = "";
            for (int j = 0; j < vertexNum; j++) {
                String s = this.edges[i][j] == INFINITY ? "INF" : Integer.toString(this.edges[i][j]);
                line += String.format("%-5s", s);
            }
            System.out.println(line);
        }

    }

    public void dijkstra(char source, char destination) {
        int src = (int) source - 65;
        int des = (int) destination - 65;

        System.out.println("\nDijkstra algorithm for shortest path from " + source + " to  " + destination + ":\n");

        int vertexNum = this.edges.length;

        int[] dist = new int[vertexNum];
        Boolean[] sptSet = new Boolean[vertexNum];
        int[] parents = new int[vertexNum];

        for (int i = 0; i < vertexNum; i++) {
            dist[i] = INFINITY;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;
        parents[src] = NO_PARENT;

        for (int count = 0; count < vertexNum - 1; count++) {
            int u = minDistance(dist, sptSet);
            if (dist[u] == INFINITY) {
                // no more connected vertex
                break;
            }

            sptSet[u] = true;
            if (u == des) {
                // found shortest path to destination
                break;
            }

            for (int v = 0; v < vertexNum; v++) {
                boolean inSptSet = sptSet[v];
                boolean isSameVertex = this.edges[u][v] == 0;
                boolean isConnected = this.edges[u][v] != INFINITY;
                int newDistance = dist[u] + this.edges[u][v];
                boolean foundShorterPath = newDistance < dist[v];

                if (!inSptSet && !isSameVertex && isConnected && foundShorterPath) {
                    parents[v] = u;
                    dist[v] = newDistance;
                }
            }
        }

        if (dist[des] == INFINITY) {
            System.out.println("Cannot find path from " + source + " to " + destination);
            return;
        }

        System.out.println("The length of shortest path from " + source + " to  " + destination + " is " + dist[des]);
        System.out.println("Path:");
        printPath(des, parents);
    }

    private void printPath(int des, int[] parents) {
        DoublyLinkedList<String> vertices = new DoublyLinkedList<>();
        int currentVertex = des;

        while (currentVertex != NO_PARENT) {
            vertices.addFirst(nameOf(currentVertex));
            currentVertex = parents[currentVertex];
        }

        System.out.println(vertices.join(" -> "));
    }

    private int minDistance(int[] dist, Boolean[] sptSet) {
        int min = INFINITY;
        int minIdx = -1;
        for (int i = 0; i < dist.length; i++) {
            if (sptSet[i] == false && dist[i] < min) {
                min = dist[i];
                minIdx = i;
            }
        }
        return minIdx;
    }
}
