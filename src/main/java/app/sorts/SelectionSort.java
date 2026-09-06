package app.sorts;

public class SelectionSort implements Sorter 
{
    @Override public String name() 
    { 
        return "SelectionSort"; 
    }

    @Override
    public void sort(int[] array) 
    {
        for (int i = 0; i < array.length - 1; i++) 
        {
            int min = i;
            for (int j = i + 1; j < array.length; j++) 
            {
                if (array[min] > array[j]) min = j;
            }
            if (min != i) 
            {
                int tmp = array[i]; array[i] = array[min]; array[min] = tmp;
            }
        }
    }
}
