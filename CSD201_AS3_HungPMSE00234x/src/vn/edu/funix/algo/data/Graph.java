package vn.edu.funix.algo.data;

import vn.edu.funix.algo.FileUtil;

public class Graph {
    // Adapt to requirement
    public static final int INFINITY = 9999;

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
            if (vertices.contains(i)) continue; // already visited

            stack.push(i);
        }

        dfsTraverse(vertices, stack);
    }

    private DoublyLinkedList<String> convertVertexIntsToStrings(DoublyLinkedList<Integer> ints) {
        DoublyLinkedList<String> vertexStrings = new DoublyLinkedList<>();
        for (int i = 0; i < ints.size(); i++) {
            vertexStrings.add(Character.toString(ints.get(i) + 65));
        }
        return vertexStrings;
    }
}
