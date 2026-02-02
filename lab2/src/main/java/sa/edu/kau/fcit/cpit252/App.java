package sa.edu.kau.fcit.cpit252;

import sa.edu.kau.fcit.cpit252.profiling.MemoryProfiler;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("--- Welcome to the Singleton Memory Profiler! ---");
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("⚠️  WARNING: Every 10,000 objects equal approximately 1GB of RAM usage!");
        System.out.print("How many iterations would you like to run? ");
        
        int iterations = 1000; // default
        if (scanner.hasNextInt()) {
            iterations = scanner.nextInt();
        } else {
            System.out.println("Invalid input. Defaulting to 1000 iterations.");
        }

        MemoryProfiler profiler = new MemoryProfiler();
        profiler.runComparison(iterations);

        System.out.println("\nAnd that's a wrap! Check out the README.md if you want to know the 'why' behind the numbers.");
        scanner.close();
    }
}
