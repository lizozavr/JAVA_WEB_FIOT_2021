package com.kpi;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.*;

public class FindDecimalTask implements Callable<String> {

    private BlockingQueue<File> queue;

    public FindDecimalTask(BlockingQueue<File>queue) {
        this.queue = queue;
    }

    @Override
    public String call() {
        String resultRecord = "";
        try {
            while (true) {
                File file = queue.take();
                if (file == DecimalFilesSearcher.EXIT) {
                    queue.put(file);
                    break;
                } else {
                    resultRecord += countDecimalSum(file);
                }
            }
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
        return resultRecord;
    }


    private String countDecimalSum(File file) {
        try (Scanner scanner = new Scanner(new FileInputStream(file))) {
            double sum = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] splitedParts = line.split(" ");
                for (String part : splitedParts) {
                    if (part.matches("^[+-]?([0-9]+\\.?[0-9]*|\\.[0-9]+)$")) {
                        sum += Double.parseDouble(part);
                    }
                }
            }
            return "File name: " + file.getName() + ", Decimal sum: " + sum+" \n";

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
