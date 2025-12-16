//  How to use:
/*
    from sorting-app/
        javac -d out $(find src -name "*.java")
        java -cp out app.Main

    Example prompts:
        Array size: 20000
        Max value (exclusive, e.g. 1000): 100000
        Warmup runs per algorithm (e.g. 2): 2
        Measured runs per algorithm (e.g. 5): 5
        Fixed seed? Enter a non-negative number for reproducibility, or -1 for random: 42
*/
package app;

import app.sorts.*;
import app.util.*;

import java.util.*;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);

        int size   = InputUtils.getValidInt(sc, "Array size: ");
        int bound  = InputUtils.getValidInt(sc, "Max value (exclusive, e.g. 1000): ");
        int warmup = InputUtils.getValidInt(sc, "Warmup runs per algorithm (e.g. 2): ");
        int runs   = InputUtils.getValidInt(sc, "Measured runs per algorithm (e.g. 5): ");

        System.out.print("Fixed seed? Enter a non-negative number for reproducibility, or -1 for random: ");
        long seed;
        try 
        {
            seed = sc.nextLong();
            sc.nextLine();
        } catch (InputMismatchException e) 
        {
            sc.nextLine();
            seed = -1;
        }
        boolean reproducible = seed >= 0;

        long actualSeed = reproducible ? seed : System.nanoTime();
        int[] base = ArrayUtils.makeRandom(size, bound, actualSeed);

        System.out.println("\nGenerated array of " + size + " elements"
                + (reproducible ? (" with seed " + actualSeed) : " (random seed)."));
        System.out.println("Benchmarking " + runs + " run(s) after " + warmup + " warmup run(s).\n");

        List<Sorter> sorters = List.of
        (
            new SelectionSort(),
            new QuickSort(),
            new MergeSort(),
            new BubbleSort()
        );

        System.out.printf("%-14s %15s %15s %15s%n", "Algorithm", "Avg (ns)", "StdDev (ns)", "Avg (ms)");
        System.out.println("-----------------------------------------------------------------------");

        for (Sorter s : sorters) 
        {
            Bench.Result r = Bench.benchmark(s, base, warmup, runs);
            System.out.printf("%-14s %15d %15.0f %15.3f%n",
                    s.name(), r.avgNs(), r.stdDevNs(), r.avgNs() / 1_000_000.0);

            if (!ArrayUtils.isSorted(r.lastSorted())) 
            {
                System.out.println("  -> WARNING: " + s.name() + " did not sort correctly!");
            }
        }

        sc.close();
    }
}
