package vn.edu.funix.algo.data;

public class Product implements Comparable<Product> {
    private String id;
    private String name;
    private int quantity;
    private double price;

    public Product(String id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public static void printProducts(DoublyLinkedList<Product> products) {
        ProductPrinter.printProducts(products);
    }

    public static void printProducts(Stack stack) {
        ProductPrinter.printProducts(stack);
    }

    public static void printProducts(CircularQueue queue) {
        ProductPrinter.printProducts(queue);
    }

    @Override
    public int compareTo(Product product) {
        return id.compareTo(product.getId());
    }
}

class ProductPrinter {
    static void printProducts(DoublyLinkedList<Product> products) {
        int idMaxLen = "ID".length();
        int nameMaxLen = "Title".length();
        int quanMaxLen = "Quantity".length();
        int priceMaxLen = "Price".length();

        Node<Product> node = products.getHead();
        while (node != null) {
            Product product = node.getData();
            int idLength = product.getId().length();
            int nameLength = product.getName().length();
            int quanLength = Integer.toString(product.getQuantity()).length();
            int priceLength = Double.toString(product.getPrice()).length();

            if (idLength > idMaxLen) idMaxLen = idLength;
            if (nameLength > nameMaxLen) nameMaxLen = nameLength;
            if (quanLength > quanMaxLen) quanMaxLen = quanLength;
            if (priceLength > priceMaxLen) priceMaxLen = priceLength;

            node = node.next();
        }

        printHeaders(idMaxLen, nameMaxLen, quanMaxLen, priceMaxLen);
        printHorizonBar(idMaxLen + nameMaxLen + quanMaxLen + priceMaxLen + 9);

        node = products.getHead();
        while (node != null) {
            printProduct(node.getData(), idMaxLen, nameMaxLen, quanMaxLen, priceMaxLen);
            node = node.next();
        }
    }

    static void printProducts(Stack stack) {
        System.out.println("ID   | Name   | Quantity | Price");
        System.out.println("--------------------------------");
        while(!stack.isEmpty()) {
            Product product = stack.pop();
            System.out.println(product.getId() +
                    " | " + product.getName() +
                    " | " + product.getQuantity() +
                    " | " + product.getPrice());
        }
    }

    static void printProducts(CircularQueue queue) {
        System.out.println("ID   | Name   | Quantity | Price");
        System.out.println("--------------------------------");
        while(!queue.isEmpty()) {
            Product product = queue.dequeue();
            System.out.println(product.getId() +
                    " | " + product.getName() +
                    " | " + product.getQuantity() +
                    " | " + product.getPrice());
        }
    }

    static void printHeaders(int idMaxLen, int nameMaxLen, int quanMaxLen, int priceMaxLen) {
        String headers = fmt("ID", idMaxLen) +
                " | " + fmt("Title", nameMaxLen) +
                " | " + fmt("Quantity", quanMaxLen) +
                " | " + fmt("Price", priceMaxLen);
        System.out.println(headers);
    }

    static void printHorizonBar(int length) {
        String bar = "";
        for (int i = 0; i < length; i++) bar += "-";
        System.out.println(bar);
    }

    private static void printProduct(Product product, int idMaxLen, int nameMaxLen, int quanMaxLen, int priceMaxLen) {
        String id = product.getId();
        String name = product.getName();
        String quantity = Integer.toString(product.getQuantity());
        String price = Double.toString(product.getPrice());

        String s = fmt(id, idMaxLen) +
                " | " + fmt(name, nameMaxLen) +
                " | " + fmt(quantity, quanMaxLen) +
                " | " + fmt(price, priceMaxLen);

        System.out.println(s);
    }

    // return String with added spaces to have length of maxLen
    static String fmt(String s, int len) {
        String result = "";
        result += s;
        for (int i = s.length(); i < len; i++) result += " ";
        return result;
    }
}
