package edu.canisius.csc213.lab3;

import edu.canisius.csc213.util.*;

/**
 * Demonstrates how to dump running processes if an exception occurs.
 */
public class DebugExample {

    public static void main(String[] args) {
        System.out.println("Starting DebugExample...");
        
        try {
            // Simulate some operation that may throw an exception
            dangerousOperation();
            System.out.println("Operation completed successfully.");
        } catch (RuntimeException e) {
            System.err.println("[ERROR] A runtime exception occurred: " + e.getMessage());
            
            // 1) Log additional info
            System.err.println("[DEBUG] Dumping running processes for troubleshooting...");
            
            // 2) Call our SysDebugger to gather system info
            SysDebugger.logRunningProcesses();

	    // 3)Log the time of the crash
	    SysDebugger.logCurrentDateTime();
	    
        }

        System.out.println("Exiting program.");
    }

    /**
     * A method that simulates an operation which might throw a RuntimeException.
     */
    private static void dangerousOperation() {
        // We'll randomly throw an exception 50% of the time for demonstration:
        if (Math.random() > 0.5) {
            throw new RuntimeException("Simulated crash!");
        }
        // Otherwise, do something "normal"
        System.out.println("dangerousOperation succeeded. No crash this time.");
    }
}

