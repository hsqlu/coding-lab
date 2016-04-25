package com.hsqlu.coding.concurrent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created: 2016/4/11.
 * Author: Qiannan Lu
 */
public class Downloader extends Thread {
    private InputStream in;
    private OutputStream out;

    private ArrayList<ProcessListener> listeners;
    private CopyOnWriteArrayList<ProcessListener> copyOnWriteListeners;

    public Downloader(URL url, String outputFilename) throws IOException {
        in = url.openConnection().getInputStream();
        out = new FileOutputStream(outputFilename);
        listeners = new ArrayList<>();
        copyOnWriteListeners = new CopyOnWriteArrayList<>();
    }

    /*
    public synchronized void addListener(ProcessListener listener) {
        listeners.add(listener);
    }
    */

    public void addListener(ProcessListener listener) {
        copyOnWriteListeners.add(listener);
    }

    public synchronized void removeListener(ProcessListener listener) {
        listeners.remove(listener);
    }

    private synchronized void updateProgress(int n) {
        for (ProcessListener listener : listeners) {
            listener.onProgress(n);
        }
    }

    private void refactoredUpdateProgress(int n ) {
        ArrayList<ProcessListener> listenersCopy;
        synchronized (this) {
            listenersCopy = (ArrayList<ProcessListener>) listeners.clone();
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

class ProcessListener {
    public void onProgress(int n) {
    }
}
