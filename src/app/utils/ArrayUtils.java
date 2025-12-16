package app.util;

import java.util.Random;

public class ArrayUtils 
{
    public static int[] makeRandom(int size, int bound) 
    {
        return makeRandom(size, bound, System.nanoTime());
    }
    public static int[] makeRandom(int size, int bound, long seed) 
    {
        Random r = new Random(seed);
        int[] a = new int[size];
        for (int i = 0; i < size; i++) a[i] = r.nextInt(bound);
        return a;
    }

    public static boolean isSorted(int[] a) 
    {
        for (int i = 1; i < a.length; i++) if (a[i] < a[i - 1]) return false;
        return true;
    }

    public static void print(int[] a) 
    {
        for (int i = 0; i < a.length; i++) 
        {
            System.out.print(a[i]);
            if (i < a.length - 1) System.out.print(" ");
        }
        System.out.println();
    }
}
