package vn.edu.funix.algo;

import vn.edu.funix.algo.sort.Bubble;
import vn.edu.funix.algo.sort.Insertion;
import vn.edu.funix.algo.sort.Selection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AssignmentOne {
    private static final String INPUT_FILE = "INPUT.TXT";
    private static final String BUBBLE_SORT_OUTPUT_FILE = "OUTPUT1.TXT";
    private static final String SELECTION_SORT_OUTPUT_FILE = "OUTPUT2.TXT";
    private static final String INSERTION_SORT_OUTPUT_FILE = "OUTPUT3.TXT";
    private static final String FOUND_INDEXES_OUTPUT_FILE = "OUTPUT4.TXT";

    public static void exec(String[] args) {
        int choice = -1;
        while (choice != 0) {
            printMenu();
            choice = getChoiceFromInput();
            dealWith(choice);
        }
    }

    private static void printMenu() {
        String menu = "";
        menu += "+----------Menu----------+\n";
        menu += "| 1. Input               |\n";
        menu += "| 2. Output              |\n";
        menu += "| 3. Bubble sort         |\n";
        menu += "| 4. Selection sort      |\n";
        menu += "| 5. Insertion sort      |\n";
        menu += "| 6. Search > value      |\n";
        menu += "| 7. Search = value      |\n";
        menu += "| 0. Exit                |\n";
        menu += "+------------------------+";
        System.out.println(menu);
    }

    private static int getChoiceFromInput() {
        Scanner in = new Scanner(System.in);
        System.out.print("Choice: ");
        return in.nextInt();
    }

    private static void dealWith(int choice) {
        switch (choice) {
            case 1:
                doInput();
                break;
            case 2:
                doOutput();
                break;
            case 3:
                doBubbleSort();
                break;
            case 4:
                doSelectionSort();
                break;
            case 5:
                doInsertionSort();
                break;
            case 6:
                doLinearSearch();
                break;
            case 7:
                doBinarySearch();
                break;
            case 0:
                System.out.println("Thanks!!!");
                break;
            default:
                System.out.println("Choose again");
                break;
        }
    }

    private static void doInput() {
        Scanner in = new Scanner(System.in);

        System.out.print("Input number of elements: ");
        int num = in.nextInt();
        float[] arr = new float[num];

        System.out.print("Input elements: ");
        for (int i = 0; i < num; i++) {
            arr[i] = in.nextFloat();
        }

        FileUtil.writeFloatsToFile(INPUT_FILE, arr);
    }

    private static void doOutput() {
        String content = FileUtil.readFromFile(INPUT_FILE);
        System.out.println("Read from file");
        System.out.println("Array a: " + content);
    }

    private static void doBubbleSort() {
        System.out.println("Bubble sort");

        float[] arr = FileUtil.readFloatsFromFile(INPUT_FILE);
        float[] sortedArr = Bubble.sort(arr, true);

        FileUtil.writeFloatsToFile(BUBBLE_SORT_OUTPUT_FILE, sortedArr);
    }

    private static void doSelectionSort() {
        System.out.println("Selection sort");

        float[] arr = FileUtil.readFloatsFromFile(INPUT_FILE);
        float[] sortedArr = Selection.sort(arr, true);

        FileUtil.writeFloatsToFile(SELECTION_SORT_OUTPUT_FILE, sortedArr);
    }

    private static void doInsertionSort() {
        System.out.println("Insertion sort");

        float[] arr = FileUtil.readFloatsFromFile(INPUT_FILE);
        float[] sortedArr = Insertion.sort(arr, true);

        FileUtil.writeFloatsToFile(INSERTION_SORT_OUTPUT_FILE, sortedArr);
    }

    private static void doLinearSearch() {
        float[] arr = FileUtil.readFloatsFromFile(INPUT_FILE);
        Scanner in = new Scanner(System.in);

        System.out.println("Linear Search");
        System.out.print("Input value: ");
        float val = in.nextFloat();

        String foundIndexesString = "";
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > val) foundIndexesString += i + " ";
        }
        foundIndexesString = foundIndexesString.trim(); // remove redundant last space

        System.out.println(foundIndexesString);
        FileUtil.appendToFile(FOUND_INDEXES_OUTPUT_FILE, foundIndexesString + "\n");
    }

    private static void doBinarySearch() {
        float[] arr = FileUtil.readFloatsFromFile(INPUT_FILE);
        Scanner in = new Scanner(System.in);

        System.out.println("Binary Search");
        System.out.print("Input value: ");
        float val = in.nextFloat();

        float[] sortedArr = Insertion.sort(arr);
        int foundIndex = binarySearchOnSortedArr(sortedArr, val);

        if (foundIndex < 0) {
            System.out.println("Not found");
            return;
        }

        System.out.println("Index of first element: " + foundIndex);
    }

    private static int binarySearchOnSortedArr(float[] sortedArr, float value) {
        int bottom = 0;
        int top = sortedArr.length - 1;

        while (bottom <= top) {
            int middle = bottom + (top - bottom) / 2;

            if (sortedArr[middle] == value) {
                return middle;
            } else if (sortedArr[middle] > value) {
                top = middle - 1;
            } else {
                bottom = middle + 1;
            }
        }

        return -1;
    }
}

class FileUtil {
    static void writeFloatsToFile(String fileName, float[] arr) {
        String content = "";
        for (int i = 0; i < arr.length; i++) {
            content += arr[i] + " ";
        }
        content = content.trim();

        writeToFile(fileName, content);
    }

    static void writeToFile(String fileName, String content) {
        try {
            File file = new File(fileName);
            file.delete(); // delete if file exists
            file.createNewFile();

            FileWriter writer = new FileWriter(fileName);
            writer.write(content);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    static float[] readFloatsFromFile(String fileName) {
        String content = readFromFile(fileName);
        String[] stringFloats = content.split(" ");

        float[] floats = new float[stringFloats.length];
        for (int i = 0; i < stringFloats.length; i++) {
            floats[i] = Float.parseFloat(stringFloats[i]);
        }

        return floats;
    }

    static String readFromFile(String fileName) {
        String content = "";

        try {
            Scanner reader = new Scanner(new File(fileName));
            while (reader.hasNextLine())
                content += reader.nextLine();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        return content;
    }

    public static void appendToFile(String fileName, String content) {
        try {
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(content);

            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
