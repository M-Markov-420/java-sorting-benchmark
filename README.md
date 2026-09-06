# Java Sorting Benchmark App

A command-line Java application designed to benchmark and compare the performance of various sorting algorithms. The application measures execution time in nanoseconds, calculates standard deviation to ensure consistency, and verifies sorting correctness.

## 📂 Project Structure

```text
src/
└── main/
    └── java/
        └── app/
            ├── Main.java
            ├── sorts/
            │   ├── Sorter.java
            │   ├── BubbleSort.java
            │   ├── SelectionSort.java
            │   ├── MergeSort.java
            │   └── QuickSort.java
            └── util/
                ├── ArrayUtils.java
                ├── InputUtils.java
                └── Bench.java
```

## 🚀 Features

* **Algorithms Supported:**
    * **Bubble Sort:** Standard nested loop implementation.
    * **Selection Sort:** In-place selection logic.
    * **Merge Sort:** Recursive divide-and-conquer.
    * **Quick Sort:** Recursive implementation using a standard partitioning scheme.
* **Robust Benchmarking:**
    * Includes a **Warmup phase** to allow the JVM to optimize code before measurement.
    * Calculates **Average Time** and **Standard Deviation**.
    * Verifies that the final array is actually sorted; prints a warning if an algorithm fails.
* **Reproducibility:** Supports fixed seeds for random number generation to ensure different algorithms sort the exact same dataset.

## 🛠️ Build & Run

### 1. Compile
From the project root, run:
```bash
mvn compile
```

### 2. Run
Start the interactive benchmark with:
```bash
mvn exec:java
```

To create a packaged JAR, run `mvn package`. Maven writes compiled files and build output to `target/`.
## 💻 Usage

When you run the application, it will interactively ask for the following configuration:

1.  **Array size:** The number of integers to sort (e.g., `20000`).
2.  **Max value:** The exclusive upper bound for random numbers (e.g., `100000`).
3.  **Warmup runs:** How many times to run the sort without timing it (e.g., `2`).
4.  **Measured runs:** How many times to run the sort while measuring time (e.g., `5`).
5.  **Fixed seed:** * Enter a non-negative number (e.g., `42`) to use the same random array for every run.
    * Enter `-1` to generate a completely random array based on the system time.

### Example Output
```text
Array size: 10000
Max value (exclusive, e.g. 1000): 50000
Warmup runs per algorithm (e.g. 2): 3
Measured runs per algorithm (e.g. 5): 5
Fixed seed? Enter a non-negative number for reproducibility, or -1 for random: 42

Generated array of 10000 elements with seed 42
Benchmarking 5 run(s) after 3 warmup run(s).

Algorithm          Avg (ns)       StdDev (ns)        Avg (ms)
-----------------------------------------------------------------------
SelectionSort      45000000           120000          45.000
QuickSort           1500000             5000           1.500
MergeSort           1800000             8000           1.800
BubbleSort        120000000           500000         120.000
```
*(Note: The `Main.java` iterates through Selection, Quick, Merge, and Bubble sort in that specific order.)*