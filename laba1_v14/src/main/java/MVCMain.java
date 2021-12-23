import com.kpi.DecimalFilesSearcher;
import com.kpi.FindDecimalTask;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.concurrent.*;

//   /Users/elizavetabojko/Desktop/files

public class MVCMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter files directory: ");
        String directory = sc.next();

        while (!new File(directory).exists()) {
            System.out.println("Enter the existing files directory: \n");
            directory = sc.nextLine();
        }

        System.out.println("Enter the directory for saving counting results: ");
        String resultDirectory = sc.next();

        while (new File(resultDirectory).exists()) {
            System.out.println("Result directory is not empty, choose another one:");
            resultDirectory = sc.nextLine();
        }

        BlockingQueue<File> queue = new ArrayBlockingQueue<File>(100);
        Runnable searchTask = new DecimalFilesSearcher(queue, new File(directory));
        ExecutorService pool = Executors.newCachedThreadPool();
        int filesCount = new File(directory).listFiles().length;
        Future[] futures = new Future[filesCount];
        Future searchFuture = pool.submit(searchTask);

        try {
            searchFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
        for (int i = 0; i < filesCount; i++) {
            futures[i] = pool.submit(new FindDecimalTask(queue));
        }

        String result = "";
        try {
            for (int i = 0; i < filesCount; i++) {
                result += futures[i].get();
            }
            writeResultToFile(result, resultDirectory);
        }catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        pool.shutdown();
        System.out.println(result);
    }

    private static void writeResultToFile(String result, String resultDirectory) {
        try {
            File outputFile = new File(resultDirectory);
            Files.createDirectories(outputFile.getParentFile().toPath());
            boolean created = outputFile.createNewFile();
            if (created) {
                try (BufferedWriter destination = new BufferedWriter(new FileWriter(outputFile))) {
                    destination.write(result);
                }
                System.out.println("Results was successfully added to "+ resultDirectory);
            } else {
                System.out.println("Unable to create file:" + outputFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
