package hw2;

import java.util.Arrays;

class  QuickSortCounterB
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

        int i = l;
        int placeholder = A[r];
        A[r] = A[i];
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
}