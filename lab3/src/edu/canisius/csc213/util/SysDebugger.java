package edu.canisius.csc213.util;

import java.time.LocalDateTime; 

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * SysDebugger - A utility class with static methods to gather
 * system diagnostics (e.g., process list) after errors.
 */
public class SysDebugger {

    /**
     * Logs the currently running processes (Linux/macOS example).
     * On Windows, adjust to "tasklist" or similar.
     */
    public static void logRunningProcesses() {
        System.out.println("=== Process List Dump ===");
        try {
            // Using "ps -e" for demonstration (Linux/macOS).
            // On Windows, you might do: ProcessBuilder pb = new ProcessBuilder("tasklist");
            ProcessBuilder pb = new ProcessBuilder("ps", "-e");
            Process process = pb.start();
            
            try (BufferedReader br = new BufferedReader(
                     new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
            System.out.println("=== End of Process List ===");
        } catch (IOException e) {
            System.err.println("[SysDebugger] Could not retrieve process list: " + e.getMessage());
        }
    }
    public static void logCurrentDateTime() {
    	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	Date date = new Date();
    	System.out.println("=== Current Date & Time ===");
    	System.out.println(date.toString());
    	System.out.println("===========================");
    } 

}

