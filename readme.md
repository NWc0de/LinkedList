# Homework 1

The program may be run from the command line by providing the url to the test file(s) as an argument. By default the program will run the shell sort tests, report statistics to stdout and generate a file 'results.txt' in the working directory containing the results. 
```
java Shellsort.java [test file url(s)...]
```
The program may be run with no arguments to test the output on the elements from 'random10.txt' in the assignment specification.

**Note**: the ShellSort.java file corresponds to the Main.java file from the assignment specification.

## Class Structure

**LinkedList** - A generic class that implements a singly linked list as well as
associated functionality. **Note**: "LinkedNode" (or Node as it is in 
my implementation) is an inner class of LinkedList so the sorting
functions were implemented as methods of the LinkedList class itself so that
the Nodes may be accessed and mutated efficiently, as well as to preserve 
encapsulation. Since these methods are part of the LinkedList class, they do not require passing the root node as they can directly access it. 

**ShellSort** - Contains the main methods required to run the program. **Note**:
readIntFile() and the LinkedList's toString method correspond to the readValues() 
and displayList() methods from the assignment specification.

**TestSort** - Contains a set of unit tests demonstrating the correctness of the bubble and shell sort in LinkedList.