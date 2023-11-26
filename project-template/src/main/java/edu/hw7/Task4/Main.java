package edu.hw7.Task4;

import edu.hw7.Task4.MultiMC;
import edu.hw7.Task4.SingleMC;

class Main {
    private Main() {
    }
    public static void main(String[] args) {
        int[] iterations = { 10000000, 100000000, 1000000000 };
        int[] threadCounts = { 1, 2, 3, 4 };

        for (int iteration : iterations) {
            System.out.println("Число итераций: " + iteration);
            runSingleThreaded(iteration);
            runMultiThreaded(iteration, threadCounts);
            System.out.println();
        }
    }

    private static void runSingleThreaded(int iteration) {
        System.out.println("Однопоточная версия:");
        SingleMC.main(new String[]{Integer.toString(iteration)});
        System.out.println();
    }

    private static void runMultiThreaded(int iteration, int[] threadCounts) {
        System.out.println("Многопоточная версия:");

        for (int threadCount : threadCounts) {
            System.out.println("Количество потоков: " + threadCount);
            MultiMC.main(new String[]{Integer.toString(iteration), Integer.toString(threadCount)});
        }

        System.out.println();
    }
}
