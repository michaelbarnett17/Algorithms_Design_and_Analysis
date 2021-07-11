package hw2;
import java.util.Scanner;
import java.util.Arrays;
import java.io.File;


class Utilities
{
    public void printArray(int[] integerArray)
    {
        for (int k = 0; k < integerArray.length; k++)
        {
            System.out.println(integerArray[k]);
        }
    }   

    public int[] getIntegerArray(String fileLocation, int sizeOfArray)
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