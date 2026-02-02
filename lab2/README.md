# Singleton vs. Non-Singleton: A Memory Profiler Story

## Overview
This project is here to show you exactly why we use the **Singleton Design Pattern**. We're going to compare it against a **Non-Singleton** approach and watch the memory usage go crazy. 

Think of it as simulating a **Database Connection (DBC)** pool. In a real app, you don't want to open a new connection to the database every time you log a message or create an order—that's a one-way ticket to "Out of Memory" land!

## How we adapted Lab 2
We didn't just stick to the basic Lab 2 code; we leveled it up to fit this profiling task:
1. **The "DBC" Simulation**: We gave each `Logger` instance a **100KB weight**. This mimics a heavy resource (like a database connection) that takes up real space in your RAM.
2. **Modular Cleanup**: We moved the messy profiling logic out of `App.java` and into a dedicated `MemoryProfiler` class. This keeps the code clean and professional.
3. **Retention Policy**: In the non-singleton test, we store every new instance in a `List`. Normally, Java would delete these "garbage" objects, but by keeping them in a list, we simulate a system that "leaks" connections or fails to reuse them.

## Comparing the Original vs. This Task
| Feature | Original Lab 2 | This Memory Profiler Task |
| :--- | :--- | :--- |
| **Logger Weight** | Negligible (Empty class) | **100KB** (Simulated payload) |
| **App Logic** | Single execution | **1000-loop simulation** |
| **Object Lifecycle** | Garbage collected immediately | **Guaranteed Retention** (for Non-Singleton) |
| **Output** | Console logs | **Formatted Memory Table (MB)** |

## The Simulation Design
We built a memory profiler that measures exactly how many **Megabytes (MB)** your app is eating. No fancy baselines here, just the raw truth from the JVM heap.

### 1. The Logger with 100KB Weight
Every `Logger` instance has a `100KB` payload. Why 100KB? Well, we tried 1KB and 10KB, but they were too small to show a "scary" enough growth on modern high-RAM devices. With 100KB, creating 1,000 instances gives us a clear **100MB** jump! And if you're feeling brave, **10,000 objects will eat up about 1GB of your RAM**—so watch out!

### 2. Absolute Memory Measurement
We're using absolute values (`totalMemory - freeMemory`) converted to MB. We found that using a baseline was a bit messy because the JVM's Garbage Collector likes to move things around, sometimes giving us negative numbers which just looked weird.

### 3. User Input
The app now asks you how many iterations you want to run. This lets you decide how much "pressure" you want to put on your system. It also shows a warning about memory usage to make sure you don't accidentally freeze your machine.

### 4. Keeping Objects Alive
- **Singleton**: We reuse the same single instance. The memory stays flat because, well, there's only one object!
- **Non-Singleton**: Every time we loop, we create a new `Logger` and shove it into a `List`. This tells the JVM: "Don't touch this! I'm still using it." That's how we see the memory grow.

## The Big Lesson
- **Singleton**: 1 instance = Flat memory. It's efficient and clean.
- **Non-Singleton**: 1,000 instances = ~100MB growth. This is how you accidentally crash a server by leaking connections!

## Why does Singleton memory fluctuate slightly?
You might see the numbers move by a few KB even in the Singleton test. Don't panic!
1. **JVM Background Noise**: The JVM is busy in the background doing things like JIT compilation.
2. **Temporary Objects**: We're still creating `Order` and `Shipment` objects. They get cleaned up eventually, but they might show up for a split second.

## How to Run the show
```bash
cd lab2
mvn compile
mvn exec:java -Dexec.mainClass="sa.edu.kau.fcit.cpit252.App"
```
