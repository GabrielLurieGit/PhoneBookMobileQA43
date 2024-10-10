package config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InternetManager {
    public void cpuUsage () throws IOException, InterruptedException {
        String[] cpuCommand = {"adb", "shell", "dumpsys", "cpuinfo", "|", "grep", "com.sheygam.contactapp"};
        System.out.println("CPU USAGE: ");
        executeCommand(cpuCommand);
    }
    public void memoryMonitoring() throws IOException, InterruptedException {
        String[] memoryCommand = {"adb", "shell", "dumpsys", "meminfo", "com.sheygam.contactapp"};
        System.out.println("Memory USAGE: ");
        executeCommand(memoryCommand);
    }


    public void executeCommand(String[] command) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();
        process.waitFor();
        try( BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        }
    }
}

