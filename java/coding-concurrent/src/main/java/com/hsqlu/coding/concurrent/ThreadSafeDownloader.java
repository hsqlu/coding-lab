package com.hsqlu.coding.concurrent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created: 2016/4/12.
 * Author: Qiannan Lu
 */
public class ThreadSafeDownloader {
    private InputStream in;
    private OutputStream out;

    private CopyOnWriteArrayList<ProcessListener> listeners;

    public ThreadSafeDownloader(URL url, String outputFilename) throws IOException {
        in = url.openConnection().getInputStream();
        out = new FileOutputStream(outputFilename);
        listeners = new CopyOnWriteArrayList<>();
    }

    public void addListener(ProcessListener listener) {
        listeners.add(listener);
    }

    public void removeListener(ProcessListener listener) {
        listeners.remove(listener);
    }

    private void updateProgress(int n) {
        for (ProcessListener listener : listeners) {
            listener.onProgress(n);
        }
    }

    private void refactoredUpdateProgress(int n ) {
        CopyOnWriteArrayList<ProcessListener> listenersCopy;
        synchronized (this) {
            listenersCopy = (CopyOnWriteArrayList<ProcessListener>) listeners.clone();
        }
        for (ProcessListener listener : listenersCopy) {
            listener.onProgress(n);
        }
    }

    public void run() {
        int n = 0, total = 0;
        byte[] buffer = new byte[1024];

        try {
            while((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);;
                total += n;
                updateProgress(total);
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
