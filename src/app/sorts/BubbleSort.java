package app.sorts;

public class BubbleSort implements Sorter 
{
    @Override public String name() { return "BubbleSort"; }

    @Override
    public void sort(int[] a) 
    {
        boolean swapped;
        for (int i = 0; i < a.length - 1; i++) 
        {
            swapped = false;
            for (int j = 0; j < a.length - 1 - i; j++) 
            {
                if (a[j] > a[j + 1]) 
                {
                    int t = a[j]; a[j] = a[j + 1]; a[j + 1] = t;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
    }
}
