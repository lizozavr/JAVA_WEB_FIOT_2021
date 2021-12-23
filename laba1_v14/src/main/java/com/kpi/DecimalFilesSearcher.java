package com.kpi;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class DecimalFilesSearcher implements Runnable{
    private BlockingQueue<File> queue;
    private File startDirectory;
    // Специальный файл, отмечающий окончание списка всех найденных файлов
    public static final File EXIT = new File("");

    public DecimalFilesSearcher(BlockingQueue<File> queue, File startDirectory){
        this.queue = queue;
        this.startDirectory = startDirectory;
    }

    @Override
    public void run() {
        try {
            // Разбор директории на поддиректории
            runDirectory(startDirectory);
            queue.put(EXIT);
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    private void runDirectory(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                runDirectory(file);
            }
            else
                queue.put(file);
        }
    }
}
