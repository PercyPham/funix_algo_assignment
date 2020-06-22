package vn.edu.funix.algo;

import java.util.Scanner;

public class AssignmentTwo {

    private static final String DATA_FILE = "data.txt";

    public static void exec(String[] args) {
        while (true) {
            printMenu();
            int choice = getChoiceFromInput();
            dealWith(choice);
        }
    }

    private static void printMenu() {
        String menu = "";

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
                // TODO
                break;
            case 2:
                // TODO
                break;
            case 3:
                // TODO
                break;
            case 4:
                // TODO
                break;
            case 5:
                // TODO
                break;
            case 6:
                // TODO
                break;
            case 7:
                // TODO
                break;
            case 8:
                // TODO
                break;
            case 9:
                // TODO
                break;
            case 10:
                // TODO
                break;
            case 0:
                System.out.println("Thanks!!!");
                System.exit(0);
            default:
                System.out.println("Choose again");
                break;
        }
    }
}
