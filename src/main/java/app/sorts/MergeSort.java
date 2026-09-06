package app.sorts;

public class MergeSort implements Sorter {
    @Override public String name() { return "MergeSort"; }

    @Override
    public void sort(int[] a) 
    {
        if (a.length <= 1) return;
        int[] tmp = new int[a.length];
        ms(a, 0, a.length - 1, tmp);
    }

    private void ms(int[] a, int l, int r, int[] tmp) 
    {
        if (l >= r) return;
        int m = l + (r - l) / 2;
        ms(a, l, m, tmp);
        ms(a, m + 1, r, tmp);
        merge(a, l, m, r, tmp);
    }

    private void merge(int[] a, int l, int m, int r, int[] tmp) 
    {
        int i = l, j = m + 1, k = l;
        while (i <= m && j <= r) 
        {
            if (a[i] <= a[j]) tmp[k++] = a[i++];
            else tmp[k++] = a[j++];
        }
        while (i <= m) tmp[k++] = a[i++];
        while (j <= r) tmp[k++] = a[j++];
        for (int x = l; x <= r; x++) a[x] = tmp[x];
    }
}
