package de.cuelex.launcher;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.ConsoleLoggerType;
import de.cuelex.logger.thread.RunningThread;
import de.cuelex.logger.thread.StartingThread;
import de.cuelex.logger.thread.StoppingThread;
import de.cuelex.logger.thread.UpdateThread;
import de.cuelex.main.HomeCloud;

import java.util.Scanner;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: HomeCloud/de.cuelex.launcher
    Date: 19.11.2020
    
*/
public class Launcher {

    public static Launcher instance = new Launcher();
    public static Launcher getInstance() {
        return instance;
    }
    private boolean cloudRunning = false;
    public boolean isCloudRunning() {
        return cloudRunning;
    }
    private String startingDate;
    public String getStartingDate() {
        return startingDate;
    }
    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public void start(){
        getInstance().cloudRunning = true;
        HomeCloud.getInstance().getTavaniaThread().startThread(new UpdateThread(), "UpdateThread");
        HomeCloud.getInstance().getTavaniaThread().startThread(new StartingThread(), "StartingThread");
        HomeCloud.getInstance().getTavaniaThread().startThread(new RunningThread(), "RunningThread");
    }

    public void stop(){
        getInstance().cloudRunning = false;
        HomeCloud.getInstance().getTavaniaThread().startThread(new StoppingThread(), "StoppingThread");
    }

    public void restart(){
        if(getInstance().cloudRunning){
            stop();
            ConsoleLogger.getInstance().log(ConsoleLoggerType.SUCCESS, Launcher.class,"HomeCloud successfully stopped!");
            start();
        }else{
            ConsoleLogger.getInstance().log(ConsoleLoggerType.ERROR, Launcher.class,"HomeCloud couldn't restart, because the system is not running!");
        }
    }

}
