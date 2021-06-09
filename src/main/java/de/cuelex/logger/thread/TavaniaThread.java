package de.cuelex.logger.thread;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.LoggerType;

import java.util.ArrayList;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.logger.thread
    Date: 19.11.2020
    
*/
public class TavaniaThread {

    private final ArrayList<Thread> runningThreads = new ArrayList<>();
    private Thread thread;

    /**
     * Starting a thread
     *
     * @param runnable   is needed class
     * @param threadName is the custom name
     */
    public void startThread(Runnable runnable, String threadName) {
        thread = new Thread(runnable);
        thread.setName(threadName);
        thread.start();
        runningThreads.add(thread);
        ConsoleLogger.getInstance().log(LoggerType.SUCCESS, TavaniaThread.class, "Started thread '" + threadName + "' successfully.");
    }

    /**
     * Stopping a thread
     *
     * @param threadName is the custom thread name, defined in the start method
     */
    public void stopThread(String threadName) {
        getRunningThreads().remove(getThread(threadName));
        getThread(threadName).stop();
        ConsoleLogger.getInstance().log(LoggerType.SUCCESS, TavaniaThread.class, "Stopped thread '" + threadName + "' successfully.");
    }

    /**
     * Getting a thread
     *
     * @param threadName is the custom name
     * @return a thread by using the java thread class
     */
    public Thread getThread(String threadName) {
        for (java.lang.Thread t : java.lang.Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(threadName)) return t;
        }
        return null;
    }

    /**
     * Getting all running threads
     *
     * @return all running threads
     */
    public ArrayList<Thread> getRunningThreads() {
        return runningThreads;
    }

    public Thread getThread() {
        return thread;
    }


}
