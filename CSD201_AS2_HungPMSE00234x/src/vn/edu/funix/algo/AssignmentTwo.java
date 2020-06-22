package vn.edu.funix.algo;

import vn.edu.funix.algo.data.*;

import java.util.Scanner;

public class AssignmentTwo {
    private static final String DATA_FILE = "data.txt";

    private static DoublyLinkedList<Product> products;

    public static void exec(String[] args) {
        products = new DoublyLinkedList<>();

        while (true) {
            printMenu();
            int choice = getChoiceFromInput();
            dealWith(choice);
        }
    }

    private static void printMenu() {
        String menu = "\n\n";

        menu += "Choose one of this options:\n";
        menu += "Product list:\n";
        menu += "1. Load data from file and display\n";
        menu += "2. Input & add to the end\n";
        menu += "3. Display data\n";
        menu += "4. Save product list to file\n";
        menu += "5. Search by ID\n";
        menu += "6. Delete by ID\n";
        menu += "7. Sort by ID\n";
        menu += "8. Convert to Binary\n";
        menu += "9. Load to stack and display\n";
        menu += "10. Load to queue and display\n";
        menu += "Exit:\n";
        menu += "0. Exit\n";

        System.out.println(menu);
    }

    private static int getChoiceFromInput() {
        Scanner in = new Scanner(System.in);
        System.out.print("Choice=");
        int choice = in.nextInt();
        System.out.println();
        return choice;
    }

    private static void dealWith(int choice) {
        switch (choice) {
            case 1:
                // Load data from file and display
                products = loadFromFile();
                Product.printProducts(products);
                break;
            case 2:
                // Input & add to the end
                Product product = inputNewProduct();
                products.add(product);
                break;
            case 3:
                // Display data
                Product.printProducts(products);
                break;
            case 4:
                // Save product list to file
                saveProductsToFile();
                break;
            case 5:
                // Search by ID
                handleSearchByIdCommand();
                break;
            case 6:
                // Delete by ID
                handleDeleteByIdCommand();
                break;
            case 7:
                // Sort by ID
                handleSortByIdCommand();
                break;
            case 8:
                // Convert to Binary
                handleConvertProductsNumToBinary();
                break;
            case 9:
                // Load to stack and display
                loadToStackAndDisplay();
                break;
            case 10:
                // Load to queue and display
                loadToQueueAndDisplay();
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

    private static Product inputNewProduct() {
        Scanner in = new Scanner(System.in);

        System.out.print("Input new ID: ");
        String id = in.nextLine();

        System.out.print("Input Product's Name: ");
        String name = in.nextLine();

        System.out.print("Input Product's quantity: ");
        int quantity = in.nextInt();

        System.out.print("Input Product's price: ");
        double price = in.nextDouble();

        return new Product(id, name, quantity, price);
    }

    private static void saveProductsToFile() {
        String content = "";

        Node<Product> node = products.getHead();
        while (node != null) {
            Product product = node.getData();

            String s = product.getId() +
                    "+++" + product.getName() +
                    "+++" + product.getQuantity() +
                    "+++" + product.getPrice();
            content += s + "\n";

            node = node.next();
        }

        FileUtil.writeToFile(DATA_FILE, content);

        System.out.println("Successfully!");
    }

    private static DoublyLinkedList<Product> loadFromFile() {
        DoublyLinkedList<Product> products = new DoublyLinkedList<>();
        String content = FileUtil.readFromFile(DATA_FILE);

        String[] productStrings = content.split("\r?\n|\r");
        for (int i = 0; i < productStrings.length; i++) {
            String productString = productStrings[i];

            String[] elements = productString.split("\\+\\+\\+");
            String id = elements[0];
            String name = elements[1];
            int quantity = Integer.parseInt(elements[2]);
            double price = Double.parseDouble(elements[3]);

            Product product = new Product(id, name, quantity, price);
            products.add(product);
        }
        return products;
    }

    private static void handleSearchByIdCommand() {
        Scanner in = new Scanner(System.in);

        System.out.print("Input the ID to search: ");
        String id = in.nextLine();

        Product found = findProductById(id);
        if (found == null) {
            System.out.println("Not found");
            return;
        }

        System.out.println("Result: " + found.getId() +
                " | " + found.getName() +
                " | " + found.getQuantity() +
                " | " + found.getPrice());
    }

    private static void handleDeleteByIdCommand() {
        Scanner in = new Scanner(System.in);

        System.out.print("Input the ID to delete: ");
        String id = in.nextLine();

        Product found = findProductById(id);
        if (found == null) {
            System.out.println("Not found");
            return;
        }

        products.delete(found);
        System.out.println("Deleted!");
    }

    private static Product findProductById(String id) {
        Node<Product> node = products.getHead();
        while (node != null) {
            if (node.getData().getId().equals(id)) {
                return node.getData();
            }
            node = node.next();
        }
        return null;
    }

    private static void handleSortByIdCommand() {
        products.sort();
        System.out.println("Successfully!");
    }

    private static void handleConvertProductsNumToBinary() {
        int size = products.size();
        String result = "";
        result = toBinary(size);
        System.out.println("Quantity=" + size + "=>(" + result + ")");
    }

    private static String toBinary(int i) {
        if (i == 0) return "";

        int remainder = i % 2;
        return toBinary(i / 2) + remainder;
    }

    private static void loadToStackAndDisplay() {
        DoublyLinkedList<Product> products = loadFromFile();
        Stack stack = new Stack();

        Node<Product> node = products.getHead();
        while(node != null ) {
            stack.push(node.getData());
            node = node.next();
        }

        Product.printProducts(stack);
    }

    private static void loadToQueueAndDisplay() {
        DoublyLinkedList<Product> products = loadFromFile();
        CircularQueue queue = new CircularQueue(products.size());

        Node<Product> node = products.getHead();
        while(node != null ) {
            queue.enqueue(node.getData());
            node = node.next();
        }

        Product.printProducts(queue);
    }

}
