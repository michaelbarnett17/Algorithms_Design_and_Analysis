package hw6;

import java.util.*;

class Homework6_part1
{
    public static void main(String args[])
    {
        String fileName = "hw6/algo1-programming_prob-2sum.txt";
        int graphSize = 1000000;

        TwoSumCalculator twoSumCalculator = new TwoSumCalculator(fileName);
        twoSumCalculator.populateArray();

    }
}