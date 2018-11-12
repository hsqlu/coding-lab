package com.hsqlu.coding.concurrent.phaser;

import java.io.File;
import java.util.*;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * Created: 2016/4/26.
 * Author: Qiannan Lu
 */
public class FileSearch implements Runnable {
    private String initPath;
    private String end;
    private List<String> results;
    private Phaser phaser;

    public FileSearch(String initPath, String end, Phaser phaser) {
        this.initPath = initPath;
        this.end = end;
        this.results = new ArrayList<>();
        this.phaser = phaser;
    }

    private void directoryProcess(File file) {
        File[] files = file.listFiles();
        if (null != files) {
//            Arrays.asList(files).stream().map(File::isDirectory).forEach(e -> directoryProcess(e));
            for (File file1 : files) {
                if (file1.isDirectory()) {
                    directoryProcess(file1);
                } else {
                    fileProcess(file1);
                }
            }
        }
    }

    private void fileProcess(File file) {
        if (file.getName().endsWith(end)) {
            results.add(file.getAbsolutePath());
        }
    }

    private void filterResults() {
        List<String> tmpResults = new ArrayList<>();
        long actualDate = new Date().getTime();
        for (int i = 0; i < results.size(); ++i) {
            File file = new File(results.get(i));
            long fileDate = file.lastModified();
            if (actualDate - fileDate < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS)) {
                tmpResults.add(results.get(i));
            }
        }
        results = tmpResults;
    }

    private boolean checkResults() {
        if (results.isEmpty()) {
            System.out.printf("%s: Phase %d: 0 results.\n", Thread.currentThread().getName(), phaser.getPhase());
            System.out.printf("%s: Phase %d: End.\n", Thread.currentThread().getName(), phaser.getPhase());
            phaser.arriveAndDeregister();
            return false;
        } else {
            System.out.printf("%s: Phase %d: %d results.\n", Thread.currentThread().getName(), phaser.getPhase(), results.size());
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }

    private void showInfo() {
        for (String result : results) {
            File file = new File(result);
            System.out.printf("%s: %s\n", Thread.currentThread().getName(), file.getAbsolutePath());
        }
        phaser.arriveAndAwaitAdvance();
    }

    @Override
    public void run() {
        phaser.arriveAndAwaitAdvance();
        System.out.printf("%s: Starting.\n", Thread.currentThread().getName());
        File file = new File(initPath);
        if (file.isDirectory()) {
            directoryProcess(file);
        }
        if (!checkResults()) {
            return;
        }
        filterResults();
        if (!checkResults()) {
            return;
        }
        showInfo();
        phaser.arriveAndDeregister();
        System.out.printf("%s: Work completed.\n", Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        FileSearch system = new FileSearch("C:\\Windows", "log", phaser);
        FileSearch apps = new FileSearch("C:\\Program Files", "log", phaser);
        FileSearch documents = new FileSearch("C:\\Users\\qlu3\\Documents", "log", phaser);
        Thread systemThread = new Thread(system, "System");
        systemThread.start();

        Thread appsThread = new Thread(apps, "Apps");
        appsThread.start();

        Thread documentsThread = new Thread(documents, "Documents");
        documentsThread.start();

        try {
            systemThread.join();
            appsThread.join();
            documentsThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Terminated: " + phaser.isTerminated());
    }
}
