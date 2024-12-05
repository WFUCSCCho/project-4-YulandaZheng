/****************************************************************************
 @file: Proj4.java
 @description: This program is the Project 4 main class
 @author: Yulanda Zheng
 @date: December 6, 2024
 ****************************************************************************/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Proj4 {
    public static void main(String[] args) throws IOException {
        // Use command line arguments to specify the input file
        if (args.length != 2) {
            System.err.println("Usage: java TestAvl <input file> <number of lines>");
            System.exit(1);
        }

        String inputFileName = args[0];
        int numLines = Integer.parseInt(args[1]);
        ArrayList<Cat> arr = new ArrayList<>();
        Cat kitty;
        long start;
        long end;
        long insertTime;
        long searchTime;
        long deleteTime;
        String outputLine;
        SeparateChainingHashTable<Cat> hashbrownTable = new SeparateChainingHashTable<>();

        // For file input
        FileInputStream inputFileNameStream = null;
        Scanner inputFileNameScanner = null;

        // Open the input file
        inputFileNameStream = new FileInputStream(inputFileName);
        inputFileNameScanner = new Scanner(inputFileNameStream);

        //ignore first line
        inputFileNameScanner.nextLine();

        //putting lines in an array list
        for(int i = 0; i < numLines; i++) {
            String tempLine = inputFileNameScanner.nextLine();
            String[] line = tempLine.split(",");
            kitty = new Cat(line[0], Integer.parseInt(line[1]), Integer.parseInt(line[2]),line[3],line[4]);
            arr.add(kitty);
        }

        /* NOTE
        Format of analysis.txt CVS
        (List type, number of lines, insert runtime, search runtime, delete runtime)
         */

        //Sorted list
        Collections.sort(arr);
        start = System.nanoTime();
        //Inserting into table
        for(int i = 0; i < numLines; i++) {
            hashbrownTable.insert(arr.get(i));
        }
        end = System.nanoTime();
        insertTime = end - start;

        //Searching in table
        start = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashbrownTable.contains(arr.get(i));
        }
        end = System.nanoTime();
        searchTime = end - start;

        //Deleting in table
        start = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashbrownTable.remove(arr.get(i));
        }
        end = System.nanoTime();
        deleteTime = end - start;
        //Writing to File
        outputLine = "Sorted List, " + numLines + ", " + insertTime + ", " + searchTime + ", " + deleteTime;
        System.out.println("Sorted List with " + numLines + " lines. \n\tInsert time: " + insertTime + "\n\tSearch time: " + searchTime + "\n\tDelete time: " + deleteTime);
        writeToFile(outputLine, "analysis.txt", true);


        //Shuffled list
        Collections.shuffle(arr);
        start = System.nanoTime();
        //Inserting into table
        for(int i = 0; i < numLines; i++) {
            hashbrownTable.insert(arr.get(i));
        }
        end = System.nanoTime();
        insertTime = end - start;

        //Searching in table
        start = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashbrownTable.contains(arr.get(i));
        }
        end = System.nanoTime();
        searchTime = end - start;

        //Deleting in table
        start = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashbrownTable.remove(arr.get(i));
        }
        end = System.nanoTime();
        deleteTime = end - start;
        //Writing to File
        outputLine = "Shuffled List, " + numLines + ", " + insertTime + ", " + searchTime + ", " + deleteTime;
        System.out.println("Shuffled List with " + numLines + " lines. \n\tInsert time: " + insertTime + "\n\tSearch time: " + searchTime + "\n\tDelete time: " + deleteTime);
        writeToFile(outputLine, "analysis.txt", true);


        //Reversed sorted list
        Collections.sort(arr, Collections.reverseOrder());
        start = System.nanoTime();
        //Inserting into table
        for(int i = 0; i < numLines; i++) {
            hashbrownTable.insert(arr.get(i));
        }
        end = System.nanoTime();
        insertTime = end - start;

        //Searching in table
        start = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashbrownTable.contains(arr.get(i));
        }
        end = System.nanoTime();
        searchTime = end - start;

        //Deleting in table
        start = System.nanoTime();
        for(int i = 0; i < numLines; i++) {
            hashbrownTable.remove(arr.get(i));
        }
        end = System.nanoTime();
        deleteTime = end - start;
        //Writing to file
        outputLine = "Reserve Sorted List, " + numLines + ", " + insertTime + ", " + searchTime + ", " + deleteTime;
        System.out.println("Reverse Sorted List with " + numLines + " lines. \n\tInsert time: " + insertTime + "\n\tSearch time: " + searchTime + "\n\tDelete time: " + deleteTime);
        writeToFile(outputLine, "analysis.txt", true);

    }

    //Filewriter method
    public static void writeToFile (String content, String filePath, boolean type) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath,type))){

            pw.println(content);

        } catch (IOException e) {
            System.out.println("Error writing to file");

        }
    }
}
