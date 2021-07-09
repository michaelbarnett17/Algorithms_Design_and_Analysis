package hw1;

import java.util.Scanner;
import java.util.Arrays;
import java.io.File;


class InversionCounter {

    String fileLocation = "hw1\\IntegerArray.txt";
    int sizeOfArray = 100000;
    long inversionCount = 0;


    public void countInversions()
    {
        int[] sortedArry = new int[sizeOfArray];
        //int[] integerArray = {6,5,4,3,2,1};
        int[] integerArray = getIntegerArray();

        sortedArry = mergeSort(integerArray);
        //printArray(sortedArry);
        System.out.println("Total number of inversions: " + inversionCount);
    }

    // A is input Array
    // B is 1st half of A
    // C is 2nd half of A
    public int[] mergeSort(int A[])
    {
        if (A.length == 1)
        {
            return A;
        }

        int[] B = Arrays.copyOfRange(A, 0, A.length/2);
        B = mergeSort(B);

        int[] C = Arrays.copyOfRange(A, A.length/2, A.length);
        C = mergeSort(C);

        return mergeArrays(B, C);
    }

    // B is first half of Array
    // C is second half of Array
    // D is B and C merged
    public int[] mergeArrays(int[] B, int[] C)
    {
        // i is index for B
        // j is index for C
        // k is index for D
        int i = 0;
        int j = 0;

        int[] D = new int[B.length + C.length];

        for (int k = 0; k < D.length; k++)
        {
            // If A is done being processed, keep processing B
            if (i == B.length)
            {
                D[k] = C[j];
                j += 1;
                continue;
            }

            // If B is done being processed, keep processing A 
            else if (j == C.length)
            {
                D[k] = B[i];
                i += 1;
                continue;
            }

            else if (B[i] < C[j])
            {
                D[k] = B[i];
                i += 1;
            }

            else
            {
                D[k] = C[j];
                inversionCount += B.length - i;
                j += 1;

            }
        }
        return D;
    }

    public void printArray(int[] X)
    {
        for (int i = 0; i < X.length; i++)
        {
            System.out.println(X[i]);
        }
    }

    public int[] getIntegerArray()
    {
        int[] integers = new int[sizeOfArray];

        try
        {
            Scanner scanner = new Scanner(new File(fileLocation));
            int i = 0;
            while(scanner.hasNextInt())
            {

                integers[i++] = scanner.nextInt();

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //printArray(integers);
        return integers;
    }
}