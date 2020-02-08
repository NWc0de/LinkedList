/*
 * Reads test input and displays results.
 * Author: Spencer Little
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class ShellSort {

    public static void main(String[] args) {
        StringBuilder stats = new StringBuilder();
        if (args.length == 0)
            stats.append("\nResults for random10.txt:\n" + getShellSortStats("TestData/random10.txt"));
        for (String st : args) {
            stats.append("\nResults for " + st + ": \n" + getShellSortStats(st) + "\n");
        }
        System.out.println("Results: \n\n" + stats);
        writeResultsToUrl("results.txt", stats.toString());
    }

    private static String getShellSortStats(String url) {
        LinkedList<Integer> ints = readIntFile(url);
        if (ints.size() == 0) return "Unable to read data from file.\n";

        long tmr = System.nanoTime();
        String stats = ints.shellShort();

        return stats + "\n\nTime elapsed: " + (System.nanoTime() - tmr)/1000000f + " ms";
    }

    private static LinkedList<Integer> readIntFile(String url) {
        LinkedList<Integer> fileData = new LinkedList<>();
        try {
            Scanner in = new Scanner(new File(url));
            while (in.hasNextInt()) fileData.addLast(in.nextInt());

        } catch (FileNotFoundException fne) {
            System.out.println("Unable to find/open file " + url + " , is file name correct?");
        }

        return fileData;
    }

    private static void writeResultsToUrl(String url, String stats) {
        try {
            FileOutputStream out = new FileOutputStream(url);
            out.write(stats.getBytes());
        } catch (FileNotFoundException fne) {
            System.out.println("Unable to write results to file, are permissions sufficient?");
            System.exit(1);
        } catch (IOException iox) {
            System.out.println("Error occurred while writing results to file.");
            System.exit(1);
        }
    }

}
