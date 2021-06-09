package de.cuelex.launcher;

import de.cuelex.logger.ConsoleLogger;
import de.cuelex.logger.LoggerType;
import de.cuelex.logger.thread.StartingThread;
import de.cuelex.logger.thread.StoppingThread;
import de.cuelex.logger.thread.UpdateThread;
import de.cuelex.main.TavaniaCloud;

/*

    Copyright © 2019 Alexander F.
    Twitter: @Taventiksch
    Location: TavaniaCloud/de.cuelex.launcher
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

    public void start() {
        getInstance().cloudRunning = true;
        TavaniaCloud.getInstance().getTavaniaThread().startThread(new UpdateThread(), "UpdateThread");
        TavaniaCloud.getInstance().getTavaniaThread().startThread(new StartingThread(), "StartingThread");
    }

    public void stop(){
        getInstance().cloudRunning = false;
        TavaniaCloud.getInstance().getTavaniaThread().startThread(new StoppingThread(), "StoppingThread");
    }

    public void restart(){
        if(getInstance().cloudRunning){
            stop();
            ConsoleLogger.getInstance().log(LoggerType.SUCCESS, Launcher.class, "TavaniaCloud successfully stopped!");
            start();
        }else{
            ConsoleLogger.getInstance().log(LoggerType.ERROR, Launcher.class, "TavaniaCloud couldn't restart, because the system is not running!");
        }
    }

}
