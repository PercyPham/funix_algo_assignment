package vn.edu.funix.algo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileUtil {

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

    static String readFromFile(String fileName) {
        String content = "";

        try {
            Scanner reader = new Scanner(new File(fileName));
            while (reader.hasNextLine())
                content += reader.nextLine() + "\n";
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