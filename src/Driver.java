/*
 * Reads test input and displays results.
 * Author: Spencer Little
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

    //TODO: Allow files to be passed on command line
    //TODO: Output stats: comparisons, swaps, and seconds
    public static void main(String[] args) {
        LinkedList<Integer> ints = readIntFile("TestData/random10000.txt");
        //LinkedList<Integer> ints = new LinkedList<>(new Integer[] {150,75,43,900,123,532,75,54,123,53});
        // LinkedList<Integer> ints = new LinkedList<>(new Integer[] {18, 79, 46, 75, 99, 91, 98, 53, 10, 23});

        long start = System.nanoTime();
        String stats = ints.shellShort();
        long elapsed = System.nanoTime() - start;
        stats += "\n\nTime elapsed: " + elapsed/1000000f + " ms";

        System.out.println(stats);
    }

    private static LinkedList<Integer> readIntFile(String fName) {
        LinkedList<Integer> fileData = new LinkedList<>();
        try {
            Scanner in = new Scanner(new File(fName));
            while (in.hasNextInt()) fileData.addLast(in.nextInt());

        } catch (FileNotFoundException fne) {
            System.out.println("Unable to find/open file, is file name correct?");
            System.exit(1);
        }

        return fileData;
    }

    private static String linkedListToString(LinkedList<Integer> in) {
        return in.toString();
    }

}
