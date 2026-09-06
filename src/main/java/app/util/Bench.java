package app.util;

import app.sorts.Sorter;

import java.util.Arrays;

public class Bench 
{

    public record Result(long avgNs, double stdDevNs, int runs, int[] lastSorted) {}

    /**
     * Benchmarks a sorter:
     * - runs 'warmup' times (not measured)
     * - then runs 'runs' times (measured)
     * Each time it sorts a fresh copy of 'base' to keep input identical.
     */
    public static Result benchmark(Sorter sorter, int[] base, int warmup, int runs) 
    {
        // Warmup
        for (int i = 0; i < warmup; i++) 
        {
            int[] copy = Arrays.copyOf(base, base.length);
            sorter.sort(copy);
        }

        long[] samples = new long[runs];
        int[] last = null;

        for (int i = 0; i < runs; i++) 
        {
            int[] copy = Arrays.copyOf(base, base.length);
            long t0 = System.nanoTime();
            sorter.sort(copy);
            long t1 = System.nanoTime();
            samples[i] = t1 - t0;
            last = copy; // keep last sorted array for verification if needed
        }

        long avg = average(samples);
        double std = stdDev(samples, avg);

        return new Result(avg, std, runs, last);
    }

    private static long average(long[] xs) 
    {
        long sum = 0;
        for (long x : xs) sum += x;
        return sum / Math.max(1, xs.length);
    }

    private static double stdDev(long[] xs, long mean) 
    {
        if (xs.length <= 1) return 0.0;
        double acc = 0.0;
        for (long x : xs) 
        {
            double d = (double)x - (double)mean;
            acc += d * d;
        }
        // sample std dev (N-1)
        return Math.sqrt(acc / (xs.length - 1));
    }
}
