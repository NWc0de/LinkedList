/*
 * A set of unit tests demonstrating the correctness of the sorting methods in LinkedList.java.
 * Author: Spencer Little
 */

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A set of unit tests covering shell and bubble sort in the LinkedList class.
 * @author Spencer Little
 */
public class TestSort {

    String[] testUrls = {
            "TestData/random10.txt",
            "TestData/random100.txt",
            "TestData/random1000.txt",
            "TestData/random10000.txt",
            "TestData/inorder100.txt",
            "TestData/inorder1000.txt",
            "TestData/inorder10000.txt",
            "TestData/reverse100.txt",
            "TestData/reverse1000.txt",
            "TestData/reverse10000.txt"};

    @Test
    public void testShellSort() {
        for (String url : testUrls) {
            LinkedList<Integer> test = readIntFile(url);
            test.shellShort();
            int prev = test.get(0), curr;
            for (int i = 1; i < test.size(); i++) {
                curr = test.get(i);
                Assert.assertTrue(prev < curr);
                prev = curr;
            }
        }
    }

    @Test
    public void testBubbleSort() {
        for (String url : testUrls) {
            LinkedList<Integer> test = readIntFile(url);
            test.bubbleSort();
            int prev = test.get(0), curr;
            for (int i = 1; i < test.size(); i++) {
                curr = test.get(i);
                Assert.assertTrue(prev < curr);
                prev = curr;
            }
        }
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
}
