package sa.edu.kau.fcit.cpit252.profiling;

import sa.edu.kau.fcit.cpit252.logging.Logger;
import sa.edu.kau.fcit.cpit252.orders.Order;
import sa.edu.kau.fcit.cpit252.orders.Shipment;

import java.util.ArrayList;
import java.util.List;

public class MemoryProfiler {
    // This guy helps us grab the JVM runtime so we can peek at the memory
    private final Runtime runtime = Runtime.getRuntime();

    public void runComparison(int iterationCount) {
        System.out.println("Singleton vs Non-Singleton Memory Profiler (Absolute MB)");
        System.out.println("=====================================================");

        // First, let's see how the Singleton behaves. Spoiler: it's pretty chill.
        runSingletonTest(iterationCount);
        // Now the Non-Singleton... hold on to your RAM!
        runNonSingletonTest(iterationCount);
    }

    private void runSingletonTest(int iterationCount) {
        System.out.println("\nRunning with Singleton Logger:");
        printTableHeader();
        
        Logger.resetInstanceCount();
        for (int i = 1; i <= iterationCount; i++) {
            // We just keep asking for the same instance over and over
            Logger logger = Logger.getLoggerInstance();
            simulateApplicationLoad(logger);
            
            // Every 100 rounds, let's see where we're at
            if (i % 50 == 0 || i == iterationCount) {
                printMemoryStats(i);
            }
        }
    }

    private void runNonSingletonTest(int iterationCount) {
        System.out.println("\nRunning with Non-Singleton Logger (Storing instances):");
        printTableHeader();
        
        // We gotta store these loggers in a list so the GC doesn't delete them
        // otherwise we won't see the "weight" we're looking for
        List<Logger> loggerPool = new ArrayList<>();
        Logger.resetInstanceCount();
        for (int i = 1; i <= iterationCount; i++) {
            // New instance every single time!
            Logger logger = Logger.getNonSingletonLoggerInstance();
            loggerPool.add(logger);
            simulateApplicationLoad(logger);
            
            if (i % 50 == 0 || i == iterationCount) {
                printMemoryStats(i);
            }
        }
    }

    private void simulateApplicationLoad(Logger logger) {
        // Just making some orders and shipments to make it look like a real app
        new Order(logger);
        new Shipment("Ahmed", "248 NE. Pleasant St. Niceville, FL 32578", "123-477-0001", logger);
    }

    private void printTableHeader() {
        System.out.printf("%-10s | %-20s | %-15s\n", "Iteration", "Used Memory (MB)", "Instance Count");
        System.out.println("---------------------------------------------------------------");
    }

    private void printMemoryStats(int iteration) {
        // Cleaning up the trash so we only see the important stuff
        System.gc();
        // Give the GC a tiny bit of time to breathe
        try { Thread.sleep(100); } catch (InterruptedException ignored) {}
        
        // Calculate the absolute used memory in MB
        long usedMemoryBytes = runtime.totalMemory() - runtime.freeMemory();
        double usedMemoryMB = usedMemoryBytes / (1024.0 * 1024.0);
        
        System.out.printf("%-10d | %-20.2f | %-15d\n", iteration, usedMemoryMB, Logger.getInstanceCount());
    }
}
