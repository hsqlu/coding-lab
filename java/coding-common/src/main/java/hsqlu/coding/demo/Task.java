package hsqlu.coding.demo;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

/**
 * Created: 2016/4/7.
 * Author: Qiannan Lu
 */
public class Task<P, R> implements Runnable, Observer {
    public static void setThreadMaxNum(int max) {

    }

    public static enum TaskPriority {
        max, min;
    }

    protected final static Exception withOutException = new Exception("The state is without");

    private static HashMap<String, Task> nameTasks;

    public static HashMap<String, Task> getNameTasks() {
        if (nameTasks == null) {
            nameTasks = new HashMap<>();
        }
        return nameTasks;
    }


    @Override
    public void update(Observable o, Object arg) {

    }

    @Override
    public void run() {

    }
}
