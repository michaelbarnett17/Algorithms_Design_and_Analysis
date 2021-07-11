package hw2;

import java.util.Arrays;

class  QuickSortCounterC
{
    public int comparisonCount = 0;

    // i is boundary between numbers less than and greater than the pivot
    // j is boundary between partioned and unpartioned
    public void QuickSort(int[] A, int l, int r)
    {
        if (l >= r) 
        {
            return;
        }

        int m = 0;
        if (A.length % 2 == 0) 
        {
            m = A.length / 2 - 1;
        }
        else
        {
            m = (A.length + 1) / 2;
        }

        // System.out.println("A[l] " + A[l]);
        // System.out.println("A[m] " + A[m]);
        // System.out.println("A[r] " + A[r]);

        int medianValue = middleOfThree (A[l], A[m], A[r]);
        // System.out.println("Median Value " + medianValue);

        int medianIndex = 0;

        if (medianValue == A[l])
        {
            medianIndex = l;
        }
        else if (medianValue == A[m])
        {
            medianIndex = m;
        }
        else
        {
            medianIndex = r;
        }

        // System.out.println("Median Index " + medianIndex);
        // System.out.println(" ");

        int i = l;
        int placeholder = A[medianIndex];
        A[medianIndex] = A[i];
        A[i] = placeholder;

        int j = Partition(A, l, r);

        QuickSort(A, l, j - 1);
        QuickSort(A, j + 1, r);
    }

    public int Partition(int[] A, int l, int r)
    {
        int p = A[l];
        int i = l + 1;

        for (int j = l + 1; j <= r; j++)
        {
            comparisonCount++;
            if (A[j] <= p)
            {
                int placeholder = A[j];
                A[j] = A[i];
                A[i] = placeholder;
                i++;
            }
        }
        int placeholder = A[l];
        A[l] = A[i - 1];
        A[i - 1] = placeholder;
        return (i - 1);
    }

    public int middleOfThree (int a, int b, int c)
    {
        int B[] = {a, b, c};
        java.util.Arrays.sort(B);
        // System.out.println(Arrays.toString(B));
        // System.out.println(B[1]);
        return B[1];
    }
}