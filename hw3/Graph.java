package hw3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class Graph
{

    int[][] A = new int[200][];

    public int[][] getGraph()
    {


        try
        {

            BufferedReader br = new BufferedReader(new FileReader("hw3\\kargerMinCut.txt"));

            for (int i = 0; i < 200; i++)
            {
                String[] st = br.readLine().trim().split("\t+");
                int [] B = new int[st.length];
                for (int j = 0; j < st.length; j++)
                {
                    B[j] = Integer.parseInt(st[j]);
                }
                A[i] = B;
            }
            //printGraph(A);
            //return A;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return A;

    }

    public void printGraph(int[][] A)
    {
           for (int[] a : A)
            {
                for (int b : a)
            {
                System.out.print(b + " ");
            }
                System.out.println();
            }
    }
}