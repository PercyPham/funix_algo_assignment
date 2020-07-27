package vn.edu.funix.algo;

import vn.edu.funix.algo.data.BinarySearchTree;
import vn.edu.funix.algo.data.Graph;
import vn.edu.funix.algo.data.Traverser;

import java.util.Scanner;

public class AssignmentThree {
    static BinarySearchTree<Person> bts = new BinarySearchTree<>();

    public static void exec(String[] args) {
        while (true) {
            printMenu();
            int choice = getChoiceFromInput();
            dealWith(choice);
        }
    }

    private static void printMenu() {
        String menu = "\n";

        menu += "Choose one of this options:\n";
        menu += "Person Tree:\n";
        menu += "1. Insert a new Person.\n";
        menu += "2. Inorder traverse\n";
        menu += "3. Breadth-First Traversal traverse\n";
        menu += "4. Search by Person ID\n";
        menu += "5. Delete by Person ID\n";
        menu += "6. Balancing Binary Search Tree\n";
        menu += "7. DFS-Graph\n";
        menu += "8. Dijkstra\n";
        menu += "Exit:\n";
        menu += "0. Exit";

        System.out.println(menu);
    }

    private static int getChoiceFromInput() {
        Scanner in = new Scanner(System.in);
        System.out.print("choice=");
        int choice = in.nextInt();
        System.out.println();
        return choice;
    }

    private static void dealWith(int choice) {
        switch (choice) {
            case 1:
                // Insert a new Person.
                insertNewPerson();
                break;
            case 2:
                // Inorder traverse
                traverseInOrder();
                break;
            case 3:
                // Breadth-First Traversal traverse
                traverseBreathFirstTraversal();
                break;
            case 4:
                // Search by Person ID
                searchByID();
                break;
            case 5:
                // Delete by Person ID
                removeByID();
                break;
            case 6:
                // Balancing Binary Search Tree
                balanceCurrentBTS();
                break;
            case 7:
                // DFS_Graph
                dfsGraph();
                break;
            case 8:
                // Dijkstra
                execDijkstraSearch();
                break;
            case 0:
                // Exit
                System.out.println("Thanks!!!");
                System.exit(0);
            default:
                System.out.println("Choose again");
                break;
        }
    }

    private static void insertNewPerson() {
        Scanner in = new Scanner(System.in);

        System.out.print("Input new ID: ");
        String id = in.nextLine();

        System.out.print("Input Name: ");
        String name = in.nextLine();

        System.out.print("Input birthplace: ");
        String pob = in.nextLine();

        /// this is a weird English sentence, but it is specified in the requirement
        System.out.print("input Birth of Date: ");
        String dob = in.nextLine();

        Person person = new Person(id, name, pob, dob);

        if (!bts.contains(person)) bts.insert(person);
    }

    private static void traverseInOrder() {
        Traverser<Person> traverse = new Traverser<>();
        Object[] persons = traverse.inOrder(bts);

        for (int i = 0; i < persons.length; i++) {
            System.out.println(persons[i]);
        }
    }

    private static void traverseBreathFirstTraversal() {
        Traverser<Person> traverse = new Traverser<>();
        Object[] persons = traverse.breadthFirstTraversal(bts);

        for (int i = 0; i < persons.length; i++) {
            System.out.println(persons[i]);
        }
    }

    private static void searchByID() {
        Scanner in = new Scanner(System.in);

        System.out.print("Search for ID=");
        String id = in.nextLine();

        Person found = bts.search(new Person(id));
        System.out.println(found);
    }

    private static void removeByID() {
        Scanner in = new Scanner(System.in);

        System.out.print("Delete for ID=");
        String id = in.nextLine();

        bts.remove(new Person(id));
    }

    private static void balanceCurrentBTS() {
        bts = bts.balance();
    }

    private static void dfsGraph() {
        Graph graph = Graph.loadFromFile("Matran.txt");
        System.out.println("DFS_Graph: " + graph.depthFirstSearchTraverse().join(""));
    }

    private static void execDijkstraSearch() {
        Graph graph = Graph.loadFromFile("Matran.txt");

        graph.printWeightedMatrix();

        graph.dijkstra('A', 'E');
    }

}
