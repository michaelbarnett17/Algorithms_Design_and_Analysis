package hw2;

class Homework2 
{
    public static void main(String args[])
    {
        // int[] integerArray = {8, 2, 4, 5, 7, 10};
        int sizeOfArray = 10000;
        String fileLocation = "hw2\\QuickSort.txt";

        Utilities util = new Utilities();

        // Using first in array as pivot
        int[] integerArray = util.getIntegerArray(fileLocation, sizeOfArray);
        QuickSortCounterA  quickSortCounterA = new QuickSortCounterA();
        quickSortCounterA.QuickSort(integerArray, 0, sizeOfArray - 1);
        System.out.println("Result of A (first pivot) " + quickSortCounterA.comparisonCount);
        
        // Using last in array as pivot
        integerArray = util.getIntegerArray(fileLocation, sizeOfArray);
        QuickSortCounterB  quickSortCounterB = new QuickSortCounterB();
        quickSortCounterB.QuickSort(integerArray, 0, sizeOfArray - 1);
        System.out.println("Result of B (last pivot) " + quickSortCounterB.comparisonCount);

        // Using the median of first, last, and middle as pivot
        integerArray = util.getIntegerArray(fileLocation, sizeOfArray);
        QuickSortCounterC  quickSortCounterC = new QuickSortCounterC();
        quickSortCounterC.QuickSort(integerArray, 0, sizeOfArray - 1);
        System.out.println("Result of C (last pivot) " + quickSortCounterC.comparisonCount);

        // util.printArray(integerArray);
    }
}

