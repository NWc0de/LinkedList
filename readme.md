# Homework 1

Insert note about running from command line here. 
```
Here is how to run from command line.
```

## Class Structure

**LinkedList** - A generic class that implements a linked list as well as
associated functionality. **Note**: "LinkedNode" (or Node as it is in 
my implementation) is an inner class of LinkedList and as such the sorting
functions were implemented as static methods of the LinkedList class so that
the Node may be accessed and mutated efficiently, as well as to preserve 
encapsulation.

**Driver** - Contains the main methods required to run the program. **Note**:
readIntFile() and the LinkedList's toString method correspond to the readValues() 
and displayList() methods from the assignment specification.