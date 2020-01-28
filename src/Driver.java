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
        LinkedList<Integer> ints = readIntFile("TestData/random100.txt");
        //LinkedList<Integer> ints = new LinkedList<>(new Integer[] {150,75,43,900,123,532,75,54,123});
        System.out.println(ints);
        ints.shellShort();
        System.out.println(ints);
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
